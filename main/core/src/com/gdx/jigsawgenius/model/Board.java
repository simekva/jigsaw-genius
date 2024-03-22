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

        for (int i = 0; i < board[0].length; i++) {
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

        // // Outside in positive direction
        // if (x > Math.floor(rows / 2) - 1 || y > Math.floor(columns / 2) - 1) {
        // return null;
        // }

        // // Outside in negative direction
        // if (x < -lowestx || y < -lowesty) {
        // return null;
        // }

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

    public void addTile(Tile tile, int x, int y) {

        if (this.getTile(x, y) == null) {

            boolean isExtended = false;

            if (x > (this.rows) && y > (this.columns)) {
                throw new IllegalArgumentException("Out of bounds.");
            }
            if (-x > (this.rows) && -y > (this.columns)) {
                throw new IllegalArgumentException("Out of bounds.");
            }
            if (-x > (this.rows + 1) && -y > (this.columns + 1)) {
                throw new IllegalArgumentException("Out of bounds.");
            }

            if (x > (this.rows + 2) || -x > (this.rows + 2)) {
                throw new IllegalArgumentException("Out of bounds");
            }

            if (y > (this.columns + 2) || -y > (this.columns + 2)) {
                throw new IllegalArgumentException("Out of bounds.");
            }

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

        } else {
            throw new IllegalArgumentException("Can't place tile on non-empty space.");
        }
    }

    public void positiveExtension(int x, int y) {
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

    public void negativeExtension(int x, int y) {
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
