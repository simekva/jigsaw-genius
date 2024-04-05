package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player2 {

    /**
     * Score of the player.
     */
    private int score;

    /**
     * Hand of the player.
     */
    private List<Tile> hand = new ArrayList<Tile>();

    /**
     * Handsize.
     */
    static final int HANDSIZE = 15;

    /**
     * Initializes a Player-object by giving it a hand of random tiles.
     */
    public Player2() {
        for (int i = 0; i < Player2.HANDSIZE; i++) {
            this.hand.add(this.generateRandomTile());
        }
    }

    /**
     * Returns number of tiles left in the hand.
     *
     * @return number of tiles left.
     */
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

    private void setTopTile(final Tile tile) {
        this.hand.remove(this.hand.size() - 1);
        this.hand.add(tile);
    }

    /**
     * Generates a random tile.
     *
     * @return randomly generated tile.
     */
    public Tile generateRandomTile() {
        Random random = new Random();
        List<Biome> list = new ArrayList<Biome>();
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            list.add(new Biome(random.nextInt(Biome.getLegalTerrainTypes().length)));
        }
        return new Tile(list);
    }

    /**
     * Removes the top tile from the hand and returns it.
     *
     * @return the removed tile from the top of the hand.
     */
    public Tile popTile() {
        Tile removedTile = this.hand.get(this.getTilesLeft() - 1);
        this.hand.remove(this.getTilesLeft() - 1);
        return removedTile;
    }

    /**
     * Increase the players score by the number given.
     *
     * @param number
     */
    public void increaseScore(final int number) {
        this.score += number;
    }
}
