package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    float[][] offsets = new float[][] {
            { (float) (Config.TILE_WIDTH * 0.25), (float) (Config.TILE_HEIGHT * 0.44) }, // Top-right
            { (float) (Config.TILE_WIDTH * 0.46), 0 }, // Right
            { (float) (Config.TILE_WIDTH * 0.25), (float) (-Config.TILE_HEIGHT * 0.445) }, // Bottom-right
            { (float) (-Config.TILE_WIDTH * 0.175), (float) (-Config.TILE_HEIGHT * 0.445) }, // Bottom-left
            { (float) (-Config.TILE_WIDTH * 0.4), 0 }, // Left
            { (float) (-Config.TILE_WIDTH * 0.175), (float) (Config.TILE_HEIGHT * 0.44) }, // Top-left
    };

    int[] angleOffset = { 150, 90, 30, 330, 270, 210 };

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
