package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    float hexagonSpacing = (float) (Config.TILE_WIDTH);

    float[][] offsets = new float[][] {
            { (float) (Config.TILE_WIDTH / 2.7), (float) (Config.TILE_HEIGHT * 0.75) }, // Top-right
            { (float) ((float) Config.TILE_WIDTH / 2.7), (float) (Config.TILE_HEIGHT / 4) }, // Bottom-right
            { 0, 0 }, // Bottom
            { (float) (-Config.TILE_WIDTH * 0.37), (float) (Config.TILE_HEIGHT / 4) }, // Bottom-left
            { (float) ((float) -Config.TILE_WIDTH * 0.37), (float) ((float) (Config.TILE_HEIGHT) * 0.75) }, // Top-left
            { 0, (float) (Config.TILE_HEIGHT) }, // Top
    };

    int[] angleOffset = { 120, 60, 0, 300, 240, 180 };

    BiomeDrawer biomeDrawer;

    public TileDrawer() {
        biomeDrawer = new BiomeDrawer();
    }

    public void drawTile(Assets assets, Tile tile, PolygonSpriteBatch batch, float x, float y) {
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            float xOffset = x + offsets[i][0]; // Remove the cast to float
            float yOffset = y + offsets[i][1]; // Remove the cast to float

            Biome biome = tile.getSides().get(i);

            float spriteWidth = (float) Config.TILE_WIDTH; // Get the width of the sprite
            float spriteHeight = (float) Config.TILE_HEIGHT; // Get the height of the sprite

            // Adjust the offset to account for the center origin of the sprite
            xOffset -= spriteWidth / 2f;
            yOffset -= spriteHeight / 2f;

            biomeDrawer.drawBiome(assets, biome, batch, xOffset, yOffset, angleOffset[i]);
        }
    }
}
