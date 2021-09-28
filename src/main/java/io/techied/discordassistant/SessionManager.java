package io.techied.discordassistant;

import ai.picovoice.porcupine.PorcupineException;
import audio.AudioUtil;
import com.mautini.assistant.exception.ConverseException;
import net.dv8tion.jda.api.audio.UserAudio;

import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import java.util.HashMap;

public class SessionManager {


    byte[] lastBytes;


    HashMap<Long, AssistantSession> audioStates = new HashMap<>();
    AudioHandler audioHandler;

    public SessionManager(AudioHandler audioHandler) {
        this.audioHandler = audioHandler;
    }

    public void addUserAudioState(Long userId, AssistantSession assistantSession) {
        audioStates.put(userId, assistantSession);
    }

    public void handleAudio(UserAudio userAudio) {

        long userId = userAudio.getUser().getIdLong();
        AssistantSession assistantSession = audioStates.get(userId);
        if (assistantSession == null)
            return;

        byte[] data = userAudio.getAudioData(1.0f);

        try {
            lastBytes = data; //TODO: Can remove lastBytes
            //TODO: Extract this into a metod in AssistantSession
            for (int i = 0; i < data.length; i++) {
                if (i % 6 == 0) { // Ugh, magic numbers... but it works.
                    if (assistantSession.isRecording()) { // If we are recording a query, save it to the queue
                        assistantSession.assistantQueue.write(data[i]);
                        if (data[i] != 0) {
                            assistantSession.setZeroes(0);
                        }
                    } else { // If we are not recording a query, process for wake word.
                        assistantSession.getWakeQueue().add(data[i]);
                    }
                }
            }
            //TODO: Extract this into a method in AssistantSession
            if (!assistantSession.isRecording() && assistantSession.getWakeQueue().size() > 1024) {
                // Keyword was heard, begin audio recording
                int keywordIndex = assistantSession.getWakeEngine().process(assistantSession.getNextAudioFrame());
                if (keywordIndex >= 0) {
                    System.out.println("got keyword");
                    audioHandler.queueAudio(AudioUtil.assistantBeep);
                    assistantSession.setRecording(true);
                    assistantSession.setZeroes(-100);
                }
            }
        } catch (PorcupineException e) {
            e.printStackTrace();
        }

        audioStates.put(userId, assistantSession);
    }


    boolean isRecording() {
        // Returns true if one of the AudioStates is recording
        for (AssistantSession assistantSession : audioStates.values())
            if (assistantSession.isRecording())
                return true;
        return false;
    }


    void processAudioStateTick() {
        for (AssistantSession assistantSession : audioStates.values()) {
            if (assistantSession.isRecording()) {
                assistantSession.zeroes++;
                if (assistantSession.zeroes == -1) {
                    // The user didn't speak in the allowed time, do nothing
                    System.out.println("didn't speak");
                    assistantSession.setRecording(false);
                    audioHandler.queueAudio(AudioUtil.assistantBeepNoSpeak);
                }
                if (assistantSession.zeroes > 50) {
                    // The user stopped speaking, request assistant
                    System.out.println("ended speech");
                    audioHandler.queueAudio(AudioUtil.assistantEndingBeep);
                    assistantSession.setRecording(false);
                    System.out.println("request");
                    try {
                        assistantSession.getAssistantClient().requestAssistant(assistantSession.getAssistantQueue().toByteArray());
                    } catch (ConverseException e) {
                        e.printStackTrace();
                    }
                    assistantSession.assistantQueue.reset();
                    System.out.println("convert audio");
                    byte[] audioResponse = assistantSession.getAssistantClient().getAudioResponse();
                    AudioFormat oldFormat = new AudioFormat(24000f, 16, 1, true, false);
                    byte[] stream = new byte[0];
                    try {
                        stream = AudioUtil.convertAudio(audioResponse, oldFormat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("queue audio");
                    audioHandler.queueAudio(stream);
                }
            }
        }
    }

    public void dispose() {
        for (AssistantSession assistantSession : audioStates.values()) {
            assistantSession.setRecording(false);
            assistantSession.getWakeEngine().delete();
        }
    }
}
