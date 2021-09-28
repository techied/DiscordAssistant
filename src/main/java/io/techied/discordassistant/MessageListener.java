package io.techied.discordassistant;

import ai.picovoice.porcupine.Porcupine;
import ai.picovoice.porcupine.PorcupineException;
import com.mautini.assistant.api.AssistantClient;
import com.mautini.assistant.authentication.AuthenticationHelper;
import com.mautini.assistant.device.DeviceRegister;
import com.mautini.assistant.exception.AuthenticationException;
import com.mautini.assistant.exception.DeviceRegisterException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class MessageListener extends ListenerAdapter {
    HashMap<Long, AudioHandler> audioHandlers = new HashMap<>();


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String content = event.getMessage().getContentStripped();
        if (content.equalsIgnoreCase("join")) {
            GuildVoiceState state = event.getMember().getVoiceState();
            if (state.inVoiceChannel()) {

                AudioHandler handler = new AudioHandler();
                event.getGuild().getAudioManager().setSendingHandler(handler);
                event.getGuild().getAudioManager().setReceivingHandler(handler);
                event.getGuild().getAudioManager().openAudioConnection(state.getChannel());
                try (Jedis jedis = DiscordAssistant.pool.getResource()) {
                    for (Member member : state.getChannel().getMembers()) {
                        Long userId = member.getIdLong();
                        if (jedis.exists(String.valueOf(userId))) {
                            String credentialJson = jedis.get(String.valueOf(userId));
                            AuthenticationHelper authenticationHelper = new AuthenticationHelper(DiscordAssistant.authenticationConf, credentialJson);
                            if (authenticationHelper.expired()) {
                                authenticationHelper
                                        .refreshAccessToken()
                                        .orElseThrow(() -> new AuthenticationException("Error refreshing access token"));
                            }
                            DeviceRegister deviceRegister = new DeviceRegister(DiscordAssistant.deviceRegisterConf, authenticationHelper.getOAuthCredentials().getAccessToken());
                            deviceRegister.register();
                            AssistantClient assistantClient = new AssistantClient(authenticationHelper.getOAuthCredentials(), DiscordAssistant.assistantConf,
                                    deviceRegister.getDeviceModel(), deviceRegister.getDevice(), DiscordAssistant.ioConf);
                            handler.addUserAudioState(userId, new AssistantSession(userId, assistantClient, new Porcupine.Builder().setKeyword("hey google").build()));
                        }
                    }
                } catch (DeviceRegisterException | AuthenticationException | PorcupineException e) {
                    e.printStackTrace();
                }
                audioHandlers.put(event.getGuild().getIdLong(), handler);
            }
        } else if (content.equalsIgnoreCase("leave")) {
            audioHandlers.remove(event.getGuild().getIdLong()).dispose();
            event.getGuild().getAudioManager().closeAudioConnection();
        } else if (content.toLowerCase().startsWith("auth")) {
            long userId = event.getAuthor().getIdLong();
            if (content.contains(" ")) {
                String code = content.split(" ", 2)[1];
                System.out.println(code);
                try (Jedis jedis = DiscordAssistant.pool.getResource()) {
                    AuthenticationHelper authenticationHelper = new AuthenticationHelper(DiscordAssistant.authenticationConf);
                    authenticationHelper
                            .authenticate(code)
                            .orElseThrow(() -> new AuthenticationException("Error during authentication"));

                    jedis.set(String.valueOf(userId), authenticationHelper.getCredentialJson());
                    event.getChannel().sendMessage("Authenticated successfully!").queue();
                } catch (AuthenticationException | URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                event.getMessage().addReaction("✅").queue();
                try (Jedis jedis = DiscordAssistant.pool.getResource()) {
                    if (jedis.exists(String.valueOf(userId))) {
                        event.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("You are already authenticated! Type \"clear\" to wipe your credentials.").queue());
                    } else {
                        event.getAuthor().openPrivateChannel().queue((privateChannel -> privateChannel.sendMessage(new EmbedBuilder().setTitle("Click to authenticate", "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/assistant-sdk-prototype&response_type=code&redirect_uri=urn:ietf:wg:oauth:2.0:oob&client_id=582422638744-0huvavhrdlmaavt8fore9f5jl9mr3o75.apps.googleusercontent.com").setDescription("Copy the code, then switch back here and type \"auth <code>\". Do not send this code anywhere but this private DM!").build()).queue()));
                    }
                }
            }
        } else if (content.equalsIgnoreCase("clear")) {
            try (Jedis jedis = DiscordAssistant.pool.getResource()) {
                if (jedis.exists(event.getAuthor().getId())) {
                    jedis.del(event.getAuthor().getId());
                    event.getMessage().addReaction("✅").queue();
                }
            }
        } else if(content.equalsIgnoreCase("help")){
            event.getChannel().sendMessage("Commands:\n`auth`: sends instructions for connecting your google account to the bot\n`join`: joins your voice channel (must use auth first)\n`leave`: leaves your voice channel\n`clear`: clears your google connection from the database").queue();
        }
    }
}