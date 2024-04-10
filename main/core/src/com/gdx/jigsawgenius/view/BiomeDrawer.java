package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;

public class BiomeDrawer {

    Assets assets;
    PolygonDrawer polygonDrawer;

    public BiomeDrawer() {
        assets = new Assets();
        assets.manager.finishLoading();
        polygonDrawer = new PolygonDrawer();
    }

    public void drawBiome(Biome biome, PolygonSpriteBatch batch, float x, float y,
            float rotationAngle) {

        Texture texture = assets.manager.get(Assets.getAssetURL(biome.getBiomeID()), Texture.class);
        Sprite sprite = new Sprite(texture);
        sprite.setRotation(rotationAngle);
        // batch.draw(polygonDrawer.drawPolygon(sprite, x, y), x, y);
        sprite.setPosition(x, y);
        sprite.draw(batch);

        // batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1,
        // rotationAngle, 0, 0, texture.getWidth(),
        // texture.getHeight(), false, false);
    }
}
