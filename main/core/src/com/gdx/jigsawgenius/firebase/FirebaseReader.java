package com.gdx.jigsawgenius.firebase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirebaseReader {

    private final String databaseUrl;
    private final String sessionPin;
    private final boolean isHost;
    private String data;

    private List<Integer> xOutput;
    private List<Integer> yOutput;
    private List<List<Integer>> tilesOutput;

    public FirebaseReader(String databaseUrl, String sessionPin, boolean isHost) {
        this.databaseUrl = databaseUrl;
        this.sessionPin = sessionPin;
        this.isHost = isHost;
        data = "";
        xOutput = new ArrayList<>();
        yOutput = new ArrayList<>();
        tilesOutput = new ArrayList<>();
    }

    public void startReading() {
        Thread readerThread = new Thread(() -> {
            while (true) {
                try {
                    readData();
                    processData(this.getData());
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
                // System.out.println("Received JSON response: " + jsonResponse);
                this.data = jsonResponse;
                processData(jsonResponse); // Process the JSON response
                return jsonResponse;
            } else {
                System.out.println("Failed to retrieve data. Response code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void processData(String jsonResponse) {
        int startPos = jsonResponse.indexOf("message");
        while (startPos != -1) {
            int endPos = jsonResponse.indexOf("}", startPos);
            String message = jsonResponse.substring(startPos, endPos);
            int xStartPos = message.indexOf("[") + 1;
            int xEndPos = message.indexOf(",", xStartPos);
            int x = Integer.parseInt(message.substring(xStartPos, xEndPos).trim());
            int yStartPos = xEndPos + 1;
            int yEndPos = message.indexOf(",", yStartPos);
            int y = Integer.parseInt(message.substring(yStartPos, yEndPos).trim());
            int tilesStartPos = message.indexOf("[", yEndPos) + 1;
            int tilesEndPos = message.indexOf("]", tilesStartPos);
            String[] tilesArray = message.substring(tilesStartPos, tilesEndPos).split(",");

            List<Integer> tilesArrayInteger = new ArrayList<Integer>();

            for (int i = 0; i < tilesArray.length; i++) {
                tilesArray[i] = tilesArray[i].substring(1, tilesArray[i].length() - 1);
                tilesArrayInteger.add(Integer.parseInt(tilesArray[i]));
            }
            System.out.println(x);
            System.out.println(y);
            System.out.println(tilesArrayInteger.toArray().toString());
            startPos = jsonResponse.indexOf("message", endPos);

            xOutput.add(x);
            yOutput.add(y);
            tilesOutput.add(tilesArrayInteger);
        }
    }

    public String getData() {
        return this.data;
    }

    public List<Integer> getX() {
        return this.xOutput;
    }

    public List<Integer> getY() {
        return this.yOutput;
    }

    public List<List<Integer>> getTiles() {
        return this.tilesOutput;
    }
}
