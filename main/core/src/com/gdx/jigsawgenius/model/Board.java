package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;

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
     * Difference in x-coordinates for different tiles.
     */
    private int[] dx = { 1, 2, 1, -1, -2, -1 };
    /**
     * Difference in y-coordinates for different tiles.
     */
    @SuppressWarnings("checkstyle:WhitespaceAfter")
    private int[] dy = { 1, 0, -1, -1, 0, 1 };

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
        System.out.println("Initiated board with dims: " + numberRows + ", "
                + numberColumns);
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
            // System.out.println("No tile in position: " + x + "," + y);
            return null;
        }
    }

    /**
     * Returns adjacent tiles for a given x and y coordinate.
     *
     * @param x x coordinate of the tile to check adjacent tiles.
     * @param y y coordinate of the tile to check adjacent tiles.
     * @return A list of all adjacent tiles.
     */
    public List<Tile> getAdjacentTiles(final int x, final int y) {

        List<Tile> adacjentTiles = new ArrayList<Tile>();
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            try {
                adacjentTiles.add(this.getTile(x + this.dx[i], y + this.dy[i]));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        adacjentTiles.removeAll(java.util.Collections.singleton(null));
        System.out.println("Found " + adacjentTiles.size()
                + " adjacent tile(s) for tile: " + x + ", " + y);
        return adacjentTiles;
    }

    /**
     * Returns number of matching biomes for a tile with given coordinates.
     *
     * @param x
     * @param y
     * @return number of matching biomes.
     */
    public final int numberOfMatches(final int x, final int y) {
        int[] biomePositionOne = { 0, 1, 2, 3, 4, 5 };
        int[] biomePositionTwo = { 3, 4, 5, 0, 1, 2 };

        int numberOfMatches = 0;

        Tile currentTile = this.getTile(x, y);
        for (int i = 0; i < Tile.SIDESCOUNT; i++) {
            try {
                Tile tileToCheck = this.getTile(x + this.dx[i], y + this.dy[i]);
                if (tileToCheck != null) {
                    String currentTileBiome = currentTile.getSides()
                            .get(biomePositionOne[i]).getTerrainType();
                    String tileToCheckBiome = tileToCheck.getSides()
                            .get(biomePositionTwo[i]).getTerrainType();

                    if (currentTileBiome == tileToCheckBiome) {
                        numberOfMatches++;
                    }
                }
            } catch (Exception e) {

            }
        }
        return numberOfMatches;
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
    public final void placeTile(final Tile tile, final int x, final int y) {

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
            System.out.println("Placed tile in position: " + (x + lowestx)
                    + ", " + (y + lowesty));
            System.out.println("Board dimensions: " + this.rows
                    + ", " + this.columns);
        } catch (Exception e) {
            this.placeTile(tile, x, y);
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
                System.out.println("Extended x.");
            }
            if (y > 0) {
                columns++;
                System.out.println("Extended y.");
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
