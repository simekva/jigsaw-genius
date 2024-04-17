package com.gdx.jigsawgenius.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.view.ScreenInterface;

public class GameInputController implements InputProcessor {

    private ScreenInterface screen;

    public GameInputController(ScreenInterface screen) {
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    // @Override
    // public boolean touchDown(int x, int y, int pointer, int button) {

    // Vector3 worldCoordinates = new Vector3(x, y, 0);
    // Camera camera = main.getCamera();
    // camera.unproject(worldCoordinates);

    // float worldX = worldCoordinates.x - Assets.WORLD_SIZE / 2; // 0 is middle of
    // origin
    // float worldY = worldCoordinates.y - Assets.WORLD_SIZE / 2; // 0 is middle of
    // origin

    // int tileX = 0;
    // int tileY = 0;

    // if (worldX >= 0) {
    // tileX = (int) (worldX / Assets.pieceHeight) + 1;
    // }
    // if (worldX < 0) {
    // tileX = (int) (worldX / Assets.pieceHeight) - 1;
    // }
    // if (worldY >= 0) {
    // tileY = (int) (worldY / Assets.pieceWidth + 1);
    // }
    // if (worldY < 0) {
    // tileY = (int) (worldY / Assets.pieceWidth - 1);
    // }
    // if (worldY < Assets.pieceWidth / 2 && worldY > 0) {
    // tileY = 0;
    // }
    // if (-worldY < Assets.pieceWidth / 2 && -worldY > 0) {
    // tileY = 0;
    // }
    // System.out.println(tileX + ", " + tileY);
    // try {
    // main.placeTile(tileX, tileY);
    // } catch (Exception e) {
    // }
    // return true;

    // }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        Camera camera = screen.getCamera();
        camera.unproject(worldCoordinates);

        float worldX = worldCoordinates.x - Assets.WORLD_SIZE / 2; // 0 is middle of
        float worldY = worldCoordinates.y - Assets.WORLD_SIZE / 2; // 0 is middle of

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

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private int[] convertToWorldCoords(float worldX, float worldY) {
        return new int[] {
                (int) (worldX / 172.5), (int) (worldY / 300)
        };
    }

}
