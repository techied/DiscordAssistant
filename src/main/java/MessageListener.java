import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MessageListener extends ListenerAdapter {
    static AudioHandler handler;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().equalsIgnoreCase("listen")) {
            GuildVoiceState state = event.getMember().getVoiceState();
            if (state.inVoiceChannel()) {
                handler = new AudioHandler();
                event.getGuild().getAudioManager().setSendingHandler(handler);
                event.getGuild().getAudioManager().setReceivingHandler(handler);
                event.getGuild().getAudioManager().openAudioConnection(state.getChannel());

            }
        } else if (event.getMessage().getContentStripped().equalsIgnoreCase("disconnect")) {
            try {
                event.getGuild().getAudioManager().closeAudioConnection();
                Files.write(new File("testFile").toPath(), handler.getOutputStream().toByteArray());
                System.out.println(handler.getLastBytes().length + " bytes per 20 ms");
                System.out.println("Resulting audio bitrate: " + handler.getLastBytes().length * 25 + " kbps");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
