package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;

public class BiomeDrawer {

    /**
     * Draws a biome in specified x, y with rotation.
     *
     * @param assets
     * @param biome         biome to draw.
     * @param batch
     * @param x             x coordinate to draw it in.
     * @param y             y coordinate to draw it in.
     * @param rotationAngle rotation angle in degrees.
     * @param scale         size.
     */
    public final void drawBiome(final Assets assets, final Biome biome,
            final SpriteBatch batch, final float x, final float y,
            final float rotationAngle, final float scale) {

        Texture texture = assets.manager.get(
                Assets.getAssetURL(biome.getBiomeID()), Texture.class);
        Sprite sprite = new Sprite(texture);

        // Set rotation angle
        sprite.setRotation(rotationAngle);

        // Set position
        sprite.setPosition(x - Assets.pieceWidth / 2, y - Assets.pieceHeight);

        sprite.setOrigin(Assets.pieceWidth / 2, Assets.pieceHeight);

        sprite.setSize(Assets.pieceWidth, Assets.pieceHeight);

        sprite.setScale(scale);

        // Draw the sprite
        sprite.draw(batch);
    }
}
