package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.jigsawgenius.controller.DrawerController;
import com.gdx.jigsawgenius.controller.GameInputController;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;

public class SinglePlayerScreen extends ScreenAdapter implements ScreenInterface {

    SpriteBatch batch;
    OrthographicCamera camera;
    Assets assets;

    com.gdx.jigsawgenius.view.Button startButton;
    float buttonWidth;
    float buttonHeight;
    float buttonX;
    float buttonY;
    BitmapFont font;

    Stage stage;

    Game game;

    static GameLogicController controller;

    GameInputController inputProcessor;

    DrawerController drawerController;

    public SinglePlayerScreen(Assets assets, Game game) {
        this.game = game;
        this.assets = assets;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture buttonTexture = new Texture("single.png");
        float originalWidth = 150;
        float originaHeight = 50;
        buttonWidth = originalWidth * 1.5f;
        buttonHeight = originaHeight * 1.5f;

        buttonX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        buttonY = (Gdx.graphics.getHeight() - buttonHeight) / 2 + (2 * 96 / 2.54f); // Move down 2 cm

        startButton = new Button(buttonTexture, buttonX, buttonY, buttonWidth, buttonHeight);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        inputProcessor = new GameInputController(this);
        Gdx.input.setInputProcessor(inputProcessor);

        camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() /
                Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,
                0);
        camera.zoom = 50;
        camera.position.set(Assets.WORLD_SIZE / 2, Assets.WORLD_SIZE / 2, 0);
        camera.update();

        controller = new GameLogicController(1);
        drawerController = new DrawerController();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();

        if (assets.manager.update()) {
            handleCamera();
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            ScreenUtils.clear(255, 5, 5, 1);

            drawerController.drawBoard(controller.getBoard(), assets, batch, 500, 500);

        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.manager.clear();
        startButton.dispose();
        font.dispose();
    }

    public void handleCamera() {
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.A)) {
            camera.zoom += 10;
            System.out.println("zooming");
        }
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.Q)) {
            camera.zoom -= 10;
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
            camera.translate(-5, 0, 0);
            System.out.println(camera.position.x);
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {

            camera.translate(5, 0, 0);
            System.out.println(camera.position.x);
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
            camera.translate(0, -5, 0);
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
            camera.translate(0, 5, 0);
        }
    }

    @Override
    public void placeTile(int x, int y) {
        controller.placeTile(x, y);
    }

    public Camera getCamera() {
        return this.camera;
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 30f;
        camera.viewportHeight = 30f * height / width;
        camera.update();
    }

}
