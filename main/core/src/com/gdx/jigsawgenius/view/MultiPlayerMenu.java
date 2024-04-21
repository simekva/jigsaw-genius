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
import com.gdx.jigsawgenius.model.FirebaseHost;

/**
 * The MultiPlayerMenu class represents the screen where players can host or join multiplayer games.
 * It allows players to enter a 4-digit code to join a game hosted by another player, or host their own game.
 * It extends the ScreenAdapter class from the LibGDX framework.
 */
public class MultiPlayerMenu extends ScreenAdapter {

    private Assets assets; // Reference to game assets
    private final JigsawGenius game; // Reference to the game instance
    private SpriteBatch batch; // Sprite batch for rendering
    private Texture backgroundTexture; // Texture for the background
    private Texture ribbonTexture; // Texture for the ribbon
    private Stage stage; // Stage for UI components
    private Skin skin; // Skin for UI components
    private BitmapFont font; // Font for UI components
    private TextField codeField; // Text field for entering game code
    private boolean isHost = false; // Flag indicating if the player is hosting a game
    private String pin; // Game pin for joining/hosting

    /**
     * Creates a MultiPlayerMenu object.
     *
     * @param game The game instance
     */
    public MultiPlayerMenu(JigsawGenius game) {
        this.game = game;
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        batch = new SpriteBatch(); // Initialize sprite batch
        assets = game.getAssets(); // Get game assets

        loadTextures(); // Load textures
        setupUI(); // Setup UI components
    }

    /**
     * Loads textures for the background and ribbon.
     */
    private void loadTextures() {
        backgroundTexture = new Texture("background.png"); // Load background texture
        ribbonTexture = new Texture("ribbon.png"); // Load ribbon texture
        ribbonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ribbonTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    /**
     * Sets up the user interface.
     */
    private void setupUI() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // Initialize stage
        Gdx.input.setInputProcessor(stage); // Set input processor to stage

        skin = new Skin(); // Initialize skin
        font = new BitmapFont(); // Initialize font

        skin.add("default-font", font); // Add font to skin
        skin.add("background", new Texture("rectangle.png")); // Add background texture to skin

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(); // Create button style
        buttonStyle.up = skin.getDrawable("background"); // Set up button appearance
        buttonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        buttonStyle.font = skin.getFont("default-font");

        createHostButton(buttonStyle); // Create host game button
        createCodeField(); // Create text field for entering game code
        createJoinButton(buttonStyle); // Create join game button
        createBackButton(buttonStyle); // Create back button
    }

    /**
     * Creates the host game button.
     *
     * @param buttonStyle The style for the button
     */
    private void createHostButton(TextButton.TextButtonStyle buttonStyle) {
        TextButton hostButton = new TextButton("Host game", buttonStyle); // Create host game button
        hostButton.setSize(200, 50); // Set button size
        hostButton.setPosition(Gdx.graphics.getWidth() / 2 - hostButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isHost = true; // Set as host
                FirebaseHost.sendData(); // Send data to Firebase
                pin = String.valueOf(FirebaseHost.getPin()); // Get game pin
                game.setScreen(new pinMenu(game, pin, isHost)); // Navigate to pin menu
            }
        });
        stage.addActor(hostButton); // Add button to stage
    }

    /**
     * Creates the text field for entering game code.
     */
    private void createCodeField() {
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(); // Create text field style
        textFieldStyle.font = skin.getFont("default-font"); // Set font for text field
        textFieldStyle.fontColor = Color.WHITE; // Set font color
        textFieldStyle.background = skin.getDrawable("background"); // Set background

        codeField = new TextField("", textFieldStyle); // Create text field
        codeField.setMessageText("Enter 4-digit code"); // Set placeholder text
        codeField.setSize(200, 50); // Set size
        codeField.setPosition(Gdx.graphics.getWidth() / 2 - codeField.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - 150); // Set position
        codeField.setAlignment(Align.center); // Set alignment

        codeField.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                codeField.setText(""); // Clear text on click
                codeField.getStyle().fontColor = Color.WHITE; // Reset font color
            }
        });
        stage.addActor(codeField); // Add text field to stage
    }

    /**
     * Creates the join game button.
     *
     * @param buttonStyle The style for the button
     */
    private void createJoinButton(TextButton.TextButtonStyle buttonStyle) {
        TextButton joinButton = new TextButton("Join game", buttonStyle); // Create join game button
        joinButton.setSize(200, 50); // Set button size
        joinButton.setPosition(Gdx.graphics.getWidth() / 2 - joinButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - 100); // Set position

        joinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (codeField.getText().matches("\\d{4}")) { // Check if entered code is valid
                    isHost = false; // Set as not host
                    pin = codeField.getText(); // Get game pin
                    game.setScreen(new MultiPlayerScreen(assets, game, pin, isHost)); // Navigate to multiplayer screen
                } else {
                    codeField.setText("Invalid code! Please try again"); // Display error message
                    codeField.getStyle().fontColor = Color.RED; // Set font color to red
                }
            }
        });
        stage.addActor(joinButton); // Add button to stage
    }

    /**
     * Creates the back button.
     *
     * @param buttonStyle The style for the button
     */
    private void createBackButton(TextButton.TextButtonStyle buttonStyle) {
        TextButton backButton = new TextButton("Go back", buttonStyle); // Create back button
        backButton.setSize(100, 100); // Set button size
        backButton.setPosition(50, Gdx.graphics.getHeight() - backButton.getHeight() - 50); // Set position
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game)); // Navigate to main menu screen
            }
        });
        stage.addActor(backButton); // Add button to stage
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
        float ribbonWidth = ribbonTexture.getWidth() / 2; // Calculate ribbon width
        float ribbonHeight = ribbonTexture.getHeight() / 2; // Calculate ribbon height
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
