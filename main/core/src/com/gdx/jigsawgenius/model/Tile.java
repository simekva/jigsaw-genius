package com.gdx.jigsawgenius.model;

import java.util.LinkedList;
import java.util.List;

public class Tile {
    /**
     * A tile is represented as a linked list with Side objects.
     */
    private List<Side> sides = new LinkedList<Side>();
    /**
     * Number of sides.
     */
    static final int SIDESCOUNT = 6;

    /**
     * Creates a tile object with a given list of tiles.
     *
     * @param list List of tiles.
     */
    public Tile(final List<Side> list) {
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
    public List<Side> getSides() {
        return this.sides;
    }

    /**
     * Rotates the tile clockwise.
     */
    public void rotateTile() {
        // Rotates tile
        List<Side> tempSides = new LinkedList<Side>();
        tempSides.add(sides.get(SIDESCOUNT - 1));
        for (int i = 0; i < SIDESCOUNT - 2; i++) {
            tempSides.add(sides.get(i));
        }

        this.sides = tempSides;
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
