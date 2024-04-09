package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
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

    public void drawBiome(Biome biome, PolygonSpriteBatch batch, float x, float y, float width, float height,
            float rotationAngle) {

        Texture texture = assets.manager.get(Assets.getAssetURL(biome.getBiomeID()), Texture.class);

        batch.draw(polygonDrawer.drawPolygon(texture, x, y), x, y);

        // batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1,
        // rotationAngle, 0, 0, texture.getWidth(),
        // texture.getHeight(), false, false);
    }
}
