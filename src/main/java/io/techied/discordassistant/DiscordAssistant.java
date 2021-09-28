package io.techied.discordassistant;

import com.mautini.assistant.config.AssistantConf;
import com.mautini.assistant.config.AuthenticationConf;
import com.mautini.assistant.config.DeviceRegisterConf;
import com.mautini.assistant.config.IoConf;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import io.techied.discordassistant.db.EphemeralKVDB;
import io.techied.discordassistant.db.KVDB;
import io.techied.discordassistant.db.RedisKVDB;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DiscordAssistant implements EventListener {
    static KVDB database;
    static AuthenticationConf authenticationConf;
    static DeviceRegisterConf deviceRegisterConf;
    static AssistantConf assistantConf;
    static IoConf ioConf;

    public DiscordAssistant(String[] args) throws LoginException, URISyntaxException {

        Config root = ConfigFactory.load();
        authenticationConf = ConfigBeanFactory.create(root.getConfig("authentication"), AuthenticationConf.class);
        deviceRegisterConf = ConfigBeanFactory.create(root.getConfig("deviceRegister"), DeviceRegisterConf.class);
        assistantConf = ConfigBeanFactory.create(root.getConfig("assistant"), AssistantConf.class);
        ioConf = ConfigBeanFactory.create(root.getConfig("io"), IoConf.class);

        JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.setActivity(Activity.listening("\"OK, Google\""));
        builder.addEventListeners(new MessageListener());
        builder.build();
        if (args.length < 2) {
            database = new EphemeralKVDB();
        } else {
            database = new RedisKVDB(new URI(args[1]));
        }
        System.out.println(database.getInfo());
    }

    public static void main(String[] args) throws LoginException, IOException, URISyntaxException {
        new DiscordAssistant(args);
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}