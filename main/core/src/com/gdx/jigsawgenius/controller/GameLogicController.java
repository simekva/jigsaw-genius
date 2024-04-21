package com.gdx.jigsawgenius.controller;

import com.gdx.jigsawgenius.model.TileManager;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * Number to know who'se turn it is.
     */
    private int turn;

    /**
     * Creates a board with a pure plains tile in the middle, and
     * initializes the hand.
     *
     * @param numberOfPlayers
     */
    public GameLogicController(final int numberOfPlayers) {
        board = new TileManager(1, 1);
        board.placeTile(new Tile(), 0, 0);
        if (numberOfPlayers != 1 && numberOfPlayers != 2) {
            throw new IllegalArgumentException(
                    "Couldn't create game controller with " + numberOfPlayers
                            + "players.");
        }
        players = new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }

        // player1 = new Player();
        // player2 = new Player();
        turn = 1;
    }

    /**
     * Returns the active board.
     *
     * @return board.
     */
    public TileManager getBoard() {
        return this.board;
    }

    /**
     * Returns a specific player based on input.
     *
     * @param n
     * @return Player from players array with index n.
     */
    public Player getPlayer(final int n) {
        return this.players.get(n - 1);
    }

    /**
     * Places a tile in the given coordinates.
     *
     * @param x
     * @param y
     */
    public void placeTile(final int x, final int y) {
        board.placeTile(getPlayer(1).getTopTile(), x, y);
        getPlayer(this.turn).removeTopTile();
        getPlayer(this.turn).increaseScore(
                board.numberOfMatches(x, y) * Config.POINTMULTIPLIER);
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
     * Updates tile from the backend. Same as above but less checks, as checks
     * are excecuted on sender's application.
     *
     * @param x
     * @param y
     * @param biomeIDs
     */
    public void placeTileFromBackend(
            final int x, final int y, final List<Integer> biomeIDs) {
        Tile tile = new Tile(x, y, biomeIDs);
        this.board.updateTilesFromBackend(tile, x, y);
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
}
