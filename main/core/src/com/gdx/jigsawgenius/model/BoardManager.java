package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;

public class BoardManager {

    private Board board = new Board(1, 1);

    public void placeTile(Tile tile, int x, int y) {
        board.addTile(tile, x, y);
    }

    public void initBoard() {
        board.addTile(board.generateRandomTile(), 0, 0);
    }

    public Board getBoard() {
        return this.board;
    }

    public List<Tile> getAdjacentTiles(int x, int y) {
        int[] dx = { -2, -1, 1, 2, 1, -1 };
        int[] dy = { 0, 1, 1, 0, -1, -1 };

        List<Tile> adacjentTiles = new ArrayList<Tile>();
        for (int i = 0; i < 6; i++) {
            try {
                adacjentTiles.add(this.board.getTile(x + dx[i], y + dy[i]));
            } catch (Exception e) {
            }
        }
        adacjentTiles.removeAll(java.util.Collections.singleton(null));
        return adacjentTiles;
    }

    public static void main(String[] args) {
        BoardManager main = new BoardManager();
        main.initBoard();
        System.out.println(main.board.toString());
    }
}
