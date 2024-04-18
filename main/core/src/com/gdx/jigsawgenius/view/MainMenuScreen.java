package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.jigsawgenius.JigsawGenius;
import com.gdx.jigsawgenius.model.Assets;


public class MainMenuScreen extends ScreenAdapter {

    private Assets assets;

    private final JigsawGenius game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture ribbonTexture;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;

    public MainMenuScreen(JigsawGenius game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        assets = game.getAssets();

        backgroundTexture = new Texture("background.png");
        ribbonTexture = new Texture("ribbon.png");
        // Scale down the ribbon image
        ribbonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ribbonTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        font = new BitmapFont();

        skin.add("default-font", font);
        skin.add("background", new Texture("rectangle.png"));

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("background");
        buttonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        buttonStyle.font = skin.getFont("default-font");

        TextButton singlePlayerButton = new TextButton("Single Player", buttonStyle);
        singlePlayerButton.setSize(200, 50);
        singlePlayerButton.setPosition(Gdx.graphics.getWidth() / 2 - singlePlayerButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
        singlePlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SinglePlayerScreen(assets, game));

            }
        });

        TextButton multiPlayerButton = new TextButton("Multi Player", buttonStyle);
        multiPlayerButton.setSize(200, 50);
        multiPlayerButton.setPosition(Gdx.graphics.getWidth() / 2 - multiPlayerButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - 100);
        multiPlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MultiPlayerScreen(game));

            }
        });

        //Help screen:
        Texture helpIconTexture = new Texture("help.png");
        Image helpIcon = new Image(helpIconTexture);
        helpIcon.setSize(70, 70); 

        helpIcon.setPosition(60, 60);

        helpIcon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new helpScreen(game));
            }
        });

        stage.addActor(singlePlayerButton);
        stage.addActor(multiPlayerButton);
        stage.addActor(helpIcon);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float ribbonWidth = ribbonTexture.getWidth() / 2;
        float ribbonHeight = ribbonTexture.getHeight() / 2;
        batch.draw(ribbonTexture, Gdx.graphics.getWidth() / 2 - ribbonWidth / 2, Gdx.graphics.getHeight() - 150,
                ribbonWidth, ribbonHeight);

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
        ribbonTexture.dispose();
        stage.dispose();
        skin.dispose();
        font.dispose();
    }


    
}
