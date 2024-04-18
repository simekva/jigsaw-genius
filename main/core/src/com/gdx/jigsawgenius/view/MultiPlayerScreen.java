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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.jigsawgenius.JigsawGenius;
import com.gdx.jigsawgenius.model.Assets;

import com.gdx.jigsawgenius.firebase.FirebaseHost;

public class MultiPlayerScreen extends ScreenAdapter {

    private Assets assets;
    private final JigsawGenius game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture ribbonTexture;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private TextField codeField;
    private boolean isHost = false;
    private String pin;

    public MultiPlayerScreen(JigsawGenius game) {
        this.game = game;
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

        TextButton hostButton = new TextButton("Host Game", buttonStyle);
        hostButton.setSize(200, 50);
        hostButton.setPosition(Gdx.graphics.getWidth() / 2 - hostButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                isHost = true;

                //Create new session in database
                FirebaseHost.sendData();

                //Helps display new pin in pinMenu
                int pin = FirebaseHost.getPin();

                //game.setScreen(new SinglePlayerScreen(assets, game));
                game.setScreen(new pinMenu(game, String.valueOf(pin), isHost));
            }
        });

        //Create a TextField for the 4-digit code
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default-font");
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = skin.getDrawable("background");

        codeField = new TextField("", textFieldStyle);
        codeField.setMessageText("Enter 4-digit code"); 
        codeField.setSize(200, 50);
        codeField.setPosition(Gdx.graphics.getWidth() / 2 - codeField.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 150);          //Sets position of text field
        codeField.setAlignment(Align.center);

        //Listener to clear text field when clicked
        codeField.addListener(new ClickListener() {
            @Override
                public void clicked(InputEvent event, float x, float y) {
                codeField.setText("");
                codeField.getStyle().fontColor = Color.WHITE;
            }
        });

        TextButton joinButton = new TextButton("Join Game", buttonStyle);
        joinButton.setSize(200, 50);
        joinButton.setPosition(Gdx.graphics.getWidth() / 2 - joinButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 100);        //Sets position of button

        //Listener to check for correct 4 digit input
        joinButton.addListener(new ClickListener() {
            @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (codeField.getText().matches("\\d{4}")) {

                        isHost = false;
                        pin = codeField.getText(); 

                        game.setScreen(new MultiPlayer(assets, game, pin, isHost));


                    } else {
                        codeField.setText("Invalid code! Please try again");
                        codeField.getStyle().fontColor = Color.RED;
                    }
            }
        });


        TextButton backButton = new TextButton("Go back", buttonStyle);
        backButton.setSize(100, 100);
        backButton.setPosition(50, Gdx.graphics.getHeight() - backButton.getHeight() - 50);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        stage.addActor(backButton);

        stage.addActor(hostButton);
        stage.addActor(joinButton);
        stage.addActor(codeField);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float ribbonWidth = ribbonTexture.getWidth() / 2;
        float ribbonHeight = ribbonTexture.getHeight() / 2;
        batch.draw(ribbonTexture, Gdx.graphics.getWidth() / 2 - ribbonWidth / 2, Gdx.graphics.getHeight() - 150, ribbonWidth, ribbonHeight);
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
