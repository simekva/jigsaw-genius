package com.gdx.jigsawgenius.model;

import java.util.LinkedList;
import java.util.List;

public class TileManager {

    static final int maxHandSize = 15;

    public TileManager() {
        
    }

    /**
     * Returns a new set of Tiles. A set may consist of 1 to 15 tiles. 
     * @return List<Tile>
     */    
    public List<Tile> newSet(int size) {
        if (size>maxHandSize || size<1){throw new IllegalArgumentException("Invalid amount");}

        List<Tile> hand = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            Tile randomizedTile = randomTile();
            hand.add(randomizedTile);
        }
        return hand;
    }

    /**
     * Generates a Tile from 6 random sides. 
     * @return A new Tile
     */
    public Tile randomTile() {
        List<Side> randomizedSides = new LinkedList<Side>();

        for (int i = 0; i < Tile.sidesCount; i++) {
            int randomIndex = (int) Math.floor(Math.random()*Side.legalTerrainTypes.length);
            randomizedSides.add(
                new Side(Side.legalTerrainTypes[randomIndex])
            );
        }

        Tile randomTile = new Tile(randomizedSides);
        //System.out.println("[randomTile] Final ".concat(randomTile.toString()));
        return randomTile;
    }
}
