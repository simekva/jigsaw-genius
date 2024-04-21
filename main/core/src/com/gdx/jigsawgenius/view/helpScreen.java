package com.gdx.jigsawgenius.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.jigsawgenius.JigsawGenius;


/**
 * The helpScreen class represents the screen that displays tutorial slides to help the player
 * understand the game. It allows navigation between tutorial slides and provides a button to
 * return to the main menu.
 * It extends the ScreenAdapter class from the LibGDX framework.
 */
public class helpScreen extends ScreenAdapter {

    private final JigsawGenius game; // Reference to the game instance
    private SpriteBatch batch; // Sprite batch for rendering
    private Texture backgroundTexture; // Texture for the background
    private Image display; // Image displaying the current tutorial slide
    private Stage stage; // Stage for UI components
    private Skin skin; // Skin for UI components
    private BitmapFont font; // Font for UI components
    private ArrayList<Image> tutorialImages; // List of tutorial slide images
    private int currentTutorial; // Index of the current tutorial slide
    private TextButton nextTutorialButton; // Button to navigate to the next tutorial slide
    private TextButton prevTutorialButton; // Button to navigate to the previous tutorial slide
    private TextButton backButton; // Button to return to the main menu

    /**
     * Creates a helpScreen object.
     *
     * @param game The game instance
     */
    public helpScreen(JigsawGenius game) {
        this.game = game;
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // Initialize stage
        tutorialImages = new ArrayList<Image>(); // Initialize list of tutorial slide images
        loadTutorialImages(); // Load tutorial slide images
        currentTutorial = 0; // Set initial tutorial slide index
        setupDisplay(); // Setup display for current tutorial slide
    }

    /**
     * Loads the tutorial slide images.
     */
    private void loadTutorialImages() {
        // Add images from assets to be displayed
        tutorialImages.add(new Image(new Texture("tutorialSlide1.png")));
        tutorialImages.add(new Image(new Texture("tutorialSlide2.png")));
        tutorialImages.add(new Image(new Texture("tutorialSlide3.png")));
        tutorialImages.add(new Image(new Texture("tutorialSlide4.png")));
    }

    /**
     * Sets up the display for the current tutorial slide.
     */
    private void setupDisplay() {
        display = tutorialImages.get(currentTutorial);
        display.setSize(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);
        display.setPosition(120, 120);
    }

    /**
     * Sets the display to show the current tutorial slide.
     */
    private void setDisplay() {
        display = tutorialImages.get(currentTutorial);
        display.setSize(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);
        display.setPosition(120, 120);
        updateStage();
    }

    /**
     * Navigates to the next tutorial slide.
     */
    private void nextTutorial() {
        prevTutorialButton.setTouchable(Touchable.enabled);
        prevTutorialButton.setDisabled(false);
        currentTutorial++;
        setDisplay();
        if (currentTutorial >= tutorialImages.size() - 1) {
            nextTutorialButton.setTouchable(Touchable.disabled);
            nextTutorialButton.setDisabled(true);
        }
    }

    /**
     * Navigates to the previous tutorial slide.
     */
    private void prevTutorial() {
        nextTutorialButton.setTouchable(Touchable.enabled);
        nextTutorialButton.setDisabled(false);
        currentTutorial--;
        setDisplay();
        if (currentTutorial == 0) {
            prevTutorialButton.setTouchable(Touchable.disabled);
            prevTutorialButton.setDisabled(true);
        }
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        batch = new SpriteBatch(); // Initialize sprite batch
        backgroundTexture = new Texture("background.png"); // Load background texture
        Gdx.input.setInputProcessor(stage); // Set input processor to stage
        loadSkinAndFont(); // Load skin and font for UI components
        setupButtons(); // Setup navigation buttons
        updateStage(); // Update stage with UI components
    }

    /**
     * Loads the skin and font for UI components.
     */
    private void loadSkinAndFont() {
        skin = new Skin(); // Initialize skin
        font = new BitmapFont(); // Initialize font
        skin.add("default-font", font); // Add font to skin
        skin.add("backArrow", new Texture("arrow.png")); // Add arrow texture to skin
        skin.add("rectangle", new Texture("rectangle.png")); // Add rectangle texture to skin
    }

    /**
     * Sets up the navigation buttons.
     */
    private void setupButtons() {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(); // Create button style
        buttonStyle.up = skin.newDrawable("rectangle", Color.SKY); // Set up button appearance
        buttonStyle.down = skin.newDrawable("rectangle", Color.BLUE);
        buttonStyle.font = skin.getFont("default-font");
        buttonStyle.disabled = skin.newDrawable("rectangle", Color.GRAY);

        nextTutorialButton = new TextButton(">", buttonStyle); // Create next tutorial button
        prevTutorialButton = new TextButton("<", buttonStyle); // Create previous tutorial button
        nextTutorialButton.setSize(50, 50); // Set button size
        prevTutorialButton.setSize(50, 50);
        int screenCenter = Gdx.graphics.getWidth() / 2; // Calculate screen center

        // Set button positions
        nextTutorialButton.setPosition(screenCenter + 50, 50);
        prevTutorialButton.setPosition(screenCenter - 100, 50);
        prevTutorialButton.setTouchable(Touchable.disabled); // Disable previous button initially
        prevTutorialButton.setDisabled(true);

        // Add listeners to buttons
        nextTutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextTutorial();
            }
        });

        prevTutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prevTutorial();
            }
        });

        // Create back button
        TextButton.TextButtonStyle buttonStyle2 = new TextButton.TextButtonStyle();
        buttonStyle2.up = skin.getDrawable("rectangle");
        buttonStyle2.down = skin.newDrawable("rectangle", Color.DARK_GRAY);
        buttonStyle2.font = skin.getFont("default-font");
        backButton = new TextButton("Go back", buttonStyle2);
        backButton.setSize(100, 100);
        backButton.setPosition(50, Gdx.graphics.getHeight() - backButton.getHeight() - 50);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game)); // Return to main menu
            }
        });
    }

    /**
     * Updates the stage with UI components.
     */
    private void updateStage() {
        stage.clear(); // Clear stage
        stage.addActor(display); // Add display image
        stage.addActor(nextTutorialButton); // Add next button
        stage.addActor(prevTutorialButton); // Add previous button
        stage.addActor(backButton); // Add back button
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
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();
    }

    /**
     * Called when the screen's size changes.
     *
     * @param width  The new width
     * @param height The new height
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        font.dispose();
    }
}

