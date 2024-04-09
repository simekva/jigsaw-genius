package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;

public class BiomeDrawer {

    Assets assets;
    Biome biome;

    public BiomeDrawer(Biome biome) {
        assets = new Assets();
        assets.manager.finishLoading();
        this.biome = biome;
    }

    public void drawBiome(SpriteBatch batch, float x, float y, float height, float width) {
        batch.draw(assets.manager.get(Assets.getAssetURL(this.biome.getBiomeID()), Texture.class), x, y);
    }
}
