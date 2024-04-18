package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.controller.DrawerController;
import com.gdx.jigsawgenius.controller.GameInputControllerMulti;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;

public class MultiPlayer extends ScreenAdapter implements ScreenInterface {

    SpriteBatch batch;
    CameraHandler cameraHandler;
    Assets assets;
    Game game;
    static GameLogicController gameLogicController;
    GameInputControllerMulti inputProcessor;
    DrawerController drawerController;
    private boolean isHost;
    private String pin;

    public MultiPlayer(Assets assets, Game game, String pin, boolean isHost) {
        this.game = game;
        this.assets = assets;
        this.pin = pin;
        this.isHost = isHost;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        cameraHandler = new CameraHandler();
        gameLogicController = new GameLogicController(1);
        inputProcessor = new GameInputControllerMulti(this, isHost, pin);
        drawerController = new DrawerController();

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();

        if (assets.manager.update()) {
            cameraHandler.handleCamera();
            cameraHandler.update();
            batch.setProjectionMatrix(cameraHandler.getCamera().combined);
            ScreenUtils.clear(255, 5, 5, 1);
            drawerController.drawBoard(gameLogicController.getBoard(), assets, batch, 500, 500);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.manager.clear();
    }

    @Override
    public void resize(int width, int height) {
        cameraHandler.getCamera().viewportWidth = 30f;
        cameraHandler.getCamera().viewportHeight = 30f * height / width;
        cameraHandler.update();
    }

    @Override
    public void placeTile(int x, int y) {
        gameLogicController.placeTile(x, y);
    }

    @Override
    public CameraHandler getCameraHandler() {
        return this.cameraHandler;
    }
}
