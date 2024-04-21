package com.gdx.jigsawgenius.controller;


import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.view.ScreenInterface;
import com.gdx.jigsawgenius.firebase.FirebaseSender;
import com.gdx.jigsawgenius.firebase.FirebaseHost;
import com.gdx.jigsawgenius.model.Tile;

public class GameInputControllerMulti implements InputProcessor {

    /**
     * Active screen in the game.
     */
    private ScreenInterface screen;
    private boolean isHost;
    String pin;

    /**
     * Sets the screen to the screen active in the game.
     *
     * @param screenInput
     */
    public GameInputControllerMulti(final ScreenInterface screenInput, boolean isHost, String pin) {
        this.screen = screenInput;
        this.isHost = isHost;
        this.pin = pin;
    }

    /**
     * Unused.
     */
    @Override
    public boolean keyDown(final int keycode) {
        return false;
    }

    /**
     * Unused.
     */
    @Override
    public boolean keyUp(final int keycode) {
        return false;
    }

    /**
     * Unused.
     */
    @Override
    public boolean keyTyped(final char character) {
        return false;
    }

    int attempt = 0;

    @Override
    public final boolean touchDown(final int screenX, final int screenY,
            final int pointer, final int button) {

        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        Camera camera = screen.getCameraHandler().getCamera();
        camera.unproject(worldCoordinates);

        float worldX = worldCoordinates.x - Assets.WORLD_SIZE / 2;
        float worldY = worldCoordinates.y - Assets.WORLD_SIZE / 2;

        System.out.println("Clicked on: " + worldX + ", " + worldY);

        int[] tileCoords = convertToWorldCoords1(worldX, worldY);

        try {
            screen.placeTile(this.convertToWorldCoords(worldX, worldY)[0],
                    this.convertToWorldCoords(worldX, worldY)[1]);

            // Sends data to Firebase based on each click
            attempt++;
            int sessionPin = FirebaseHost.getPin();

            // TODO: Må klare å importere hvordan tiles ser ut, i tileData, så dette kan
            // sendes

            Tile tile = screen.getController().getBoard().getTile(this.convertToWorldCoords(worldX, worldY)[0],
                    this.convertToWorldCoords(worldX, worldY)[1]);
            if (isHost) {
                // Send data for the host player
                FirebaseSender.sendData(String.valueOf(sessionPin), Integer.toString(attempt),
                        tileCoords[0], tileCoords[1], tile.getBiomeIDs(), true);
            } else {
                // Send data for the joining player
                FirebaseSender.sendData(pin, Integer.toString(attempt), tileCoords[0],
                        tileCoords[1], tile.getBiomeIDs(), false);
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    /**
     * Unused.
     */
    @Override
    public final boolean touchUp(final int screenX, final int screenY,
            final int pointer, final int button) {
        return false;
    }

    /**
     * Unused.
     */
    @Override
    public final boolean touchCancelled(
            final int screenX, final int screenY,
            final int pointer, final int button) {
        return false;
    }

    /**
     * Unused.
     */
    @Override
    public final boolean touchDragged(
            final int screenX, final int screenY, final int pointer) {
        return false;
    }

    /**
     * Unused.
     */
    @Override
    public final boolean mouseMoved(final int screenX, final int screenY) {
        return false;
    }

    /**
     * Unused.
     */
    @Override
    public final boolean scrolled(final float amountX, final float amountY) {
        return false;
    }

    /**
     * Converts the world coordinates to TileManager coordinates.
     *
     * @param worldX
     * @param worldY
     * @return tile coordinates.
     */
    private int[] convertToWorldCoords(final float worldX, final float worldY) {
        return new int[] {
                (int) (worldX / 172.5), (int) (worldY / 300)
        };
    }

    public int[] convertToWorldCoords1(final float worldX, final float worldY) {
        // Calculate the coordinates based on the tile size and orientation
        int x = (int) (worldX / Assets.pieceHeight);
        int y = (int) (worldY / (Assets.pieceHeight * 1.732));

        // Return the coordinates as an array
        return new int[] { x, y };
    }

}
