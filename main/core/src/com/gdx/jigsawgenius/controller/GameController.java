package com.gdx.jigsawgenius.controller;

import com.gdx.jigsawgenius.model.Board;
import com.gdx.jigsawgenius.model.Player2;
import com.gdx.jigsawgenius.model.Tile;

public class GameController {

    /**
     * Board object.
     */
    private Board board;
    /**
     * Player 1 object.
     */
    private Player2 player1;
    /**
     * Player 2 object.
     */
    private Player2 player2;
    /**
     * Number to know who'se turn it is.
     */
    private int turn;

    /**
     * Creates a board with a pure plains tile in the middle, and
     * initializes the hand.
     */
    public GameController() {
        board = new Board(1, 1);
        board.placeTile(new Tile(), 0, 0);
        player1 = new Player2();
        player2 = new Player2();
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
            System.out.println("Player 1 placed tile on: " + x + ", " + y + ", and scored"
                    + board.numberOfMatches(x, y) * 100 + " points.");
            player1.increaseScore(board.numberOfMatches(x, y) * 100);
        } else if (turn == 2) {
            board.placeTile(player2.popTile(), x, y);
            turn = 1;
            System.out.println("Player 2 placed tile on: " + x + ", " + y + ", and scored"
                    + board.numberOfMatches(x, y) * 100 + " points.");
            player2.increaseScore(board.numberOfMatches(x, y) * 100);
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
