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

/**
 * The MainMenuScreen class represents the main menu of the game, where players can choose between
 * single-player and multi-player modes, as well as access the help screen.
 * It extends the ScreenAdapter class from the LibGDX framework.
 */
public class MainMenuScreen extends ScreenAdapter {

    private Assets assets; // Assets manager for game resources
    private final JigsawGenius game; // Reference to the game instance
    private SpriteBatch batch; // Sprite batch for rendering
    private Texture backgroundTexture; // Texture for the background
    private Texture ribbonTexture; // Texture for the ribbon
    private Stage stage; // Stage for UI components
    private Skin skin; // Skin for UI components
    private BitmapFont font; // Font for UI components

    /**
     * Creates a MainMenuScreen object.
     *
     * @param game The game instance
     */
    public MainMenuScreen(JigsawGenius game) {
        this.game = game;
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        batch = new SpriteBatch(); // Initialize sprite batch
        assets = game.getAssets(); // Get game assets manager

        loadTextures(); // Load background and ribbon textures
        setupUI(); // Setup UI components
    }

    /**
     * Loads the background and ribbon textures.
     */
    private void loadTextures() {
        backgroundTexture = new Texture("background.png"); // Load background texture
        ribbonTexture = new Texture("ribbon.png"); // Load ribbon texture
        ribbonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ribbonTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    /**
     * Sets up the user interface (UI) components.
     */
    private void setupUI() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // Initialize stage
        Gdx.input.setInputProcessor(stage); // Set input processor to stage

        skin = new Skin(); // Initialize skin
        font = new BitmapFont(); // Initialize font

        // Add font and background texture to skin
        skin.add("default-font", font);
        skin.add("background", new Texture("rectangle.png"));

        // Create button style
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("background");
        buttonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        buttonStyle.font = skin.getFont("default-font");

        // Create single player button and add listener
        createSinglePlayerButton(buttonStyle);

        // Create multi-player button and add listener
        createMultiPlayerButton(buttonStyle);

        // Create help icon and add listener
        createHelpIcon();
    }

    /**
     * Creates the single player button and adds it to the stage.
     *
     * @param buttonStyle The style for the button
     */
    private void createSinglePlayerButton(TextButton.TextButtonStyle buttonStyle) {
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
        stage.addActor(singlePlayerButton);
    }

    /**
     * Creates the multi-player button and adds it to the stage.
     *
     * @param buttonStyle The style for the button
     */
    private void createMultiPlayerButton(TextButton.TextButtonStyle buttonStyle) {
        TextButton multiPlayerButton = new TextButton("Multi Player", buttonStyle);
        multiPlayerButton.setSize(200, 50);
        multiPlayerButton.setPosition(Gdx.graphics.getWidth() / 2 - multiPlayerButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - 100);
        multiPlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MultiPlayerMenu(game));
            }
        });
        stage.addActor(multiPlayerButton);
    }

    /**
     * Creates the help icon and adds it to the stage.
     */
    private void createHelpIcon() {
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
        stage.addActor(helpIcon);
    }

    /**
     * Renders the screen.
     *
     * @param delta The time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin(); // Begin batch rendering
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw background
        float ribbonWidth = ribbonTexture.getWidth() / 2;
        float ribbonHeight = ribbonTexture.getHeight() / 2;
        batch.draw(ribbonTexture, Gdx.graphics.getWidth() / 2 - ribbonWidth / 2, Gdx.graphics.getHeight() - 150,
                ribbonWidth, ribbonHeight); // Draw ribbon
        batch.end(); // End batch rendering

        stage.act(); // Update stage
        stage.draw(); // Draw stage
    }

    /**
     * Called when the screen's size changes.
     *
     * @param width  The new width
     * @param height The new height
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true); // Update stage viewport
    }

    /**
     * Called when this screen is no longer the current screen for a Game.
     */
    @Override
    public void hide() {
        // Dispose of resources
        batch.dispose();
        backgroundTexture.dispose();
        ribbonTexture.dispose();
        stage.dispose();
        skin.dispose();
        font.dispose();
    }
}
