package com.gdx.jigsawgenius.view;

import com.gdx.jigsawgenius.controller.GameLogicController;

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
     * Return camerahandler.
     *
     * @return camerahandler.
     */
    CameraHandler getCameraHandler();

    /**
     * Returns the game logic controller for the game.
     * 
     * @return
     */
    GameLogicController getController();
}
