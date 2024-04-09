package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
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
    public static final int SIDESCOUNT = 6;

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
     * Creates a tile object with only plains sides.
     */
    public Tile() {
        List<Biome> list = new ArrayList<Biome>();
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            list.add(new Biome(0));
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

    /**
     * toString for the tile.
     *
     * @return string-representation of the tile.
     */
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
