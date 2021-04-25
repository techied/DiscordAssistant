import ai.picovoice.porcupine.Porcupine;
import ai.picovoice.porcupine.PorcupineException;
import com.mautini.assistant.api.AssistantClient;
import com.mautini.assistant.exception.ConverseException;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AudioHandler implements AudioSendHandler, AudioReceiveHandler {
    ConcurrentLinkedQueue<Byte> inputStream = new ConcurrentLinkedQueue<Byte>();
    byte[] lastBytes;
    //    Porcupine wakeDetect;
//    ConcurrentLinkedQueue<Byte> wakeQueue = new ConcurrentLinkedQueue<Byte>();
//    ByteArrayOutputStream assistantQueue = new ByteArrayOutputStream();
    AssistantClient assistantClient;
//    private boolean listening = false;
//    private int zeroes = 0;

    private static final Porcupine.Builder porcupineBuilder = new Porcupine.Builder().setKeyword("hey google");

    HashMap<Long, AudioState> audioStates = new HashMap<Long, AudioState>();

    public AudioHandler(AssistantClient assistantClient) {
        this.assistantClient = assistantClient;
    }

    public byte[] getLastBytes() {
        return lastBytes;
    }

    @Override
    public boolean canReceiveCombined() {
        return true;
    }

    @Override
    public boolean canReceiveUser() {
        return true;
    }

    @Override
    public void handleUserAudio(UserAudio userAudio) {
        long userId = userAudio.getUser().getIdLong();
        if (!audioStates.containsKey(userId)) {
            try {
                audioStates.put(userId, new AudioState(userId, assistantClient, porcupineBuilder.build()));
            } catch (PorcupineException e) {
                e.printStackTrace();
            }
        }

        AudioState audioState = audioStates.get(userId);
        byte[] data = userAudio.getAudioData(1.0f); // volume at 100% = 1.0 (50% = 0.5 / 55% = 0.55)

        try {
            lastBytes = data;
            for (int i = 0; i < data.length; i++) {
                if (i % 6 == 0) {
                    if (!audioState.isListening())
                        audioState.getWakeQueue().add(data[i]);


                    if (audioState.isListening()) {
                        audioState.assistantQueue.write(data[i]);
                        if (data[i] != 0) {
                            audioState.setZeroes(0);
//                            audioState.zeroes++;
//                            System.out.println(audioState.zeroes);
//                            if (audioState.zeroes > 20000) {
//                                System.out.println("ended speech");
//                                queueAudio(DiscordAssistant.assistantEndingBeep);
//                                audioState.setListening(false);
//                                System.out.println("request");
//                                assistantClient.requestAssistant(audioState.getAssistantQueue().toByteArray());
//                                audioState.assistantQueue.reset();
//                                System.out.println("convert audio");
//                                byte[] audioResponse = assistantClient.getAudioResponse();
//                                AudioFormat oldFormat = new AudioFormat(24000f, 16, 1, true, false);
//                                AudioFormat newFormat = new AudioFormat(48000f, 16, 2, true, true);
//                                byte[] stream = convertAudio(audioResponse, oldFormat, newFormat);
//                                System.out.println("queue audio");
//                                queueAudio(stream);
//                            }
                        }
                    }
                }
            }
            if (!audioState.isListening() && audioState.getWakeQueue().size() > 1024) {
                int keywordIndex = audioState.getWakeEngine().process(audioState.getNextAudioFrame());
                if (keywordIndex >= 0) {
                    System.out.println("got keyword");
                    queueAudio(DiscordAssistant.assistantBeep);
                    audioState.setListening(true);
                    audioState.setZeroes(-50000);
                }
            }
        } catch (PorcupineException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCombinedAudio(CombinedAudio combinedAudio) {

        for (AudioState audioState : audioStates.values()) {
            if (audioState.isListening()) {
                audioState.zeroes++;
                System.out.println(audioState.zeroes);
                if (audioState.zeroes > 50) {
                    System.out.println("ended speech");
                    queueAudio(DiscordAssistant.assistantEndingBeep);
                    audioState.setListening(false);
                    System.out.println("request");
                    try {
                        assistantClient.requestAssistant(audioState.getAssistantQueue().toByteArray());
                    } catch (ConverseException e) {
                        e.printStackTrace();
                    }
                    audioState.assistantQueue.reset();
                    System.out.println("convert audio");
                    byte[] audioResponse = assistantClient.getAudioResponse();
                    AudioFormat oldFormat = new AudioFormat(24000f, 16, 1, true, false);
                    AudioFormat newFormat = new AudioFormat(48000f, 16, 2, true, true);
                    byte[] stream = new byte[0];
                    try {
                        stream = convertAudio(audioResponse, oldFormat, newFormat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("queue audio");
                    queueAudio(stream);
                }
            }
        }

//        byte[] data = combinedAudio.getAudioData(1.0f); // volume at 100% = 1.0 (50% = 0.5 / 55% = 0.55)
//
//        try {
//            lastBytes = data;
//            for (int i = 0; i < data.length; i++) {
//                if (i % 6 == 0) {
//                    if (!listening)
//                        wakeQueue.add(data[i]);
//
//                    if (listening) {
//                        assistantQueue.write(data[i]);
//                        if (data[i] == 0) {
//                            zeroes++;
//                            if (zeroes > 20000) {
//                                System.out.println("ended speech");
//                                queueAudio(DiscordAssistant.assistantEndingBeep);
//                                listening = false;
//                                System.out.println("request");
//                                assistantClient.requestAssistant(assistantQueue.toByteArray());
//                                assistantQueue.reset();
//                                System.out.println(assistantClient.getTextResponse());
//                                System.out.println("convert audio");
//                                byte[] audioResponse = assistantClient.getAudioResponse();
//                                AudioFormat oldFormat = new AudioFormat(24000f, 16, 1, true, false);
//                                AudioFormat newFormat = new AudioFormat(48000f, 16, 2, true, true);
//                                byte[] stream = convertAudio(audioResponse, oldFormat, newFormat);
//                                System.out.println("queue audio");
//                                queueAudio(stream);
//                            }
//                        } else {
//                            zeroes = 0;
//                        }
//                    }
//                }
//            }
//            if (!listening && wakeQueue.size() > 1024) {
//                int keywordIndex = wakeDetect.process(getNextAudioFrame());
//                if (keywordIndex >= 0) {
//                    System.out.println("got keyword");
//                    queueAudio(DiscordAssistant.assistantBeep);
//                    listening = true;
//                    zeroes = -50000;
//                }
//            }
//        } catch (PorcupineException | ConverseException | IOException e) {
//            e.printStackTrace();
//        }
    }

    private byte[] convertAudio(byte[] inputAudio, AudioFormat oldFormat, AudioFormat newFormat) throws IOException {
        return AudioSystem.getAudioInputStream(newFormat, new AudioInputStream(new ByteArrayInputStream(assistantClient.getAudioResponse()), oldFormat, inputAudio.length)).readAllBytes();
    }

    @Override
    public boolean canProvide() {
        return inputStream.size() > 3840;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        ByteBuffer audio = ByteBuffer.allocate(3840);
        for (int i = 0; i < audio.capacity(); i++) {
            audio.put(inputStream.poll());
        }
        return audio.rewind();
    }

    @Override
    public boolean isOpus() {
        return false;
    }

    public void queueAudio(byte[] audio) {
        for (byte b : audio) {
            inputStream.add(b);
        }
    }
}