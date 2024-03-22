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
        if ((x + y) % 2 == 0) {
            return this.board[x + lowestx][y + lowesty];
        }
        throw new IllegalArgumentException("Invalid coordinates.");
    }

    public void addTile(Tile tile, int x, int y) {

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

        if (x < (0 + lowestx) && y < (0 + lowestx)) {
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
        }

        else if (x < (0 + lowestx)) {
            Tile[][] newBoard = new Tile[rows + 2][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    newBoard[i + 2][j] = board[i][j];
                }
            } 
            board = newBoard;
            lowestx += 2;
            rows += 2;
        }

        else if (y < (0 + lowesty)) {
            Tile[][] newBoard = new Tile[rows][columns + 2];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    newBoard[i][j + 2] = board[i][j];
                }
            }
            board = newBoard;
            lowesty += 2;
            columns += 2;
        }

        // Extend in positive x
        else if ((x + lowestx) > rows) {
            Tile[][] newBoard = new Tile[rows + 2][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    newBoard[i][j] = board[i][j];
                }
            }
            board = newBoard;
            rows += 2;
        }

        if ((x + y) % 2 == 0) {

            if (x >= board.length - lowestx || y >= board[0].length - lowesty) {
                int newRows = Math.max(x + 1, board.length);
                int newColumns = Math.max(y + 1, board[0].length);
                this.rows++;
                this.columns++;
    
                Tile[][] newBoard = new Tile[newRows][newColumns];
    
                for (int i = 0; i < board.length; i++) {
                    System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
                }
                board = newBoard;
            }
    
            if (this.getTile(x, y ) == null) {
                board[x + lowestx][y + lowesty] = tile;
            } else {
                throw new IllegalArgumentException("Can't place tile on non-empty space.");
            }
        } else {
            throw new IllegalArgumentException("Invalid coordinates.");
        }
    }
}
 