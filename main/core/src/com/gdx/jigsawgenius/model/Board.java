package com.gdx.jigsawgenius.model;

public class Board {

    Tile[][] board;
    private int rows;
    private int columns;
    private int lowestx = 0;
    private int lowesty = 0;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new Tile[rows][columns];
    }

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

    public Tile getTile(int x, int y) {

        try {
            if ((x + y) % 2 == 0) {
                return this.board[x + lowestx][y + lowesty];
            }
            throw new IllegalArgumentException("Invalid coordinates.");
        } catch (Exception e) {
            return null;
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getLowestX() {
        return this.lowestx;
    }

    public int getLowestY() {
        return this.lowesty;
    }

    private boolean isOutOfBounds(int x, int y) {
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

    public void addTile(Tile tile, int x, int y) {

        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("Out of bounds.");
        }

        if (this.getTile(x, y) != null) {
            throw new IllegalArgumentException("Can't place tile on non-empty space.");
        }

        boolean isExtended = false;
        // Extends in negative x and y
        if (x < (-lowestx) && y < (-lowesty) && (!isExtended)) {
            negativeExtension(1, 1);
            isExtended = true;
        }

        // Extends in negative x positive y
        if (x < (-lowestx) && (y + lowesty) > (columns - 1) && (!isExtended)) {
            negativeExtension(1, 0);
            positiveExtension(0, 1);
            isExtended = true;
        }

        // Extend if positive x and y
        if ((x + lowestx) > (rows - 1) && (y + lowesty) > (columns - 1) && (!isExtended)) {
            positiveExtension(1, 1);
            isExtended = true;
        }

        // Extend in positive x, negative y
        if ((x + lowestx) > (rows - 1) && (y < -lowesty) && (!isExtended)) {
            positiveExtension(1, 0);
            negativeExtension(0, 1);
            isExtended = true;
        }

        // Extends in negative x
        if (x < (-lowestx) && (!isExtended)) {
            negativeExtension(1, 0);
            isExtended = true;
        }

        // Extend in positive x
        if ((x + lowestx) > (rows - 1) && (!isExtended)) {
            positiveExtension(1, 0);
            isExtended = true;
        }
        // Extends in negative y
        if (y < (-lowesty) && (!isExtended)) {
            negativeExtension(0, 1);
            isExtended = true;
        }
        // Extend in positive y
        if ((y + lowesty) > (columns - 1) && (!isExtended)) {
            positiveExtension(0, 1);
            isExtended = true;
        }

        try {
            board[x + lowestx][y + lowesty] = tile;
        } catch (Exception e) {
            this.addTile(tile, x, y);
        }

    }

    private void positiveExtension(int x, int y) {
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

    private void negativeExtension(int x, int y) {
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
