package com.gdx.jigsawgenius;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FirebaseSender {
    public static void sendData(String message) {
        try {
            // Proper Firebase URL with path and .json suffix
            URI uri = new URI("https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app/test.json");

            // Convert URI to URL
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT"); // Use PUT to overwrite or create data
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Data you want to send, as a JSON string
            String data = "{\"message\": \"" + message + "\"}";

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
}

