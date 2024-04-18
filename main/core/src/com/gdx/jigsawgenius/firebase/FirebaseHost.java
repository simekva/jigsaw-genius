package com.gdx.jigsawgenius.firebase;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class FirebaseHost {
    public static void sendData() {
        try {
            int pin = generateRandomNumber(1000, 9999);
            String sessionName = "session" + pin;
            // Proper Firebase URL with path and .json suffix
            URI uri = new URI("https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app/" + sessionName + ".json");

            // Convert URI to URL
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT"); // Use PUT to overwrite or create data
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Initialize players with dummy data
            String data = String.format("{\"pin\": \"%d\", \"score\": \"0\", \"player1\": {\"tiles\": {\"init\": \"empty\"}}, \"player2\": {\"tiles\": {\"init\": \"empty\"}}}", pin);

            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(out);
            connection.getOutputStream().close();

            // Check response code and message
            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Response Message: " + connection.getResponseMessage());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
