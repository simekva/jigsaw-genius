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
     * X coordinate to draw in.
     */
    private float xCoord;
    /**
     * Y coordinate to draw in.
     */
    private float yCoord;

    /**
     * X coordinate in the tile manager.
     */
    private int x;

    /**
     * Y coordinate in the tile manager.
     */
    private int y;

    /**
     * Creates a tile object with a given list of tiles.
     *
     * @param list
     */
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

    /**
     * Retusn xCoord.
     *
     * @return xCoord.
     */
    public final float getXCoord() {
        return this.xCoord;
    }

    /**
     * Retusn yCoord.
     *
     * @return yCoord.
     */
    public final float getYCoord() {
        return this.yCoord;
    }

    /**
     * Sets xCoord.
     *
     * @param n
     */
    public final void setXCoord(final float n) {
        this.xCoord = n;
    }

    /**
     * Sets yCoord.
     *
     * @param n
     */
    public final void setYCoord(final float n) {
        this.yCoord = n;
    }

    /**
     * Sets x.
     *
     * @param n
     */
    public final void setX(final int n) {
        this.x = n;
    }

    /**
     * Sets y.
     *
     * @param n
     */
    public final void setY(final int n) {
        this.y = n;
    }

    /**
     * Gets x.
     *
     * @return x
     */
    public final int getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return y
     */
    public final int getY() {
        return this.y;
    }
}
