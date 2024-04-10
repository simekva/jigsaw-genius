package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;

public class BoardDrawer {

    TileManager tileManager;
    TileDrawer tileDrawer;
    Assets assets;
    PolygonSpriteBatch batch;

    int[][] tileOffset = new int[][] {
            { 1, 1 },
            { 2, 0 },
            { 1, -1 },
            { -1, -1 },
            { -2, 0 },
            { -1, 1 }
    };

    double[][] positionOffset = new double[][] {
            { 298.0, 170.0 },
            { 0, 345 },
            { 295, -170 },
            { -397, -173 },
            { 0, -345 }
    };

    public BoardDrawer(TileManager manager) {
        this.tileManager = manager;
        this.tileDrawer = new TileDrawer();
    }

    public void drawBoard() {

        Tile[][] board = this.tileManager.getBoard();

        for (int i = 0; i < board[0].length - 1; i++) {
            for (int j = 0; j < board.length; i++) {
                this.tileDrawer.drawTile(assets, board[i][j], batch, i, j);
            }
        }
    }
}
