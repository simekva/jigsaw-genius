package com.gdx.jigsawgenius.model;

import java.util.LinkedList;
import java.util.List;

public class Tile {
    /**
     * A tile is represented as a linked list with Side objects.
     */
    private List<Biome> sides = new LinkedList<Biome>();
    /**
     * Number of sides.
     */
    static final int SIDESCOUNT = 6;

    /**
     * Creates a tile object with a given list of tiles.
     *
     * @param list List of tiles.
     */
    public Tile(final List<Biome> list) {
        if (list.size() != SIDESCOUNT) {
            throw new IllegalArgumentException("Tile has to have 6 sides");
        }
        this.sides = list;
    }

    /**
     * Returns a list of all sides of the tile.
     *
     * @return all sides.
     */
    public List<Biome> getSides() {
        return this.sides;
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            returnString += this.getSides().get(i).getTerrainType();
            if (i != Tile.SIDESCOUNT - 1) {
                returnString += ", ";
            }
        }
        return returnString;
    }
}
