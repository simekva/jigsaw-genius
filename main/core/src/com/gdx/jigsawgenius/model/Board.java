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
            int n = board.length;
            Tile[][] newBoard = new Tile[n + 1][n + 1];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newBoard[i + 1][j + 1] = board[i][j];
                }
            }
            board = newBoard;
            lowestx++;
            lowesty++;
        }

        else if (x < (0 + lowestx)) {
            int n = board.length;
            Tile[][] newBoard = new Tile[n + 2][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newBoard[i + 2][j] = board[i][j];
                }
            } 
            board = newBoard;
            lowestx += 2;
        }

        else if (y < (0 + lowesty)) {
            int n = board.length;
            Tile[][] newBoard = new Tile[n][n + 2];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newBoard[i][j + 2] = board[i][j];
                }
            }
            board = newBoard;
            lowesty += 2;
        }

        if ((x + y) % 2 == 0) {

            if (x >= board.length || y >= board[0].length) {
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
 