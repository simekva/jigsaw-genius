package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Board manager class to used in GameController. Allows abstract access of the
 * Board class by allowing negative indexes.
 */
public class BoardManager {

    /**
     * Board object with 1 row and 1 column initially.
     */
    private Board board = new Board(1, 1);

    /**
     * Places a tile in the given position.
     *
     * @param tile Tile to be placed.
     * @param x    x coordinate to place it.
     * @param y    y coordinate to place it.
     */
    public void placeTile(final Tile tile, final int x, final int y) {
        board.addTile(tile, x, y);
    }

    /**
     * Places a random tile in the center of the board.
     */
    public void initBoard() {
        board.addTile(board.generateRandomTile(), 0, 0);
    }

    /**
     * Returns adjacent tiles for a given x and y coordinate.
     *
     * @param x x coordinate of the tile to check adjacent tiles.
     * @param y y coordinate of the tile to check adjacent tiles.
     * @return A list of all adjacent tiles.
     */
    public List<Tile> getAdjacentTiles(final int x, final int y) {
        int[] dx = { -2, -1, 1, 2, 1, -1 };
        int[] dy = { 0, 1, 1, 0, -1, -1 };

        List<Tile> adacjentTiles = new ArrayList<Tile>();
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            try {
                adacjentTiles.add(this.board.getTile(x + dx[i], y + dy[i]));
            } catch (Exception e) {
            }
        }
        adacjentTiles.removeAll(java.util.Collections.singleton(null));
        return adacjentTiles;
    }

    /**
     * For testing.
     *
     * @param args empty.
     */
    public static void main(final String[] args) {
        BoardManager main = new BoardManager();
        main.initBoard();
        System.out.println(main.board.toString());
    }
}
