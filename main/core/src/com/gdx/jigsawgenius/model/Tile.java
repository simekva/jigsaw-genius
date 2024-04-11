package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tile {
    /**
     * A tile is represented as a linked list with Side objects.
     */
    private List<Biome> sides = new LinkedList<Biome>();

    private float xCoord;
    private float yCoord;

    private int x;
    private int y;

    /**
     * Creates a tile object with a given list of tiles.
     *
     * @param list List of tiles.
     */
    public Tile(final List<Biome> list, int xCoord, int yCoord) {
        if (list.size() != Config.SIDESCOUNT) {
            throw new IllegalArgumentException("Tile has to have 6 sides");
        }
        this.sides = list;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
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
            list.add(new Biome(0));
        }
        this.sides = list;
        this.xCoord = 0;
        this.yCoord = 0;
    }

    /**
     * Returns a list of all sides of the tile.
     *
     * @return all sides.
     */
    public List<Biome> getSides() {
        return this.sides;
    }

    public float getXCoord() {
        return this.xCoord;
    }

    public float getYCoord() {
        return this.yCoord;
    }

    public void setXCoord(float n) {
        this.xCoord = n;
    }

    public void setYCoord(float n) {
        this.yCoord = n;
    }

    public void setX(int n) {
        this.x = n;
    }

    public void setY(int n) {
        this.y = n;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
