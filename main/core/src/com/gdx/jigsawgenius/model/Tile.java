package com.gdx.jigsawgenius.model;

import java.util.LinkedList;
import java.util.List;

public class Tile {
    private List<Side> sides = new LinkedList<Side>();
    static final int sidesCount = 6;

    public Tile(List<Side> list) {
        // Tile has to have a size of 6
        if (list.size() != sidesCount) {
            throw new IllegalArgumentException("Tile has to have 6 sides");
        }
        this.sides = list;
    }

    public List<Side> getSides() {
        return this.sides;
    }
 
    public void rotateTile() {
        //Rotates tile
        List<Side> tempSides = new LinkedList<Side>();
        tempSides.add(sides.get(5));
        tempSides.add(sides.get(0));
        tempSides.add(sides.get(1));
        tempSides.add(sides.get(2));
        tempSides.add(sides.get(3));
        tempSides.add(sides.get(4));
        this.sides = tempSides;
    }

    @Override
    public String toString() {
        String returnString = "Tile: [";
        for (int i = 0; i < this.sides.size(); i++) {
            returnString += sides.get(i).getTerrainType();
            if (i != this.sides.size() - 1) {
                returnString += ", ";
            }
        }
        returnString += "]";
        return returnString;
    }
}
