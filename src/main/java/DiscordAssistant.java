import com.mautini.assistant.api.AssistantClient;
import com.mautini.assistant.authentication.AuthenticationHelper;
import com.mautini.assistant.config.*;
import com.mautini.assistant.device.DeviceRegister;
import com.mautini.assistant.exception.AuthenticationException;
import com.mautini.assistant.exception.DeviceRegisterException;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.security.auth.login.LoginException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class DiscordAssistant implements EventListener {

    static byte[] assistantBeep;
    static byte[] assistantEndingBeep;
    static byte[] assistantBeepNoSpeak;
    private static JedisPool pool;

    public static void main(String[] args) throws LoginException, DeviceRegisterException, AuthenticationException, IOException, URISyntaxException {

        Config root = ConfigFactory.load();
        AuthenticationConf authenticationConf = ConfigBeanFactory.create(root.getConfig("authentication"), AuthenticationConf.class);
        DeviceRegisterConf deviceRegisterConf = ConfigBeanFactory.create(root.getConfig("deviceRegister"), DeviceRegisterConf.class);
        AssistantConf assistantConf = ConfigBeanFactory.create(root.getConfig("assistant"), AssistantConf.class);
        IoConf ioConf = ConfigBeanFactory.create(root.getConfig("io"), IoConf.class);

        AuthenticationHelper authenticationHelper = new AuthenticationHelper(authenticationConf);
        authenticationHelper
                .authenticate()
                .orElseThrow(() -> new AuthenticationException("Error during authentication"));

        if (authenticationHelper.expired()) {
            authenticationHelper
                    .refreshAccessToken()
                    .orElseThrow(() -> new AuthenticationException("Error refreshing access token"));
        }

        DeviceRegister deviceRegister = new DeviceRegister(deviceRegisterConf, authenticationHelper.getOAuthCredentials().getAccessToken());
        deviceRegister.register();

        AssistantClient assistantClient = new AssistantClient(authenticationHelper.getOAuthCredentials(), assistantConf,
                deviceRegister.getDeviceModel(), deviceRegister.getDevice(), ioConf);

        JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.setActivity(Activity.listening("\"OK, Google\""));
        builder.addEventListeners(new MessageListener(authenticationHelper, assistantClient));
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

    private static byte[] convertAudio(byte[] inputAudio, AudioFormat oldFormat, AudioFormat newFormat) throws IOException {
        return AudioSystem.getAudioInputStream(newFormat, new AudioInputStream(new ByteArrayInputStream(inputAudio), oldFormat, inputAudio.length)).readAllBytes();
    }
}