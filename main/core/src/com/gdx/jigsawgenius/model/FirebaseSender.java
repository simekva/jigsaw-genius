package com.gdx.jigsawgenius.model;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FirebaseSender {

    private static String url = "https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app";

     /**
     * Sends data to Firebase Database, based on the URL of the session. This URL connects to a Direbase Database
     * 
     * @params sessionPin
     * @params attempts
     * @params x - X coordinate for tile placement
     * @params y - Y coordinate for tile placement
     * @params tileData - IDs for biome sectors
     * @params isHost - boolean, if player is host or not
     */
    public static void sendData(String sessionPin, String attempt, int x, int y,
                                List<Integer> tileData, boolean isHost) {
        String player;
        if (isHost) {
            player = "player1";
        } else {
            player = "player2";
        }

        try {
            // Proper Firebase URL with path and .json suffix
            URI uri = new URI(url
                    + "/session" + sessionPin + "/" + player + "/tiles/pos" + attempt + ".json");

            // Convert URI to URL
            URL url = uri.toURL();

            //Sets up HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            //Sends data for biome IDs as a list of 6 integers
            StringBuilder tileDataJson = new StringBuilder();
            tileDataJson.append("[");
            for (int i = 0; i < tileData.size(); i++) {
                tileDataJson.append("\"").append(tileData.get(i)).append("\"");
                if (i < tileData.size() - 1) {
                    tileDataJson.append(",");
                }
            }
            tileDataJson.append("]");

            String data = "{\"message\": [" + x + ", " + y + ", " + tileDataJson.toString() + "]}";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(out);
            connection.getOutputStream().close();

            // Log response code and message to check if it was successful
            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Response Message: " + connection.getResponseMessage());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
