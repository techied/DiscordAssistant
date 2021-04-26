import com.mautini.assistant.config.AssistantConf;
import com.mautini.assistant.config.AuthenticationConf;
import com.mautini.assistant.config.DeviceRegisterConf;
import com.mautini.assistant.config.IoConf;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import redis.clients.jedis.JedisPool;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class DiscordAssistant implements EventListener {

    static byte[] assistantBeep;
    static byte[] assistantEndingBeep;
    static byte[] assistantBeepNoSpeak;
    static JedisPool pool;
    static AuthenticationConf authenticationConf;
    static DeviceRegisterConf deviceRegisterConf;
    static AssistantConf assistantConf;
    static IoConf ioConf;

    public static void main(String[] args) throws LoginException, IOException, URISyntaxException {

        Config root = ConfigFactory.load();
        authenticationConf = ConfigBeanFactory.create(root.getConfig("authentication"), AuthenticationConf.class);
        deviceRegisterConf = ConfigBeanFactory.create(root.getConfig("deviceRegister"), DeviceRegisterConf.class);
        assistantConf = ConfigBeanFactory.create(root.getConfig("assistant"), AssistantConf.class);
        ioConf = ConfigBeanFactory.create(root.getConfig("io"), IoConf.class);

        JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.setActivity(Activity.listening("\"OK, Google\""));
        builder.addEventListeners(new MessageListener());
        builder.build();

        assistantBeep = Files.readAllBytes(new File("assistant-beep.raw").toPath());
        assistantEndingBeep = Files.readAllBytes(new File("assistant-beep-end.raw").toPath());
        assistantBeepNoSpeak = Files.readAllBytes(new File("assistant-beep-nospeak.raw").toPath());

        pool = new JedisPool(new URI(args[1]));
    }

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}