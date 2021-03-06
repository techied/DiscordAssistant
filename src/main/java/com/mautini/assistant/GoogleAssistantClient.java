package com.mautini.assistant;

import com.mautini.assistant.client.audio.AudioRecorder;
import com.mautini.assistant.config.IoConf;
import com.mautini.assistant.exception.AudioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class GoogleAssistantClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAssistantClient.class);
/*
    public static void main(String[] args) throws AuthenticationException, AudioException, ConverseException, DeviceRegisterException {

        Config root = ConfigFactory.load();
        AuthenticationConf authenticationConf = ConfigBeanFactory.create(root.getConfig("authentication"), AuthenticationConf.class);
        DeviceRegisterConf deviceRegisterConf = ConfigBeanFactory.create(root.getConfig("deviceRegister"), DeviceRegisterConf.class);
        AssistantConf assistantConf = ConfigBeanFactory.create(root.getConfig("assistant"), AssistantConf.class);
        AudioConf audioConf = ConfigBeanFactory.create(root.getConfig("audio"), AudioConf.class);
        IoConf ioConf = ConfigBeanFactory.create(root.getConfig("io"), IoConf.class);

        // Authentication
        AuthenticationHelper authenticationHelper = new AuthenticationHelper(authenticationConf);
        authenticationHelper
                .authenticate()
                .orElseThrow(() -> new AuthenticationException("Error during authentication"));

        // Check if we need to refresh the access token to request the api
        if (authenticationHelper.expired()) {
            authenticationHelper
                    .refreshAccessToken()
                    .orElseThrow(() -> new AuthenticationException("Error refreshing access token"));
        }

        // Register Device model and device
        DeviceRegister deviceRegister = new DeviceRegister(deviceRegisterConf, authenticationHelper.getOAuthCredentials().getAccessToken());
        deviceRegister.register();

        // Build the client (stub)
        AssistantClient assistantClient = new AssistantClient(authenticationHelper.getOAuthCredentials(), assistantConf,
                deviceRegister.getDeviceModel(), deviceRegister.getDevice(), ioConf);

        // Build the objects to record and play the conversation
        AudioRecorder audioRecorder = new AudioRecorder(audioConf);
        AudioPlayer audioPlayer = new AudioPlayer(audioConf);

        // Initiating Scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // Main loop
        while (true) {
            // Check if we need to refresh the access token to request the api
            if (authenticationHelper.expired()) {
                authenticationHelper
                        .refreshAccessToken()
                        .orElseThrow(() -> new AuthenticationException("Error refreshing access token"));

                // Update the token for the assistant client
                assistantClient.updateCredentials(authenticationHelper.getOAuthCredentials());
            }


            // Get input (text or voice)
            byte[] request = getInput(ioConf, scanner, audioRecorder);

            // requesting assistant with text query
            assistantClient.requestAssistant(request);
            LOGGER.info(assistantClient.getTextResponse());

            if (Boolean.TRUE.equals(ioConf.getOutputAudio())) {
                byte[] audioResponse = assistantClient.getAudioResponse();
                if (audioResponse.length > 0) {
                    audioPlayer.play(audioResponse);
                } else {
                    LOGGER.info("No response from the API");
                }
            }
        }
    }*/

    private static byte[] getInput(IoConf ioConf, Scanner scanner, AudioRecorder audioRecorder) throws AudioException {
        switch (ioConf.getInputMode()) {
            case IoConf.TEXT:
                LOGGER.info("Tap your request and press enter...");
                // Taking user input in
                String query = scanner.nextLine();
                // Converting user into byte array to keep consistency of requestAssistant params
                return query.getBytes();
            case IoConf.AUDIO:
                // Record
                return audioRecorder.getRecord();
            default:
                LOGGER.error("Unknown input mode {}", ioConf.getInputMode());
                return new byte[]{};
        }
    }
}
