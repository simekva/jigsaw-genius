package com.gdx.jigsawgenius.model;

import com.badlogic.gdx.math.MathUtils;

public abstract class Config {

    /**
     * A list of the legal terrain types in String form.
     */
    static String[] legalTerrainTypes = new String[] {
            "plains", "village", "field", "forest", "desert" };

    /**
     * Number of sides per Tile object.
     */
    public static final int SIDESCOUNT = 6;

    /**
     * 200 pixels.
     */
    public static final double TILE_WIDTH = 200.0;
    public static final double TILE_HEIGHT = MathUtils.sinDeg(60) * TILE_WIDTH;

    /**
     * Point multiplier to be used for points calcualation.
     */
    public static final int POINTMULTIPLIER = 100;

    public static void main(String[] args) {
        System.out.println(Config.TILE_HEIGHT);
    }
}
