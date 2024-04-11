package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;

public class EmptyTileDrawer {

    int[] angleOffset = { 150, 90, 30, 330, 270, 210 };
    BiomeDrawer biomeDrawer;

    public EmptyTileDrawer() {
        biomeDrawer = new BiomeDrawer();
    }

    public void drawTile(Assets assets, SpriteBatch batch, float x, float y) {
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            biomeDrawer.drawBiome(assets, new Biome(Assets.getNumberOfAssets() - 1), batch, x, y, angleOffset[i]);
        }
    }
}
