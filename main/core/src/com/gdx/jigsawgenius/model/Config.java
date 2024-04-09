package com.gdx.jigsawgenius.model;

public abstract class Config {

    /**
     * A list of the legal terrain types in String form.
     */
    static String[] legalTerrainTypes = new String[] {
            "plains", "village", "field", "forest", "desert" };

    /**
     * Number of sides per Tile object.
     */
    static final int SIDESCOUNT = 6;

    /**
     * Point multiplier to be used for points calcualation.
     */
    public static final int POINTMULTIPLIER = 100;
}
