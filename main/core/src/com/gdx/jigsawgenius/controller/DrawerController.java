package com.gdx.jigsawgenius.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;
import com.gdx.jigsawgenius.view.TileDrawer;

public class DrawerController {

    TileDrawer tileDrawer;

    public DrawerController() {
        tileDrawer = new TileDrawer();
    }

    public void drawBoard(TileManager manager, Assets assets, SpriteBatch batch, float centerX, float centerY) {
        for (Tile[] tileList : manager.getAllTiles()) {
            for (Tile tile : tileList) {
                try {
                    tileDrawer.drawTile(assets, tile, batch, tile.getXCoord() + centerX,
                            tile.getYCoord() + centerY);

                } catch (Exception e) {
                }

            }
        }
    }
}
