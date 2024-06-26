package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.controller.DrawerController;
import com.gdx.jigsawgenius.controller.GameInputControllerMultiPlayer;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.FirebaseReader;
import com.gdx.jigsawgenius.model.Tile;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

public class MultiPlayerScreen extends ScreenAdapter implements ScreenInterface {

    SpriteBatch batch;
    CameraHandler cameraHandler;
    Assets assets;
    Game game;
    GameLogicController gameLogicController;
    GameInputControllerMultiPlayer inputProcessor;
    DrawerController drawerController;
    private boolean isHost;
    private String pin;
    SpriteBatch batch2;
    Tile topTile;
    TileDrawer topTileDrawer;
    BitmapFont font;
    FirebaseReader reader;

    /**
     * Creates MultiPlayerScreen Object
     * 
     * @param assets
     * @param game
     * @param pin
     * @param isHost
     */
    public MultiPlayerScreen(Assets assets, Game game, String pin, boolean isHost) {
        this.game = game;
        this.assets = assets;
        this.pin = pin;
        this.isHost = isHost;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        cameraHandler = new CameraHandler();
        gameLogicController = new GameLogicController(2);
        inputProcessor = new GameInputControllerMultiPlayer(this, isHost, pin);
        drawerController = new DrawerController();

        topTile = gameLogicController.getPlayer(1).getTopTile();
        topTileDrawer = new TileDrawer();

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);

        Gdx.input.setInputProcessor(inputProcessor);

        // Setup backend reader
        String baseURL = "https://jigsawgame-e855b-default-rtdb.europe-west1.firebasedatabase.app";
        reader = new FirebaseReader(baseURL, pin, isHost);
        reader.startReading();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        backendDataParser();
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

    /**
     * Updates gameLogicController with data from backend
     */
    public void backendDataParser() {
        for (int i = 0; i < reader.getX().size(); i++) {
            this.gameLogicController.placeTileFromBackend(reader.getX().get(i), reader.getY().get(i),
                    reader.getTiles().get(i));
        }
    }
}
