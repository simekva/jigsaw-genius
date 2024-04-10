package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    float hexagonSpacing = (float) Config.TILE_HEIGTH * 0.75f;
    float[][] offsets = new float[][] {
            { hexagonSpacing * 0.5f, (float) (Config.TILE_HEIGTH * 0.866f) }, // Top-right
            { -hexagonSpacing * 0.5f, (float) (Config.TILE_HEIGTH * 0.866f) }, // Top-left
            { -hexagonSpacing, 0 }, // Left
            { -hexagonSpacing * 0.5f, (float) (-Config.TILE_HEIGTH * 0.866f) }, // Bottom-left
            { hexagonSpacing * 0.5f, (float) (-Config.TILE_HEIGTH * 0.866f) }, // Bottom-right
            { hexagonSpacing, 0 } // Right
    };

    int[] angleOffset = { 120, 180, 240, 300, 0, 60 };

    BiomeDrawer biomeDrawer;

    public TileDrawer() {
        biomeDrawer = new BiomeDrawer();
    }

    public void drawTile(Tile tile, PolygonSpriteBatch batch, float x, float y) {
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            float xOffset = x + offsets[i][0];
            float yOffset = y + offsets[i][1];

            Biome biome = tile.getSides().get(i);

            biomeDrawer.drawBiome(biome, batch, xOffset, yOffset, angleOffset[i]);
        }
    }
}
