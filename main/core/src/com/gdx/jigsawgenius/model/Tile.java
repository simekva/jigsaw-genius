package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.utils.Null;

public class Tile {
    /**
     * A tile is represented as a linked list with Side objects.
     */
    private List<Biome> sides = new LinkedList<Biome>();

    private int x;
    private int y;

    /**
     * Creates a tile object with a given list of tiles.
     *
     * @param list List of tiles.
     */
    public Tile(final List<Biome> list, int x, int y) {
        if (list.size() != Config.SIDESCOUNT) {
            throw new IllegalArgumentException("Tile has to have 6 sides");
        }
        this.sides = list;
        this.x = x;
        this.y = y;
    }

    public Tile(final List<Biome> list) {
        if (list.size() != Config.SIDESCOUNT) {
            throw new IllegalArgumentException("Tile has to have 6 sides");
        }
        this.sides = list;
    }

    /**
     * Creates a tile object with only plains sides.
     */
    public Tile() {
        List<Biome> list = new ArrayList<Biome>();
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            list.add(new Biome(2));
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int n) {
        this.x = n;
    }

    public void setY(int n) {
        this.y = n;
    }

    /**
     * toString for the tile.
     *
     * @return string-representation of the tile.
     */
    public String toString() {
        String returnString = "";
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            returnString += Config.legalTerrainTypes[this.getSides()
                    .get(i).getBiomeID()];
            // returnString += this.getSides().get(i).getTerrainType();
            if (i != Config.SIDESCOUNT - 1) {
                returnString += ", ";
            }
        }
        return returnString;
    }
}
