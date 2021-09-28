package io.techied.discordassistant;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * So yeah I kind of waited too long before writing the comments for this class, it's a bit of a mess. Hopefully I can remember what all this is exactly and clean it up a fair amount.
 * <p>
 * Lots of inefficient code here.
 */

public class AudioHandler implements AudioSendHandler, AudioReceiveHandler {
    ConcurrentLinkedQueue<Byte> inputStream = new ConcurrentLinkedQueue<>();

    SessionManager sessionManager = new SessionManager(this);

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
        sessionManager.handleAudio(userAudio);
    }

    @Override
    public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
        sessionManager.processAudioStateTick();
    }


    @Override
    public boolean canProvide() {
        return inputStream.size() > 3840 || sessionManager.isRecording();
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
        sessionManager.dispose();
    }

    public void addUserAudioState(Long userId, AssistantSession session) {
        sessionManager.addUserAudioState(userId, session);
    }
}