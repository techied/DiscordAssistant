import com.mautini.assistant.api.AssistantClient;
import com.mautini.assistant.authentication.AuthenticationHelper;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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
            handler.dispose();
            event.getGuild().getAudioManager().closeAudioConnection();
        }
    }
}
