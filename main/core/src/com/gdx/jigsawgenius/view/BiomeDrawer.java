package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;

public class BiomeDrawer {

    public BiomeDrawer() {
    }

    public void drawBiome(Assets assets, Biome biome, SpriteBatch batch, float x, float y,
            float rotationAngle) {

        Texture texture = assets.manager.get(Assets.getAssetURL(biome.getBiomeID()), Texture.class);
        Sprite sprite = new Sprite(texture);

        // Set rotation angle
        sprite.setRotation(rotationAngle);

        // Set position
        sprite.setPosition(x - Assets.pieceWidth / 2, y - Assets.pieceHeight);

        sprite.setOrigin(Assets.pieceWidth / 2, Assets.pieceHeight);

        sprite.setSize(Assets.pieceWidth, Assets.pieceHeight);

        // Draw the sprite
        sprite.draw(batch);
    }
}
