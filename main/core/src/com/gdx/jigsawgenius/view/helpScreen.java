
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

public class helpScreen extends ScreenAdapter {

    private final JigsawGenius game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Image display;
    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private ArrayList<Image> tutorialImages;
    private int currentTutorial;
    private TextButton nextTutorialButton;
    private TextButton prevTutorialButton;
    private TextButton backButton;

    public helpScreen(JigsawGenius game) {
        this.game = game;
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        tutorialImages = new ArrayList<Image>();
        tutorialImages.add(new Image(new Texture("village.PNG")));
        tutorialImages.add(new Image(new Texture("plains.PNG")));
        tutorialImages.add(new Image(new Texture("field.PNG")));
        currentTutorial = 0;
        display = tutorialImages.get(currentTutorial);
        display.setSize(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);
        display.setPosition(120, 120);
       }

    private void setDisplay() {
        display = tutorialImages.get(currentTutorial);
        display.setSize(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);
        display.setPosition(120, 120);
        updateStage();
    }
    
    private void nextTutorial() {
        prevTutorialButton.setTouchable(Touchable.enabled);
        prevTutorialButton.setDisabled(false);
        currentTutorial++;
        setDisplay();
        if(currentTutorial >= tutorialImages.size() - 1){
            nextTutorialButton.setTouchable(Touchable.disabled);
            nextTutorialButton.setDisabled(true);
            System.out.println("Disabled next");
        }System.out.println(currentTutorial);
    }

    private void prevTutorial() {
        nextTutorialButton.setTouchable(Touchable.enabled);
        nextTutorialButton.setDisabled(false);
        currentTutorial--;
        setDisplay();
        if(currentTutorial == 0) {
            prevTutorialButton.setTouchable(Touchable.disabled);
            prevTutorialButton.setDisabled(true);
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        backgroundTexture = new Texture("background.png");

        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        font = new BitmapFont();

        skin.add("default-font", font);
        skin.add("backArrow", new Texture("arrow.png"));
        skin.add("rectangle", new Texture("rectangle.png"));

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.newDrawable("rectangle", Color.SKY);
        buttonStyle.down = skin.newDrawable("rectangle", Color.BLUE);
        buttonStyle.font = skin.getFont("default-font");
        buttonStyle.disabled = skin.newDrawable("rectangle", Color.GRAY);
        
        nextTutorialButton = new TextButton(">", buttonStyle);
        prevTutorialButton = new TextButton("<", buttonStyle);
        nextTutorialButton.setSize(50, 50);
        prevTutorialButton.setSize(50, 50);
        int screenCenter = Gdx.graphics.getWidth()/2;
        nextTutorialButton.setPosition(screenCenter + 50, 50 );
        prevTutorialButton.setPosition(screenCenter - 100, 50 );
        prevTutorialButton.setTouchable(Touchable.disabled);
        prevTutorialButton.setDisabled(true);
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
                game.setScreen(new MainMenuScreen(game));
            }
        });
        
        updateStage();
    }

    private void updateStage(){
        stage.clear();
        stage.addActor(display);
        stage.addActor(nextTutorialButton);
        stage.addActor(prevTutorialButton);
        stage.addActor(backButton);
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
