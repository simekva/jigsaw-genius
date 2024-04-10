package com.gdx.jigsawgenius.view;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.gdx.jigsawgenius.main;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Config;
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

    double[][] positionOffset = new double[][] {
            { 172.5, 300.0 },
            { 345, 0 },
            { 172.5, -300 },
            { -172.5, -300 },
            { -345, 0 },
            { -172.5, 300 }
    };

    TileDrawer drawer;
    Tile tile;
    List<Tile> adjacentTiles;

    public AdjacentTileDrawer(Tile tile, TileManager manager) {
        this.tile = tile;
        this.adjacentTiles = manager.getAdjacentTiles(tile.getX(), tile.getY());
        drawer = new TileDrawer();
    }

    public void drawAdjacentTiles(Assets assets, PolygonSpriteBatch batch, float x, float y) {
        for (int i = 0; i < adjacentTiles.size(); i++) {
            int tempx = this.adjacentTiles.get(i).getX() - this.tile.getX();
            int tempy = this.adjacentTiles.get(i).getY() - this.tile.getY();

            double positionX = 0;
            double positionY = 0;

            for (int j = 0; j < positionOffset.length; j++) {
                int[] list = new int[] {
                        tempx, tempy
                };
                if (tileOffset[j][0] == list[0] && tileOffset[j][1] == list[1]) {
                    positionX = positionOffset[j][0];
                    positionY = positionOffset[j][1];
                }
            }
            drawer.drawTile(assets, adjacentTiles.get(i), batch, (float) positionX + x,
                    (float) positionY + y);
        }

        drawer.drawTile(assets, tile, batch, x, y);
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
