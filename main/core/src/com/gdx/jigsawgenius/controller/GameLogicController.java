package com.gdx.jigsawgenius.controller;

import com.gdx.jigsawgenius.model.TileManager;
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
    private Player player1;

    /**
     * Player 2 object.
     */
    private Player player2;

    /**
     * Number to know who'se turn it is.
     */
    private int turn;

    /**
     * Creates a board with a pure plains tile in the middle, and
     * initializes the hand.
     */
    public GameLogicController() {
        board = new TileManager(1, 1);
        board.placeTile(new Tile(), 0, 0);
        player1 = new Player();
        player2 = new Player();
        turn = 1;
    }

    public TileManager getBoard() {
        return this.board;
    }

    /**
     * Places a tile in the given coordinates.
     *
     * @param x
     * @param y
     */
    public void placeTile(final int x, final int y) {
        boolean placed = false;
        if (turn == 1 && !placed) {
            board.placeTile(player1.getTopTile(), x, y);
            player1.removeTopTile();
            turn = 2;
            player1.increaseScore(board.numberOfMatches(x, y)
                    * Config.POINTMULTIPLIER);
            System.out.println("Player 1 placed tile on: " + x + ", "
                    + y + ", and scored"
                    + board.numberOfMatches(x, y)
                            * Config.POINTMULTIPLIER
                    + " points.");
            placed = true;
        } else if (turn == 2 && !placed) {
            board.placeTile(player2.getTopTile(), x, y);
            player2.removeTopTile();
            turn = 1;
            System.out.println("Player 2 placed tile on: " + x + ", " + y
                    + ", and scored"
                    + board.numberOfMatches(x, y)
                            * Config.POINTMULTIPLIER
                    + " points.");
            player2.increaseScore(board.numberOfMatches(x, y)
                    * Config.POINTMULTIPLIER);
            placed = true;
        }
        System.out.println(player1.getTilesLeft());
        System.out.println(player2.getTilesLeft());
    }

    /**
     * Rotates the current players tile. This does not use up the turn.
     */
    public void rotateTile() {
        if (turn == 1) {
            player1.rotateTile();
        } else if (turn == 2) {
            player2.rotateTile();
        }
    }

    /**
     * Testing.
     *
     * @param args
     */

    public static void main(String[] args) {
        GameLogicController controller = new GameLogicController();

        controller.placeTile(-1, 1);
        controller.placeTile(-2, 2);
        controller.placeTile(-3, 3);
        System.out.println(controller.player1.getTilesLeft());
        System.out.println(controller.player2.getTilesLeft());
        System.out.println("test");
    }
}
