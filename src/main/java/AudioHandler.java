import ai.picovoice.porcupine.Porcupine;
import ai.picovoice.porcupine.PorcupineException;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AudioHandler implements AudioSendHandler, AudioReceiveHandler {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] lastBytes;
    Porcupine handle;
    ConcurrentLinkedQueue<Byte> queue = new ConcurrentLinkedQueue<Byte>();

    short[] getNextAudioFrame() {
        byte[] nextFrameBytes = new byte[1024];
        for (int i = 0; i < 1024; i++) {
            nextFrameBytes[i] = queue.poll();
        }
        short[] shortArray = new short[nextFrameBytes.length / 2];
        ByteBuffer.wrap(nextFrameBytes).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shortArray);
        return shortArray;
    }

    public AudioHandler() {
        try {
            handle = new Porcupine.Builder()
                    .setKeyword("hey google")
                    .build();
            System.out.println("Using porcupine. Sample rate of " + handle.getSampleRate() + " and frame length of " + handle.getFrameLength());
//            handle.delete();
        } catch (PorcupineException e) {
            e.printStackTrace();
        }
    }

    public byte[] getLastBytes() {
        return lastBytes;
    }

    @Override
    public boolean canReceiveCombined() {
        return true;
    }

    @Override
    public void handleCombinedAudio(CombinedAudio combinedAudio) {
        byte[] data = combinedAudio.getAudioData(1.0f); // volume at 100% = 1.0 (50% = 0.5 / 55% = 0.55)
        try {
            outputStream.write(data);
            lastBytes = data;
            for (int i = 0; i < data.length; i++) {
                if (i % 6 == 0) {
                    queue.add(data[i]);
                }
            }

//            System.out.println("Queue size: " + queue.size());

            if (queue.size() > 1024) {
                int keywordIndex = handle.process(getNextAudioFrame());
                if (keywordIndex >= 0) {
                    System.out.println("got keyword");
                }
            }


        } catch (IOException | PorcupineException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean canProvide() {
        // If we have something in our buffer we can provide it to the send system
        return false;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        return null;
    }

    @Override
    public boolean isOpus() {
        return false;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }
}