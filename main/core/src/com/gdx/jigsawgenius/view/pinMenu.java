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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.jigsawgenius.JigsawGenius;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.firebase.FirebaseHost;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class pinMenu extends ScreenAdapter {

    private Assets assets;
    private final JigsawGenius game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture ribbonTexture;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private TextField codeField;

    private String pin;
    private boolean isHost;

    public pinMenu(JigsawGenius game, String pin, boolean isHost) {
        this.game = game;
        
        this.pin = pin;

        this.isHost = isHost;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        assets = game.getAssets();

        backgroundTexture = new Texture("background.png");
        ribbonTexture = new Texture("ribbon.png");
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

        TextButton hostButton = new TextButton("Start game", buttonStyle);
        hostButton.setSize(200, 50);
        hostButton.setPosition(Gdx.graphics.getWidth() / 2 - hostButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isHost = true; // For example, you can set it based on some condition
        
                // Start the SinglePlayerScreen and pass the pin and isHost variables
                game.setScreen(new MultiPlayer(assets, game, pin, isHost));
            }
        });



        Texture backgroundTexture = new Texture("pinBackground.png");
        NinePatch backgroundPatch = new NinePatch(backgroundTexture, 12, 12, 12, 12);
        NinePatchDrawable backgroundDrawable = new NinePatchDrawable(backgroundPatch);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default-font");
        labelStyle.fontColor = Color.BLACK;
        labelStyle.background = backgroundDrawable; // Set the background

        Label codeLabel = new Label("Tell the joining player to \n enter this 4-digit code: " + pin, labelStyle);
        codeLabel.setSize(220, 100);
        codeLabel.setPosition(Gdx.graphics.getWidth() / 2 - codeLabel.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 150);
        codeLabel.setAlignment(Align.center);


        TextButton backButton = new TextButton("Go back", buttonStyle);
        backButton.setSize(100, 120);
        backButton.setPosition(50, Gdx.graphics.getHeight() - backButton.getHeight() - 50);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MultiPlayerScreen(game));
            }
        });

        stage.addActor(backButton);

        stage.addActor(hostButton);
        stage.addActor(codeLabel);

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