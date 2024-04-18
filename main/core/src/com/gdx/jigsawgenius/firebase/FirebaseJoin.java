package com.gdx.jigsawgenius.firebase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class FirebaseJoin {
    public static String readData(String pin) {
        try {
            // Proper Firebase URL with path and .json suffix
            URI uri = new URI("https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app/session" + pin + ".json");

            // Convert URI to URL
            URL url = uri.toURL();

            // Open connection to URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Close connection
            connection.disconnect();

            // Return the data read from Firebase
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}