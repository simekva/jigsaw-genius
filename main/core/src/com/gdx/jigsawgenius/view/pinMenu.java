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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.jigsawgenius.JigsawGenius;
import com.gdx.jigsawgenius.model.Assets;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;


/**
 * The pinMenu class represents the screen where players receive the PIN code to join a multiplayer game.
 * It allows the host to start the game and provides the joining player with the PIN code.
 * It extends the ScreenAdapter class from the LibGDX framework.
 */
public class pinMenu extends ScreenAdapter {

    private Assets assets; // Reference to game assets
    private final JigsawGenius game; // Reference to the game instance
    private SpriteBatch batch; // Sprite batch for rendering
    private Texture backgroundTexture; // Texture for the background
    private Stage stage; // Stage for UI components
    private Skin skin; // Skin for UI components

    private TextButton hostButton; // Button for starting the game
    private TextButton backButton; // Button for going back to the previous screen
    private Label codeLabel; // Label for displaying the PIN code

    private String pin; // Game PIN code
    private boolean isHost; // Flag indicating if the player is hosting the game

    /**
     * Creates a pinMenu object.
     *
     * @param game   The game instance
     * @param pin    The PIN code
     * @param isHost Indicates if the player is hosting the game
     */
    public pinMenu(JigsawGenius game, String pin, boolean isHost) {
        this.game = game;
        this.pin = pin;
        this.isHost = isHost;
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        batch = new SpriteBatch(); // Initialize sprite batch
        backgroundTexture = new Texture("background.png"); // Load background texture
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // Initialize stage
        Gdx.input.setInputProcessor(stage); // Set input processor to stage

        skin = new Skin(); // Initialize skin
        skin.add("default-font", new BitmapFont()); // Add default font to skin
        skin.add("background", new Texture("rectangle.png")); // Add background texture to skin

        createUIElements(); // Create UI elements
    }

    /**
     * Creates UI elements.
     */
    private void createUIElements() {
        TextButton.TextButtonStyle buttonStyle = createButtonStyle(); // Create button style
        hostButton = createHostButton(buttonStyle); // Create host button
        codeLabel = createCodeLabel(); // Create label to display PIN code
        backButton = createBackButton(buttonStyle); // Create back button

        // Add UI elements to the stage
        stage.addActor(hostButton);
        stage.addActor(codeLabel);
        stage.addActor(backButton);
    }

    /**
     * Creates the button style.
     *
     * @return The button style
     */
    private TextButton.TextButtonStyle createButtonStyle() {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(); // Create button style
        buttonStyle.up = skin.getDrawable("background"); // Set up button appearance
        buttonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        buttonStyle.font = skin.getFont("default-font");
        return buttonStyle;
    }

    /**
     * Creates the host button.
     *
     * @param buttonStyle The style for the button
     * @return The host button
     */
    private TextButton createHostButton(TextButton.TextButtonStyle buttonStyle) {
        assets = game.getAssets(); // Get game assets
        TextButton hostButton = new TextButton("Start game", buttonStyle); // Create host button
        hostButton.setSize(200, 50); // Set button size
        hostButton.setPosition(Gdx.graphics.getWidth() / 2 - hostButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2); // Set position
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Start the MultiPlayerScreen and pass the pin and isHost variables
                game.setScreen(new MultiPlayerScreen(assets, game, pin, isHost));
            }
        });
        return hostButton;
    }

    /**
     * Creates the label to display the PIN code.
     *
     * @return The label to display the PIN code
     */
    private Label createCodeLabel() {
        // Set background for label
        Texture backgroundTexture = new Texture("pinBackground.png");
        NinePatch backgroundPatch = new NinePatch(backgroundTexture, 12, 12, 12, 12);
        NinePatchDrawable backgroundDrawable = new NinePatchDrawable(backgroundPatch);

        Label.LabelStyle labelStyle = new Label.LabelStyle(); // Create label style
        labelStyle.font = skin.getFont("default-font"); // Set font for label
        labelStyle.fontColor = Color.BLACK; // Set font color
        labelStyle.background = backgroundDrawable; // Set background

        // Create label with PIN code
        Label codeLabel = new Label("Tell the joining player to \n enter this 4-digit code: " + pin, labelStyle);
        codeLabel.setSize(220, 100); // Set label size
        codeLabel.setPosition(Gdx.graphics.getWidth() / 2 - codeLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - 150); // Set position
        codeLabel.setAlignment(Align.center); // Set alignment
        return codeLabel;
    }

    /**
     * Creates the back button.
     *
     * @param buttonStyle The style for the button
     * @return The back button
     */
    private TextButton createBackButton(TextButton.TextButtonStyle buttonStyle) {
        TextButton backButton = new TextButton("Go back", buttonStyle); // Create back button
        backButton.setSize(100, 120); // Set button size
        backButton.setPosition(50, Gdx.graphics.getHeight() - backButton.getHeight() - 50); // Set position
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MultiPlayerMenu(game)); // Navigate to MultiPlayerMenu screen
            }
        });
        return backButton;
    }

    /**
     * Renders the screen.
     *
     * @param delta The time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Set clear color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen

        batch.begin(); // Begin batch rendering
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw background
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
        stage.dispose();
        skin.dispose();
    }
}
