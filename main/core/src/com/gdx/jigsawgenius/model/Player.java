package com.gdx.jigsawgenius.model;

import java.util.List;

public class Player {

    private int score;
    private List<Tile> hand;
    private TileManager tileManager;

    public Player() {
        score = 0;
        tileManager = new TileManager();
        hand = tileManager.newSet(15);
    }

    public Tile getTile(int index) {
        if (checkHand()) {
            throw new IllegalStateException("Hand is empty");
        }
        return hand.get(index);
    }

    public Tile popTop() {
        if (checkHand()) {
            throw new IllegalStateException("Hand is empty");
        }
        return hand.removeFirst();
    }

    public int getTilesLeft() {
        if (checkHand()) {
            return 0;
        }
        return hand.size();
    }

    public boolean checkHand() {
        return hand.isEmpty();
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Tile> getHand() {
        return hand;
    }

    public void setHand(List<Tile> tiles) {
        hand = tiles;
    }
}
