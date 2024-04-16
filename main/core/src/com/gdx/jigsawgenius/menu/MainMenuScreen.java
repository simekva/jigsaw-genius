package com.gdx.jigsawgenius.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen extends ScreenAdapter {
    private final Game game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;

    public MainMenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("background.png");
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        font = new BitmapFont();

        skin.add("default-font", font);
        skin.add("background", new Texture("rectangle.png")); // You can replace "white.png" with any white texture

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("background");
        buttonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        buttonStyle.font = skin.getFont("default-font");

        TextButton singlePlayerButton = new TextButton("Single Player", buttonStyle);
        singlePlayerButton.setSize(200, 50);
        singlePlayerButton.setPosition(Gdx.graphics.getWidth() / 2 - singlePlayerButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        singlePlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SinglePlayerScreen());
            }
        });

        TextButton multiPlayerButton = new TextButton("Multi Player", buttonStyle);
        multiPlayerButton.setSize(200, 50);
        multiPlayerButton.setPosition(Gdx.graphics.getWidth() / 2 - multiPlayerButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 100);
        multiPlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MultiPlayerScreen());
            }
        });

        stage.addActor(singlePlayerButton);
        stage.addActor(multiPlayerButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        batch.dispose();
        backgroundTexture.dispose();
        stage.dispose();
        skin.dispose();
        font.dispose();
    }
}
