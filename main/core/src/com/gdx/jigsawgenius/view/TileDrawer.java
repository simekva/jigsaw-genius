package com.gdx.jigsawgenius.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Tile;

public class TileDrawer {

    BiomeDrawer biomeDrawer;
    int[] rotationAngles = { 0, 60, 120, 180, 240, 300 };
    float triangleHeight;
    List<Double> xOffsets;
    List<Double> yOffsets;

    public TileDrawer() {
        biomeDrawer = new BiomeDrawer();
        xOffsets = new ArrayList<Double>();
        yOffsets = new ArrayList<Double>();
        this.calculateOffsets();
        System.out.println(this.getOffsets());
    }

    // TODO: Calculate difference in x and y so that tiles don't render on top of
    // each other.
    public void drawTile(Tile tile, PolygonSpriteBatch batch, float x, float y, float width, float height) {
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            Biome biome = tile.getSides().get(i);
            this.biomeDrawer.drawBiome(biome, batch, x, y, width, height, rotationAngles[i]);
        }
    }

    public void calculateOffsets() {
        xOffsets.add(0.0);
        xOffsets.add(Config.TILE_SIZE);
        xOffsets.add(Config.TILE_SIZE + Config.TILE_SIZE * 0.5);
        xOffsets.add(Config.TILE_SIZE);
        xOffsets.add(0.0);
        xOffsets.add(-Config.TILE_SIZE * 0.5);

        yOffsets.add(0.0);
        yOffsets.add(0.0);
        yOffsets.add(Math.cos(30) * Config.TILE_SIZE);
        yOffsets.add(Math.cos(30) * Config.TILE_SIZE * 2);
        yOffsets.add(Math.cos(30) * Config.TILE_SIZE * 2);
        yOffsets.add(Math.cos(30) * Config.TILE_SIZE);
    }

    public List<Double> getOffsets() {
        return this.yOffsets;
    }

    public static void main(String[] args) {
        TileDrawer drawer = new TileDrawer();
        System.out.println(drawer.getOffsets());
    }
}
