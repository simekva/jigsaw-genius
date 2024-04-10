package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;

public class BiomeDrawer {

    public BiomeDrawer() {
    }

    public void drawBiome(Assets assets, Biome biome, PolygonSpriteBatch batch, float x, float y,
            float rotationAngle) {

        Texture texture = assets.manager.get(Assets.getAssetURL(biome.getBiomeID()), Texture.class);
        Sprite sprite = new Sprite(texture);
        sprite.setRotation(rotationAngle);
        // batch.draw(polygonDrawer.drawPolygon(sprite, x, y), x, y);
        sprite.setPosition(x, y);
        // System.out.println("x: " + sprite.getX() + "y: " + sprite.getY());
        sprite.setSize((float) Config.TILE_WIDTH, (float) Config.TILE_HEIGHT);
        sprite.draw(batch);
        assets.manager.update();

        // batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1,
        // rotationAngle, 0, 0, texture.getWidth(),
        // texture.getHeight(), false, false);
    }
}
