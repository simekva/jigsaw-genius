package com.gdx.jigsawgenius;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
    private static final AssetManager assetManager = new AssetManager();
    private static Drawable buttonUp;
    private static Drawable buttonDown;
    private static BitmapFont font;

    public static void load() {
        assetManager.load("background.jpg", Texture.class);
        // Load other assets here
        // Load textures for button up and down states
        assetManager.load("button.png", Texture.class);
        assetManager.load("button.png", Texture.class);
        // Load font
    }

    public static boolean update() {
        return assetManager.update();
    }

    public static void dispose() {
        assetManager.dispose();
    }

    public static Texture getBackgroundTexture() {
        return assetManager.get("background.jpg", Texture.class);
    }

    public static Button.ButtonStyle getDefaultButtonStyle() {
        if (buttonUp == null || buttonDown == null) {
            // Load button textures if not already loaded
            buttonUp = new TextureRegionDrawable(assetManager.get("button.png", Texture.class));
            buttonDown = new TextureRegionDrawable(assetManager.get("button.png", Texture.class));
        }
        Button.ButtonStyle style = new Button.ButtonStyle(buttonUp, buttonDown, null);
        return style;
    }

   
}
