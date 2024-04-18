package com.gdx.jigsawgenius.controller;

import com.gdx.jigsawgenius.model.TileManager;

import java.util.ArrayList;
import java.util.List;

import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Config;
import com.gdx.jigsawgenius.model.Player;
import com.gdx.jigsawgenius.model.Tile;

public class GameLogicController {

    /**
     * Board object.
     */
    private TileManager board;

    /**
     * Player 1 object.
     */

    private List<Player> players;

    // private Player player1;

    /**
     * Player 2 object.
     */
    // private Player player2;

    /**
     * Number to know who'se turn it is.
     */
    private int turn;

    /**
     * Creates a board with a pure plains tile in the middle, and
     * initializes the hand.
     */
    public GameLogicController(int numberOfPlayers) {
        board = new TileManager(1, 1);
        board.placeTile(new Tile(), 0, 0);
        if (numberOfPlayers != 1 && numberOfPlayers != 2) {
            throw new IllegalArgumentException("Couldn't create game controller with " + numberOfPlayers + "players.");
        }
        players = new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }

        // player1 = new Player();
        // player2 = new Player();
        turn = 1;
    }

    public TileManager getBoard() {
        return this.board;
    }

    public Player getPlayer(int n) {
        return this.players.get(n - 1);
    }

    /**
     * Places a tile in the given coordinates.
     *
     * @param x
     * @param y
     */
    // public void placeTile(final int x, final int y) {
    // boolean placed = false;
    // if (turn == 1 && !placed) {
    // board.placeTile(getPlayer(1).getTopTile(), x, y);
    // getPlayer(1).removeTopTile();
    // turn = 2;
    // getPlayer(1).increaseScore(board.numberOfMatches(x, y)
    // * Config.POINTMULTIPLIER);
    // System.out.println("Player 1 placed tile on: " + x + ", "
    // + y + ", and scored"
    // + board.numberOfMatches(x, y)
    // * Config.POINTMULTIPLIER
    // + " points.");
    // placed = true;
    // } else if (turn == 2 && !placed) {
    // board.placeTile(getPlayer(2).getTopTile(), x, y);
    // getPlayer(2).removeTopTile();
    // turn = 1;
    // System.out.println("Player 2 placed tile on: " + x + ", " + y
    // + ", and scored"
    // + board.numberOfMatches(x, y)
    // * Config.POINTMULTIPLIER
    // + " points.");
    // getPlayer(2).increaseScore(board.numberOfMatches(x, y)
    // * Config.POINTMULTIPLIER);
    // placed = true;
    // }
    // }

    public void placeTile(final int x, final int y) {
        boolean placed = false;
        board.placeTile(getPlayer(1).getTopTile(), x, y);
        getPlayer(this.turn).removeTopTile();
        getPlayer(this.turn).increaseScore(board.numberOfMatches(x, y) * Config.POINTMULTIPLIER);
        System.out.println(getPlayer(this.turn).getScore());
        turn++;

        if (turn > this.players.size()) {
            turn = 1;
        }

        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getTilesLeft());
        }
    }

    /**
     * Rotates the current players tile. This does not use up the turn.
     */
    public void rotateTile() {
        if (turn == 1) {
            getPlayer(1).rotateTile();
        } else if (turn == 2) {
            getPlayer(2).rotateTile();
        }
    }

    /**
     * Testing.
     *
     * @param args
     */

    public static void main(String[] args) {
        GameLogicController controller = new GameLogicController(1);

        controller.placeTile(-1, 1);
        controller.placeTile(-2, 2);
        controller.placeTile(-3, 3);
        System.out.println(controller.getPlayer(1).getTilesLeft());
        System.out.println("test");
    }
}
