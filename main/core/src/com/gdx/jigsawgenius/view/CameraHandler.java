package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdx.jigsawgenius.model.Assets;

public class CameraHandler {

    OrthographicCamera camera;

    public CameraHandler() {
        camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() /
                Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,
                0);
        camera.zoom = 50;
        camera.position.set(Assets.WORLD_SIZE / 2, Assets.WORLD_SIZE / 2, 0);
        camera.update();
    }

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

    public Camera getCamera() {
        return this.camera;
    }

    public void zoom(float value) {
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

    public void update() {
        this.camera.update();
    }
}
