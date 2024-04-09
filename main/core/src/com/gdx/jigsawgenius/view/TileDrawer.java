package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    BiomeDrawer biomeDrawer;
    int[] rotationAngles = { 0, 60, 120, 180, 240, 300 };

    public TileDrawer() {
        biomeDrawer = new BiomeDrawer();
    }

    // TODO: Calculate difference in x and y so that tiles don't render on top of
    // each other.
    public void drawTile(Tile tile, SpriteBatch batch, float x, float y, float width, float height) {
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            Biome biome = tile.getSides().get(i);
            this.biomeDrawer.drawBiome(biome, batch, x, y, width, height, rotationAngles[i]);
        }
    }
}
