package com.gdx.jigsawgenius;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MultiPlayerScreen extends ScreenAdapter {
    private Texture backgroundTexture;

    @Override
    public void show() {
        backgroundTexture = new Texture("multi.jpg");
    }

    @Override
    public void render(float delta) {
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}

