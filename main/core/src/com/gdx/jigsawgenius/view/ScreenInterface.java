package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Camera;

public interface ScreenInterface {

    /**
     * Method for initializing fields in the screen.
     */
    void show();

    /**
     * Method for rendering the screen.
     *
     * @param delta
     */
    void render(float delta);

    /**
     * Method for disposing in the screen.
     */
    void dispose();

    /**
     * Method to handle the camera.
     */
    void handleCamera();

    /**
     * Method to handle resizing.
     *
     * @param width
     * @param height
     */
    void resize(int width, int height);

    /**
     * Method to place a tile.
     *
     * @param x
     * @param y
     */
    void placeTile(int x, int y);

    /**
     * Method to return the camera.
     *
     * @return camera of the screen.
     */
    Camera getCamera();

}
