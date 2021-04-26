import ai.picovoice.porcupine.Porcupine;
import ai.picovoice.porcupine.PorcupineException;
import com.mautini.assistant.api.AssistantClient;
import com.mautini.assistant.authentication.AuthenticationHelper;
import com.mautini.assistant.device.DeviceRegister;
import com.mautini.assistant.exception.AuthenticationException;
import com.mautini.assistant.exception.ConverseException;
import com.mautini.assistant.exception.DeviceRegisterException;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;
import org.jetbrains.annotations.NotNull;
import redis.clients.jedis.Jedis;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * So yeah I kind of waited too long before writing the comments for this class, it's a bit of a mess. Hopefully I can remember what all this is exactly and clean it up a fair amount.
 * <p>
 * Lots of inefficient code here.
 */

public class AudioHandler implements AudioSendHandler, AudioReceiveHandler {
    ConcurrentLinkedQueue<Byte> inputStream = new ConcurrentLinkedQueue<>();
    byte[] lastBytes;

    private static final Porcupine.Builder porcupineBuilder = new Porcupine.Builder().setKeyword("hey google");

    HashMap<Long, AudioState> audioStates = new HashMap<>();

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

        AudioState audioState = null;
        try {
            if (audioStates.containsKey(userId)) {
                audioState = audioStates.get(userId);
            } else {
                try (Jedis jedis = DiscordAssistant.pool.getResource()) {
                    if (!jedis.exists(String.valueOf(userId))) {
                        AuthenticationHelper authenticationHelper = new AuthenticationHelper(DiscordAssistant.authenticationConf);
                        authenticationHelper
                                .authenticate()
                                .orElseThrow(() -> new AuthenticationException("Error during authentication"));

                        jedis.set(String.valueOf(userId), authenticationHelper.getCredentialJson());
                    }
                    String credentialJson = jedis.get(String.valueOf(userId));
                    AuthenticationHelper authenticationHelper = new AuthenticationHelper(DiscordAssistant.authenticationConf, credentialJson);
                    if (authenticationHelper.expired()) {
                        authenticationHelper
                                .refreshAccessToken()
                                .orElseThrow(() -> new AuthenticationException("Error refreshing access token"));
                    }
                    DeviceRegister deviceRegister = new DeviceRegister(DiscordAssistant.deviceRegisterConf, authenticationHelper.getOAuthCredentials().getAccessToken());
                    deviceRegister.register();
                    AssistantClient assistantClient = new AssistantClient(authenticationHelper.getOAuthCredentials(), DiscordAssistant.assistantConf,
                            deviceRegister.getDeviceModel(), deviceRegister.getDevice(), DiscordAssistant.ioConf);
                    audioState = new AudioState(userId, assistantClient, porcupineBuilder.build());
                }
            }
        } catch (PorcupineException | URISyntaxException | IOException | AuthenticationException | DeviceRegisterException e) {
            e.printStackTrace();
        }

        byte[] data = userAudio.getAudioData(1.0f);

        try {
            lastBytes = data;
            for (int i = 0; i < data.length; i++) {
                if (i % 6 == 0) { // Ugh, magic numbers... but it works.
                    if (audioState.isRecording()) { // If we are recording a query, save it to the queue
                        audioState.assistantQueue.write(data[i]);
                        if (data[i] != 0) {
                            audioState.setZeroes(0);
                        }
                    } else { // If we are not recording a query, process for wake word.
                        audioState.getWakeQueue().add(data[i]);
                    }
                }
            }
            if (!audioState.isRecording() && audioState.getWakeQueue().size() > 1024) {
                // Keyword was heard, begin audio recording
                int keywordIndex = audioState.getWakeEngine().process(audioState.getNextAudioFrame());
                if (keywordIndex >= 0) {
                    System.out.println("got keyword");
                    queueAudio(DiscordAssistant.assistantBeep);
                    audioState.setRecording(true);
                    audioState.setZeroes(-100);
                }
            }
        } catch (PorcupineException e) {
            e.printStackTrace();
        }

        audioStates.put(userId, audioState);
    }

    @Override
    public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
        processAudioStateTick();
    }

    private void processAudioStateTick() {
        for (AudioState audioState : audioStates.values()) {
            if (audioState.isRecording()) {
                audioState.zeroes++;
                if (audioState.zeroes == -1) {
                    // The user didn't speak in the allowed time, do nothing
                    System.out.println("didn't speak");
                    audioState.setRecording(false);
                    queueAudio(DiscordAssistant.assistantBeepNoSpeak);
                }
                if (audioState.zeroes > 50) {
                    // The user stopped speaking, request assistant
                    System.out.println("ended speech");
                    queueAudio(DiscordAssistant.assistantEndingBeep);
                    audioState.setRecording(false);
                    System.out.println("request");
                    try {
                        audioState.getAssistantClient().requestAssistant(audioState.getAssistantQueue().toByteArray());
                    } catch (ConverseException e) {
                        e.printStackTrace();
                    }
                    audioState.assistantQueue.reset();
                    System.out.println("convert audio");
                    byte[] audioResponse = audioState.getAssistantClient().getAudioResponse();
                    AudioFormat oldFormat = new AudioFormat(24000f, 16, 1, true, false);
                    byte[] stream = new byte[0];
                    try {
                        stream = convertAudio(audioResponse, oldFormat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("queue audio");
                    queueAudio(stream);
                }
            }
        }
    }

    private byte[] convertAudio(byte[] inputAudio, AudioFormat oldFormat) throws IOException {
        return AudioSystem.getAudioInputStream(AudioSendHandler.INPUT_FORMAT, new AudioInputStream(new ByteArrayInputStream(inputAudio), oldFormat, inputAudio.length)).readAllBytes();
    }

    @Override
    public boolean canProvide() {
        return inputStream.size() > 3840 || isRecording();
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        // If there is 20ms of audio to play, take the next 20ms and play it
        if (inputStream.size() > 3840) {
            ByteBuffer audio = ByteBuffer.allocate(3840);
            for (int i = 0; i < audio.capacity(); i++) {
                audio.put(inputStream.poll());
            }
            return audio.rewind();
        }
        return ByteBuffer.allocate(3840);
    }

    private boolean isRecording() {
        // Returns true if one of the AudioStates is recording
        boolean isRecording = false;
        for (AudioState audioState : audioStates.values())
            isRecording = audioState.isRecording() || isRecording;
        return isRecording;
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

    public void dispose() {
        for (AudioState audioState : audioStates.values()) {
            audioState.setRecording(false);
            audioState.getWakeEngine().delete();
        }
    }
}