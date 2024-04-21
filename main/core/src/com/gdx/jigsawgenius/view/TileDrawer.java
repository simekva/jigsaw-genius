package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    /**
     * Angle offsets to draw. Every triangle is rotated 60 degrees with an
     * offset of 30.
     * Starting with 150 to first draw the triangle to the top right.
     */
    private int[] angleOffset = { 150, 90, 30, 330, 270, 210 };

    /**
     * Drawer that draws biomes individually.
     */
    private BiomeDrawer biomeDrawer;

    /**
     * Initalise the biomeDrawer.
     */
    public TileDrawer() {
        biomeDrawer = new BiomeDrawer();
    }

    /**
     * Draws a tile in given location.
     *
     * @param assets
     * @param tile   tile to draw.
     * @param batch  batch to draw it in.
     * @param x      x coordinate for center of tile.
     * @param y      y coordinate for center of tile.
     * @param scale  scale.
     */
    public final void drawTile(final Assets assets, final Tile tile,
            final SpriteBatch batch, final float x,
            final float y, final float scale) {

        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            Biome biome = tile.getSides().get(i);
            biomeDrawer.drawBiome(
                    assets, biome, batch, x, y, angleOffset[i], scale);
        }
    }
}
