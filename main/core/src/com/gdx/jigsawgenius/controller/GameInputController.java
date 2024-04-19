package com.gdx.jigsawgenius.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.view.ScreenInterface;

public class GameInputController implements InputProcessor {

    /**
     * Active screen in the game.
     */
    private ScreenInterface screen;

    /**
     * Sets the screen to the screen active in the game.
     *
     * @param screenInput
     */
    public GameInputController(final ScreenInterface screenInput) {
        this.screen = screenInput;
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

        try {
            screen.placeTile(this.convertToWorldCoords(worldX, worldY)[0],
                    this.convertToWorldCoords(worldX, worldY)[1]);

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
        screen.getCameraHandler().zoom(amountY);
        return true;
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

}
