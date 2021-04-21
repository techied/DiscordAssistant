import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.security.auth.login.LoginException;

public class DiscordAssistant implements EventListener {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.setActivity(Activity.listening("\"OK, Google\""));
        builder.addEventListeners(new MessageListener());
        builder.build();
    }

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}