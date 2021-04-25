import com.mautini.assistant.api.AssistantClient;
import com.mautini.assistant.authentication.AuthenticationHelper;
import com.mautini.assistant.exception.AuthenticationException;
import com.mautini.assistant.exception.ConverseException;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MessageListener extends ListenerAdapter {
    static AudioHandler handler;

    AuthenticationHelper authenticationHelper;
    AssistantClient assistantClient;

    public MessageListener(AuthenticationHelper authenticationHelper, AssistantClient assistantClient) {
        this.authenticationHelper = authenticationHelper;
        this.assistantClient = assistantClient;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().equalsIgnoreCase("listen")) {
            GuildVoiceState state = event.getMember().getVoiceState();
            if (state.inVoiceChannel()) {
                handler = new AudioHandler(assistantClient);
                event.getGuild().getAudioManager().setSendingHandler(handler);
                event.getGuild().getAudioManager().setReceivingHandler(handler);
                event.getGuild().getAudioManager().openAudioConnection(state.getChannel());

            }
        } else if (event.getMessage().getContentStripped().equalsIgnoreCase("disconnect")) {
            event.getGuild().getAudioManager().closeAudioConnection();
        } else if (event.getMessage().getContentStripped().startsWith("assistant")) {
            if (authenticationHelper.expired()) {
                try {
                    authenticationHelper
                            .refreshAccessToken()
                            .orElseThrow(() -> new AuthenticationException("Error refreshing access token"));
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                }
                assistantClient.updateCredentials(authenticationHelper.getOAuthCredentials());
            }


            byte[] request = event.getMessage().getContentStripped().substring(10).getBytes();

            try {
                assistantClient.requestAssistant(request);


                byte[] bytes = assistantClient.getAudioResponse();
                System.out.println("Before upscaling: " + bytes.length);
                AudioFormat oldFormat = new AudioFormat(24000f, 16, 1, true, false);
                AudioFormat newFormat = new AudioFormat(48000f, 16, 2, true, true);
                AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(bytes), oldFormat, bytes.length / 2);
                byte[] output = AudioSystem.getAudioInputStream(newFormat, stream).readAllBytes();
                System.out.println("After upscaling: " + output.length);
                Files.write(new File("testFile").toPath(), output);
                handler.queueAudio(output);
                System.out.println(assistantClient.getTextResponse());
            } catch (ConverseException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
