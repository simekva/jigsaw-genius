package com.gdx.jigsawgenius.model;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class FirebaseHost {

    private FirebaseHost() {
        throw new UnsupportedOperationException(
                "Can't instantiate a utility class");
    }

    /**
     * Pin for session.
     */
    private static int pin;
    /**
     * URl for session.
     */
    private static String url = "https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app/";
    /**
     * Format for session.
     */
    private static String dataFormat = "{\"pin\": \"%d\", \"score\": \"0\", \"player1\": {\"tiles\": {\"init\": \"empty\"}}, \"player2\": {\"tiles\": {\"init\": \"empty\"}}}";

    /**
     * Function to initialize new session, and firebase Table.
     * Creates a random game.
     * pin.
     */
    public static void sendData() {
        try {
            pin = generatePin();
            String sessionName = "session" + pin;

            URI uri = new URI(url + sessionName
                    + ".json"); // Different URL based on session pin

            // Convert URI to URL
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod(
                    "PUT"); // Use PUT to overwrite or create data
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Initialize table session with empty data
            String data = String.format(dataFormat, pin);

            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(out);
            connection.getOutputStream().close();

            // Check response code and message
            System.out.println("Response Code: "
                    + connection.getResponseCode());
            System.out.println("Response Message: "
                    + connection.getResponseMessage());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to generate random 4-digit pin.
     *
     * @return generatedPin
     */
    public static int generatePin() {
        int min = 1000;
        int max = 9999;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Function to get Pin.
     *
     * @return pin
     */
    public static int getPin() {
        return pin;
    }

}
