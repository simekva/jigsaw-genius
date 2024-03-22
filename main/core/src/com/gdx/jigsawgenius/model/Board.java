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
            if (x < (-lowestx) && y < (-lowestx) && (!isExtended)) {
                Tile[][] newBoard = new Tile[rows + 1][columns + 1];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i + 1][j + 1] = board[i][j];
                    }
                }
                board = newBoard;
                lowestx++;
                lowesty++;
                columns++;
                rows++;
                isExtended = true;
            }

            // Extends in negative x positive y
            if (x < (-lowestx) && (y + lowesty) > (columns - 1) && (!isExtended)) {
                Tile[][] newBoard = new Tile[rows + 1][columns + 1];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < rows; j++) {
                        newBoard[i + 1][j] = board[i][j];
                    }
                }
                board = newBoard;
                lowestx++;
                rows++;
                columns++;
                isExtended = true;
            }

            // Extend if positive x and y
            if ((x + lowestx) > (rows - 1) && (y + lowesty) > (columns - 1) && (!isExtended)) {

                Tile[][] newBoard = new Tile[rows + 1][columns + 1];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i][j] = board[i][j];
                    }
                }
                board = newBoard;
                rows++;
                columns++;
                isExtended = true;
            }

            // Extends in negative x
            if (x < (-lowestx) && (!isExtended)) {
                Tile[][] newBoard = new Tile[rows + 2][columns];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i + 2][j] = board[i][j];
                    }
                }
                board = newBoard;
                lowestx += 2;
                rows += 2;
                isExtended = true;
            }

            // Extend in positive x
            if ((x + lowestx) > (rows - 1) && (!isExtended)) {
                Tile[][] newBoard = new Tile[rows + 2][columns];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i][j] = board[i][j];
                    }
                }
                board = newBoard;
                rows += 2;
                isExtended = true;
            }

            // Extend in positive x, negative y
            if ((x + lowestx) > rows && (y < -lowesty) && !isExtended) {
                Tile[][] newBoard = new Tile[rows + 1][columns + 1];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i][j + 1] = board[i][j];
                    }
                }
                board = newBoard;
                rows++;
                columns++;
                lowesty++;
                isExtended = true;
            }

            // Extend in positive y
            if (((y + lowesty) > (columns - 1) && (!isExtended))) {
                Tile[][] newBoard = new Tile[rows][columns + 1];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i][j] = board[i][j];
                    }
                }
                board = newBoard;
                columns++;
                isExtended = true;
            }

            // Extend in negative y
            if ((y < -lowesty) && !isExtended) {
                Tile[][] newBoard = new Tile[rows][columns + 1];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        newBoard[i][j + 1] = board[i][j];
                    }
                }
                board = newBoard;
                columns++;
                lowesty++;
                isExtended = true;
            }

            board[x + lowestx][y + lowesty] = tile;
        } else {
            throw new IllegalArgumentException("Can't place tile on non-empty space.");
        }
    }

    public String toString() throws ArrayIndexOutOfBoundsException {
        String returnString = "";

        for (int i = 1; i < columns; i++) {
            for (int j = 1; j < rows; j++) {
                try {
                    if (board[j][i] != null) {
                        returnString += "T";
                    } else {
                        returnString += "-";
                    }
                } catch (Exception e) {
                }
            }
            returnString += "\n";
        }

        return returnString;
    }
}
