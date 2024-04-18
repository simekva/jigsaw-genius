package com.gdx.jigsawgenius.firebase;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FirebaseSender {
    public static void sendData(String sessionPin, String attempt, String message, String message2, List<String> tileData, boolean isHost) {
        String player;
        if (isHost) {
            player = "player1";
        } else {
            player = "player2";
        }
        
        try {
            // Proper Firebase URL with path and .json suffix
            URI uri = new URI("https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app/session" + sessionPin + "/" + player + "/tiles/pos" + attempt + ".json");

            
            
            // Convert URI to URL
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT"); // Use PUT to overwrite or create data
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            StringBuilder tileDataJson = new StringBuilder();
            tileDataJson.append("[");
            for (int i = 0; i < tileData.size(); i++) {
                tileDataJson.append("\"").append(tileData.get(i)).append("\"");
                if (i < tileData.size() - 1) {
                    tileDataJson.append(",");
                }
            }
            tileDataJson.append("]");

            // Data you want to send, as a JSON string
            String data = "{\"message\": \"" + message + "__" + message2 + "\", \"tile\": " + tileData + "}";

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
