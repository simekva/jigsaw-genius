package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.controller.DrawerController;
import com.gdx.jigsawgenius.controller.GameInputController;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Tile;

public class SinglePlayerScreen extends ScreenAdapter implements ScreenInterface {

    SpriteBatch batch;
    SpriteBatch batch2; // For drawing stuff independent of the camera
    CameraHandler cameraHandler;
    Assets assets;
    Game game;
    GameLogicController gameLogicController;
    GameInputController inputProcessor;
    DrawerController drawerController;
    Tile topTile;
    TileDrawer topTileDrawer;
    BitmapFont font;

    public SinglePlayerScreen(Assets assets, Game game) {
        this.game = game;
        this.assets = assets;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        cameraHandler = new CameraHandler();
        gameLogicController = new GameLogicController(1);
        inputProcessor = new GameInputController(this);
        drawerController = new DrawerController();

        topTile = gameLogicController.getPlayer(1).getTopTile();

        topTileDrawer = new TileDrawer();

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);

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

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.R)) {
            this.rotateTile();
        }
        batch2.begin();
        topTileDrawer.drawTile(assets, topTile, batch2, Gdx.graphics.getWidth() / 6,
                Gdx.graphics.getHeight() / 5,
                0.5f);
        font.draw(batch2,
                String.valueOf(gameLogicController.getPlayer(1).getScore()),
                Gdx.graphics.getWidth() / 6,
                Gdx.graphics.getHeight());
        batch2.end();
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
        topTile = gameLogicController.getPlayer(1).getTopTile();
    }

    @Override
    public CameraHandler getCameraHandler() {
        return this.cameraHandler;
    }

    public void rotateTile() {
        gameLogicController.getPlayer(1).rotateTile();
        topTile = gameLogicController.getPlayer(1).getTopTile();
    }

    @Override
    public GameLogicController getController() {
        return this.gameLogicController;
    }
}
