package com.gdx.jigsawgenius.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;
import com.gdx.jigsawgenius.view.TileDrawer;

public class DrawerController {

    /**
     * Tiledrawer that draws individual tiles.
     */
    private TileDrawer tileDrawer;

    /**
     * Initialises tileDrawer.
     */
    public DrawerController() {
        tileDrawer = new TileDrawer();
    }

    /**
     * Uses tiledrawer to draw every tile in the board.
     *
     * @param tileManager
     * @param assets
     * @param batch
     * @param centerX
     * @param centerY
     */
    public final void drawBoard(final TileManager tileManager,
            final Assets assets, final SpriteBatch batch,
            final float centerX, final float centerY) {

        for (Tile[] tileList : tileManager.getAllTiles()) {
            for (Tile tile : tileList) {
                try {
                    tileDrawer.drawTile(assets, tile, batch,
                            tile.getXCoord() + centerX,
                            tile.getYCoord() + centerY);

                } catch (Exception e) {
                }

            }
        }
    }
}
