package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.controller.DrawerController;
import com.gdx.jigsawgenius.controller.GameInputController;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;

public class MultiPlayerScreen extends ScreenAdapter implements ScreenInterface {

    SpriteBatch batch;
    CameraHandler cameraHandler;
    Assets assets;
    Game game;
    GameLogicController gameLogicController;
    GameInputController inputProcessor;
    DrawerController drawerController;

    public MultiPlayerScreen(Assets assets, Game game) {
        this.assets = assets;
        this.game = game;

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        cameraHandler = new CameraHandler();
        gameLogicController = new GameLogicController(2);
        inputProcessor = new GameInputController(this);
        drawerController = new DrawerController();
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

    @Override
    public void resize(int width, int height) {
        cameraHandler.getCamera().viewportWidth = 30f;
        cameraHandler.getCamera().viewportHeight = 30f * height / width;
        cameraHandler.update();
    }

    @Override
    public void placeTile(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeTile'");
    }

    @Override
    public CameraHandler getCameraHandler() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCamera'");
    }

}
