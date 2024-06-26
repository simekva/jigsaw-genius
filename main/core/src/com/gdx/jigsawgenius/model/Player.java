package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player {

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
    public Player() {
        for (int i = 0; i < Player.HANDSIZE; i++) {
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
        tempSides.add(topTile.getSides().get(Config.SIDESCOUNT - 1));
        for (int i = 0; i < Config.SIDESCOUNT - 1; i++) {
            tempSides.add(topTile.getSides().get(i));
        }
        this.setTopTile(new Tile(tempSides));
    }

    /**
     * Returns the top tile of the hand.
     *
     * @return top tile.
     */
    public Tile getTopTile() {
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
        for (int i = 0; i < Config.SIDESCOUNT; i++) {
            list.add(new Biome(random
                    .nextInt(Assets.getNumberOfAssets())));
        }
        return new Tile(list);
    }

    /**
     * Removes the top tile of the hand.
     */
    public void removeTopTile() {
        this.hand.remove(this.getTilesLeft() - 1);
    }

    /**
     * Increase the players score by the number given.
     *
     * @param number
     */
    public void increaseScore(final int number) {
        this.score += number;
    }

    /**
     * Gets score.
     *
     * @return score
     */
    public int getScore() {
        return this.score;
    }
}
