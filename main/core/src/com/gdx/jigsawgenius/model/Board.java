package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Board class represents the board for the game. It
 * It uses a 2d-matrix to represent the board, and stores
 * the values of the lowest x and y coordinate, to "allow"
 * negative indexes for the boardcontroller.
 */
public class Board {

    /**
     * 2D array with Tile objects.
     */
    private Tile[][] board;

    /**
     * Number of rows.
     */

    private int rows;
    /**
     * Number of columns.
     */
    private int columns;
    /**
     * Lowest x-coordinate, initally 0.
     */

    private int lowestx = 0;
    /**
     * Lowest y-coordinate, initially 0.
     */
    private int lowesty = 0;

    /**
     * Constructs a new board object with specified number of rows and columns.
     *
     * @param numberRows    Number of rows on the created board.
     * @param numberColumns Number of columnds on the created board.
     */

    public Board(final int numberRows, final int numberColumns) {
        this.rows = numberRows;
        this.columns = numberColumns;
        board = new Tile[rows][columns];
    }

    /**
     * Returns a string representation of the board.
     *
     * @return A string representing the board.
     */
    public String toString() {
        String returnString = "";

        for (int i = board[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != null) {
                    returnString += " T";
                } else {
                    returnString += " -";
                }
            }
            returnString += "\n";
        }
        return returnString;
    }

    /**
     * Returns a tile with given x and y coordinates.
     *
     * @param x Coordinate to get.
     * @param y Coordinate to get.
     * @return Tile at position x - lowestx, y - lowesty if it exists.
     *         Else returns null or IllegalArgumentException if illegal x and y.
     */
    public Tile getTile(final int x, final int y) {
        try {
            if ((x + y) % 2 == 0) {
                return this.board[x + lowestx][y + lowesty];
            }
            throw new IllegalArgumentException("Invalid coordinates.");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generates a random Tile object with 6 sides.
     *
     * @return Generated random tile.
     */
    public Tile generateRandomTile() {
        Random random = new Random();
        List<Side> list = new ArrayList<Side>();
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            list.add(new Side(random.nextInt(
                    Side.getLegalTerrainTypes().length)));
        }
        Tile tile = new Tile(list);
        return tile;
    }

    private boolean isOutOfBounds(final int x, final int y) {
        if (x > (this.rows) && y > (this.columns)) {
            return true;
        }
        if (-x > (this.rows) && -y > (this.columns)) {
            return true;
        }
        if (-x > (this.rows + 1) && -y > (this.columns + 1)) {
            return true;
        }

        if (x > (this.rows + 2) || -x > (this.rows + 2)) {
            return true;
        }

        if (y > (this.columns + 2) || -y > (this.columns + 2)) {
            return true;
        }
        return false;
    }

    /**
     * Places a tile in given position if legal.
     *
     * @param tile Tile object to place.
     * @param x    x coordinate to place the tile.
     * @param y    y coordinate to place the tile.
     */
    public final void addTile(final Tile tile, final int x, final int y) {

        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("Out of bounds.");
        }

        if (this.getTile(x, y) != null) {
            throw new IllegalArgumentException(
                    "Can't place tile on non-empty space.");
        }

        boolean isExtended = false;
        // Extends in negative x and y
        if (x < (-lowestx) && y < (-lowesty)
                && (!isExtended)) {
            negativeExtension(1, 1);
            isExtended = true;
        }

        // Extends in negative x positive y
        if (x < (-lowestx) && (y + lowesty) > (columns - 1)
                && (!isExtended)) {
            negativeExtension(1, 0);
            positiveExtension(0, 1);
            isExtended = true;
        }

        // Extend if positive x and y
        if ((x + lowestx) > (rows - 1) && (y + lowesty) > (columns - 1)
                && (!isExtended)) {
            positiveExtension(1, 1);
            isExtended = true;
        }

        // Extend in positive x, negative y
        if ((x + lowestx) > (rows - 1) && (y < -lowesty)
                && (!isExtended)) {
            positiveExtension(1, 0);
            negativeExtension(0, 1);
            isExtended = true;
        }

        // Extends in negative x
        if (x < (-lowestx)
                && (!isExtended)) {
            negativeExtension(1, 0);
            isExtended = true;
        }

        // Extend in positive x
        if ((x + lowestx) > (rows - 1)
                && (!isExtended)) {
            positiveExtension(1, 0);
            isExtended = true;
        }
        // Extends in negative y
        if (y < (-lowesty) && (!isExtended)) {
            negativeExtension(0, 1);
            isExtended = true;
        }
        // Extend in positive y
        if ((y + lowesty) > (columns - 1)
                && (!isExtended)) {
            positiveExtension(0, 1);
            isExtended = true;
        }

        try {
            board[x + lowestx][y + lowesty] = tile;
        } catch (Exception e) {
            this.addTile(tile, x, y);
        }

    }

    private void positiveExtension(final int x, final int y) {
        if (x >= 0 && x <= 1 && y >= 0 && y <= 1) {
            Tile[][] newBoard = new Tile[rows + x][columns + y];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    newBoard[i][j] = board[i][j];
                }
            }
            board = newBoard;
            if (x > 0) {
                rows++;
            }
            if (y > 0) {
                columns++;
            }
        }

    }

    private void negativeExtension(final int x, final int y) {
        if (x >= 0 && x <= 1 && y >= 0 && y <= 1) {
            Tile[][] newBoard = new Tile[rows + x][columns + y];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    newBoard[i + x][j + y] = board[i][j];
                }
            }
            board = newBoard;
            if (x > 0) {
                rows++;
                lowestx++;
            }
            if (y > 0) {
                columns++;
                lowesty++;
            }
        }
    }
}
