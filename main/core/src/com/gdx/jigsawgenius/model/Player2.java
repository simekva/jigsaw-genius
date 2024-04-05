package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player2 {

    private int score;
    private List<Tile> hand = new ArrayList<Tile>();
    public static int HANDSIZE = 15;

    public Player2() {
        for (int i = 0; i < Player2.HANDSIZE; i++) {
            this.hand.add(this.generateRandomTile());
        }
    }

    public int getTilesLeft() {
        return this.hand.size();
    }

    /**
     * Rotates the tile clockwise.
     */
    public void rotateTile() {
        // Rotates tile
        Tile topTile = this.getTopTile();
        List<Biome> tempSides = new LinkedList<Biome>();
        tempSides.add(topTile.getSides().get(Tile.SIDESCOUNT - 1));
        for (int i = 0; i < Tile.SIDESCOUNT - 1; i++) {
            tempSides.add(topTile.getSides().get(i));
        }
        this.setTopTile(new Tile(tempSides));
    }

    private Tile getTopTile() {
        return hand.get(hand.size() - 1);
    }

    private void setTopTile(Tile tile) {
        this.hand.remove(this.hand.size() - 1);
        this.hand.add(tile);
    }

    public Tile generateRandomTile() {
        Random random = new Random();
        List<Biome> list = new ArrayList<Biome>();
        for (int i = 0; i < 6; i++) {
            list.add(new Biome(random.nextInt(5)));
        }
        return new Tile(list);
    }

    public Tile popTile() {
        Tile removedTile = this.hand.get(this.getTilesLeft() - 1);
        this.hand.remove(this.getTilesLeft() - 1);
        return removedTile;
    }
}