package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tile {
    /**
     * A tile is represented as a linked list with Side objects.
     */
    private List<Biome> sides = new LinkedList<Biome>();

    private float x;
    private float y;

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
            list.add(new Biome(0));
        }
        this.sides = list;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Returns a list of all sides of the tile.
     *
     * @return all sides.
     */
    public List<Biome> getSides() {
        return this.sides;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float n) {
        this.x = n;
    }

    public void setY(float n) {
        this.y = n;
    }
}
