package com.gdx.jigsawgenius.controller;

import com.gdx.jigsawgenius.model.Board;
import com.gdx.jigsawgenius.model.Player2;
import com.gdx.jigsawgenius.model.Tile;

public class GameController {
    Board board;
    Player2 player1;
    Player2 player2;
    int turn;

    /**
     * Creates a board with a pure plains tile in the middle, and
     * initializes the hand.
     */
    public GameController() {
        board = new Board(1, 1);
        board.placeTile(new Tile(), 0, 0);
        player1 = new Player2();
        player2 = new Player2();
        player1.initHand();
        player2.initHand();
        turn = 1;
    }

    public void placeTile(final int x, final int y) {
        if (turn == 1) {
            board.placeTile(player1.popTile(), x, y);
            turn = 2;
            System.out.println("Player 1 placed tile on: " + x + ", " + y);
        } else if (turn == 2) {
            board.placeTile(player2.popTile(), x, y);
            turn = 1;
            System.out.println("Player 2 placed tile on: " + x + ", " + y);
        }
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.placeTile(2, 0);
        controller.placeTile(-2, 0);
        System.out.println(controller.board.getAdjacentTilesPositions(0, 0));
        System.out.println("test");
    }
}
