package com.gdx.jigsawgenius.firebase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class FirebaseReader {

    private final String databaseUrl;
    private final String sessionPin;
    private final boolean isHost;

    public FirebaseReader(String databaseUrl, String sessionPin, boolean isHost) {
        this.databaseUrl = databaseUrl;
        this.sessionPin = sessionPin;
        this.isHost = isHost;
    }

    public void startReading() {
        Thread readerThread = new Thread(() -> {
            while (true) {
                try {
                    readData();
                    Thread.sleep(5000); // Sleep for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        readerThread.start();
    }

    private String readData() {
        String player;
        if (isHost) {
            player = "player2";
        } else {
            player = "player1";
        }
        String url = databaseUrl + "/session" + sessionPin + "/" + player + "/tiles.json";

        try {
            URI uri = new URI(url);
            URL firebaseUrl = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) firebaseUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                String jsonResponse = response.toString();
                System.out.println("Received JSON response: " + jsonResponse);
                return jsonResponse;
                // Process the JSON response here
            } else {
                System.out.println("Failed to retrieve data. Response code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
