package com.gdx.jigsawgenius.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.gdx.jigsawgenius.model.Assets;

public class GameInputProcessor implements InputProcessor {

    private int origin = Assets.WORLD_SIZE / 2;

    private float originTileXOffset = Assets.pieceHeight;
    private float originTileYOffset = Assets.pieceWidth;

    private com.gdx.jigsawgenius.main main;

    public GameInputProcessor(com.gdx.jigsawgenius.main main) {
        this.main = main;
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

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        Vector3 worldCoordinates = new Vector3(x, y, 0);
        Camera camera = main.getCamera();
        camera.unproject(worldCoordinates);

        float worldX = worldCoordinates.x;
        float worldY = worldCoordinates.y;

        System.out.println("clicked at: " + worldX + ", " + worldY);

        main.placeTile(2, 0);
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

}
