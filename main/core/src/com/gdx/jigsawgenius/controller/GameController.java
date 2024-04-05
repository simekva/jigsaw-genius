package com.gdx.jigsawgenius.controller;

import com.gdx.jigsawgenius.model.TileManager;
import com.gdx.jigsawgenius.model.Player;
import com.gdx.jigsawgenius.model.Tile;

public class GameController {

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
     * Points to get for every matching biome.
     */
    static final int POINTMULTIPLIER = 100;

    /**
     * Creates a board with a pure plains tile in the middle, and
     * initializes the hand.
     */
    public GameController() {
        board = new TileManager(1, 1);
        board.placeTile(new Tile(), 0, 0);
        player1 = new Player();
        player2 = new Player();
        turn = 1;
    }

    /**
     * Places a tile in the given coordinates.
     *
     * @param x
     * @param y
     */
    public void placeTile(final int x, final int y) {
        if (turn == 1) {
            board.placeTile(player1.popTile(), x, y);
            turn = 2;
            player1.increaseScore(board.numberOfMatches(x, y)
                    * GameController.POINTMULTIPLIER);
            System.out.println("Player 1 placed tile on: " + x + ", "
                    + y + ", and scored"
                    + board.numberOfMatches(x, y)
                            * GameController.POINTMULTIPLIER
                    + " points.");
        } else if (turn == 2) {
            board.placeTile(player2.popTile(), x, y);
            turn = 1;
            System.out.println("Player 2 placed tile on: " + x + ", " + y
                    + ", and scored"
                    + board.numberOfMatches(x, y)
                            * GameController.POINTMULTIPLIER
                    + " points.");
            player2.increaseScore(board.numberOfMatches(x, y)
                    * GameController.POINTMULTIPLIER);
        }
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
        GameController controller = new GameController();
        controller.placeTile(2, 0);
        controller.placeTile(-2, 0);
        System.out.println(controller.board.numberOfMatches(0, 0));
        System.out.println("test");
    }
}
