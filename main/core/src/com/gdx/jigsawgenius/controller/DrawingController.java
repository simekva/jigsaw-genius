package com.gdx.jigsawgenius.controller;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;
import com.gdx.jigsawgenius.view.TileDrawer;

public class DrawingController {

    Tile originTile;
    Tile[][] allTiles;

    int[][] tileOffset = new int[][] {
            { 1, 1 },
            { 2, 0 },
            { 1, -1 },
            { -1, -1 },
            { -2, 0 },
            { -1, 1 }
    };

    double[][] positionOffset = new double[][] {
            { 172.5, 300.0 },
            { 345, 0 },
            { 172.5, -300 },
            { -172.5, -300 },
            { -345, 0 },
            { -172.5, 300 }
    };

    TileDrawer drawer;
    TileManager board;

    public DrawingController() {
        drawer = new TileDrawer();
        originTile = board.getTile(0, 0);
        allTiles = board.getAllTiles();
    };

    public void drawBoard(Assets assets, PolygonSpriteBatch batch) {

        for (int i = 0; i < allTiles[0].length; i++) {
            for (int j = 0; j < allTiles.length; j++) {
                // drawer.drawTile(assets, board.getTile(i, j), batch, i, j);
            }
        }
    }
}
