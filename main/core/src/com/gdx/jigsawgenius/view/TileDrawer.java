package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    int[] angleOffset = { 150, 90, 30, 330, 270, 210 };
    BiomeDrawer biomeDrawer;

    public TileDrawer() {
        biomeDrawer = new BiomeDrawer();
    }

    public void drawTile(Assets assets, Tile tile, SpriteBatch batch, float x, float y) {
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            Biome biome = tile.getSides().get(i);
            biomeDrawer.drawBiome(assets, biome, batch, x, y, angleOffset[i]);
        }
    }
}
