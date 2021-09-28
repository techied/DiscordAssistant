package audio;

import net.dv8tion.jda.api.audio.AudioSendHandler;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AudioUtil {

    public static byte[] assistantEndingBeep;
    public static byte[] assistantBeep;
    public static byte[] assistantBeepNoSpeak;

    static {
        try {
            assistantBeep = Files.readAllBytes(new File("assistant-beep.raw").toPath());
            assistantEndingBeep = Files.readAllBytes(new File("assistant-beep-end.raw").toPath());
            assistantBeepNoSpeak = Files.readAllBytes(new File("assistant-beep-nospeak.raw").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] convertAudio(byte[] inputAudio, AudioFormat oldFormat) throws IOException {
        return AudioSystem.getAudioInputStream(AudioSendHandler.INPUT_FORMAT, new AudioInputStream(new ByteArrayInputStream(inputAudio), oldFormat, inputAudio.length)).readAllBytes();
    }
}
