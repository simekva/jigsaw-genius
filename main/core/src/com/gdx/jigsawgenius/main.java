package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.view.TileDrawer;

public class main extends ApplicationAdapter {

	// private Assets assets;
	SpriteBatch batch;
	Tile tile;
	TileDrawer drawer;

	@Override
	public void create() {
		batch = new SpriteBatch();
		tile = new Tile();
		drawer = new TileDrawer();
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		drawer.drawTile(tile, batch, 0, 0, 50, 50);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
