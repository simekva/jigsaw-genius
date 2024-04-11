package com.gdx.jigsawgenius.view;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;

public class AdjacentTileDrawer {

    int[][] tileOffset = new int[][] {
            { 1, 1 },
            { 2, 0 },
            { 1, -1 },
            { -1, -1 },
            { -2, 0 },
            { -1, 1 }
    };

    TileDrawer drawer;
    Tile tile;
    List<Tile> adjacentTiles;
    TileManager manager;

    public AdjacentTileDrawer(Tile tile, TileManager manager) {
        this.tile = tile;
        // this.adjacentTiles = manager.getAdjacentTiles(tile.getX(), tile.getY());
        this.manager = manager;
        drawer = new TileDrawer();
    }

    public void drawAdjacentTiles(Assets assets, SpriteBatch batch, float x, float y) {
        for (int i = 0; i < adjacentTiles.size(); i++) {
            // int tempx = this.adjacentTiles.get(i).getX() - this.tile.getX();
            // int tempy = this.adjacentTiles.get(i).getY() - this.tile.getY();

            double positionX = 0;
            double positionY = 0;

            for (int j = 0; j < tileOffset.length; j++) {
                int[] list = new int[] {
                        // tempx, tempy
                };
                if (tileOffset[j][0] == list[0] && tileOffset[j][1] == list[1]) {
                    // positionX = positionOffset[j][0] * assets.getTileWidth();
                    // positionY = positionOffset[j][1] * assets.getTileHeight();
                    if (tileOffset[j][0] % 2 == 0) {
                        positionX = tileOffset[j][0] * Assets.pieceWidth;
                        positionY = tileOffset[j][1] * Assets.pieceHeight;
                    } else {
                        positionX = tileOffset[j][0] * Assets.pieceHeight * MathUtils.cosDeg(60) * 2;
                        positionY = tileOffset[j][1] * Assets.pieceHeight * MathUtils.sinDeg(60) * 2;
                    }
                }
            }
            drawer.drawTile(assets, adjacentTiles.get(i), batch, (float) positionX + x,
                    (float) positionY + y);
        }

        drawer.drawTile(assets, tile, batch, x, y);
        // this.adjacentTiles = this.manager.getAdjacentTiles(tile.getX(), tile.getY());
    }

    // public static void main(String[] args) {
    // Assets assets = new Assets();
    // GameLogicController controller = new GameLogicController();
    // controller.placeTile(2, 0);
    // AdjacentTileDrawer drawer = new
    // AdjacentTileDrawer(controller.getBoard().getTile(0, 0),
    // controller.getBoard());
    // drawer.drawAdjacentTiles(assets, 0, 0);
    // }

}
