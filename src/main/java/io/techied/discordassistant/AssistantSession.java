package io.techied.discordassistant;

import ai.picovoice.porcupine.Porcupine;
import com.mautini.assistant.api.AssistantClient;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AssistantSession {
    private final Long userId;
    private final AssistantClient assistantClient;
    private final Porcupine wakeEngine;

    public Long getUserId() {
        return userId;
    }

    public AssistantClient getAssistantClient() {
        return assistantClient;
    }

    public Porcupine getWakeEngine() {
        return wakeEngine;
    }

    ConcurrentLinkedQueue<Byte> wakeQueue = new ConcurrentLinkedQueue<Byte>();
    boolean recording = false;
    int zeroes = 0;

    public int getZeroes() {
        return zeroes;
    }

    public void setZeroes(int zeroes) {
        this.zeroes = zeroes;
    }

    ByteArrayOutputStream assistantQueue = new ByteArrayOutputStream();

    public ByteArrayOutputStream getAssistantQueue() {
        return assistantQueue;
    }

    public void setAssistantQueue(ByteArrayOutputStream assistantQueue) {
        this.assistantQueue = assistantQueue;
    }

    public boolean isRecording() {
        return recording;
    }

    public void setRecording(boolean listening) {
        this.recording = listening;
    }

    public ConcurrentLinkedQueue<Byte> getWakeQueue() {
        return wakeQueue;
    }

    public void setWakeQueue(ConcurrentLinkedQueue<Byte> wakeQueue) {
        this.wakeQueue = wakeQueue;
    }

    short[] getNextAudioFrame() {
        byte[] nextFrameBytes = new byte[1024];
        for (int i = 0; i < 1024; i++) {
            nextFrameBytes[i] = wakeQueue.poll();
        }
        short[] shortArray = new short[nextFrameBytes.length / 2];
        ByteBuffer.wrap(nextFrameBytes).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shortArray);
        return shortArray;
    }

    public AssistantSession(Long userId, AssistantClient assistantClient, Porcupine wakeEngine) {
        this.userId = userId;
        this.assistantClient = assistantClient;
        this.wakeEngine = wakeEngine;
    }
}
