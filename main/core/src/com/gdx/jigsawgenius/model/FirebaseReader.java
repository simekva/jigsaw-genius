package com.gdx.jigsawgenius.model;

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

    /**
     * Creates a tile object with a given list of tiles.
     *
     * @param databaseUrlInput
     * @param sessionPinInput
     * @param isHostInput
     */
    public FirebaseReader(final String databaseUrlInput,
            final String sessionPinInput, final boolean isHostInput) {
        this.databaseUrl = databaseUrlInput;
        this.sessionPin = sessionPinInput;
        this.isHost = isHostInput;
        data = "";
        xOutput = new ArrayList<>();
        yOutput = new ArrayList<>();
        tilesOutput = new ArrayList<>();
    }

    /**
     * Reads database that the session is connected to. Updates every second.
     *
     */
    public void startReading() {
        Thread readerThread = new Thread(() -> {
            while (true) {
                try {
                    readData();
                    processData(this.getData());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        readerThread.start();
    }

    /**
     * Reads the data from the tables in the database.Depending on which players
     * runs the function, a table "player1" or "player2" gets read from.
     *
     * @return String of data.
     */
    private String readData() {
        String player;
        if (isHost) {
            player = "player2";
        } else {
            player = "player1";
        }

        String url = databaseUrl + "/session" + sessionPin + "/"
                + player + "/tiles.json"; // Changes read-URl based on
                                          // which player is reading
                                          // from table

        try {
            URI uri = new URI(url);
            URL firebaseUrl = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) firebaseUrl
                    .openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                String jsonResponse = response.toString();
                this.data = jsonResponse;
                processData(jsonResponse); // Process the JSON response
                return jsonResponse;
            } else {
                System.out.println("Failed to retrieve data. Response code: "
                        + responseCode); // error code for wrong
                                         // data format
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Process the data from the database. Converts the data from .json to
     * readable data. This data is used when drawing a tile in the game later.
     *
     * @param jsonResponse
     */
    public void processData(final String jsonResponse) {
        int startPos = jsonResponse.indexOf("message");
        while (startPos != -1) {
            int endPos = jsonResponse.indexOf("}", startPos);
            String message = jsonResponse.substring(startPos, endPos);
            int xStartPos = message.indexOf("[") + 1;
            int xEndPos = message.indexOf(",", xStartPos);
            int x = Integer.parseInt(message.substring(
                    xStartPos, xEndPos).trim());
            int yStartPos = xEndPos + 1;
            int yEndPos = message.indexOf(",", yStartPos);
            int y = Integer.parseInt(message.substring(
                    yStartPos, yEndPos).trim());
            int tilesStartPos = message.indexOf("[", yEndPos) + 1;
            int tilesEndPos = message.indexOf("]", tilesStartPos);
            String[] tilesArray = message.substring(
                    tilesStartPos, tilesEndPos).split(",");

            List<Integer> tilesArrayInteger = new ArrayList<Integer>();

            for (int i = 0; i < tilesArray.length; i++) {
                tilesArray[i] = tilesArray[i]
                        .substring(1, tilesArray[i].length() - 1);
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

    /**
     * Function to get Data.
     *
     * @return this.data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Fetches the X-coordinates for tile stored in database.
     *
     * @return xOutput - X coordinates for tile
     */
    public List<Integer> getX() {
        return this.xOutput;
    }

    /**
     * Fetches the Y-coordinates for tile stored in database.
     *
     * @return yOutput - Y coordinates for tile
     */
    public List<Integer> getY() {
        return this.yOutput;
    }

    /**
     * Fetches the Biome IDs for tile stored in database.
     *
     * @return tilesOutput - Biome IDs for the tile
     */
    public List<List<Integer>> getTiles() {
        return this.tilesOutput;
    }
}
