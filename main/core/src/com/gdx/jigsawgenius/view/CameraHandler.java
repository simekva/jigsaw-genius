package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdx.jigsawgenius.model.Assets;

public class CameraHandler {

    /**
     * Camera for the application.
     */
    private OrthographicCamera camera;

    /**
     * CameraHandler constructor. Places the camera in the middle of the board,
     * and sets the zoom.
     */
    public CameraHandler() {
        camera = new OrthographicCamera(30, 30
                * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f,
                camera.viewportHeight / 2f, 0);
        camera.zoom = 50;
        camera.position.set(Assets.WORLD_SIZE / 2, Assets.WORLD_SIZE / 2, 0);
        camera.update();
    }

    /**
     * Handles input to move the camera.
     */
    public void handleCamera() {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
            camera.translate(-10, 0, 0);
            System.out.println(camera.position.x);
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {

            camera.translate(10, 0, 0);
            System.out.println(camera.position.x);
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
            camera.translate(0, -10, 0);
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
            camera.translate(0, 10, 0);
        }
    }

    /**
     * Returns the camera.
     *
     * @return camera.
     */
    public Camera getCamera() {
        return this.camera;
    }

    /**
     * Handles the zoom of the camera.
     *
     * @param value
     */
    public void zoom(final float value) {
        if (value > 0) {
            if (camera.zoom < 200) {
                camera.zoom += 10;
            }
        } else {
            if (camera.zoom > 20) {
                camera.zoom -= 10;
            }
        }

    }

    /**
     * Updates the camera. Is called every frame in screen.
     */
    public void update() {
        this.camera.update();
    }
}
