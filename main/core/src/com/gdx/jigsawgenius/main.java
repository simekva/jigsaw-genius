package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.view.BiomeDrawer;
import com.gdx.jigsawgenius.view.TileDrawer;

public class main extends ApplicationAdapter {
	// private Assets assets;
	PolygonSpriteBatch batch;
	Tile tile;
	TileDrawer drawer;
	BiomeDrawer biomeDrawer;
	BiomeDrawer biomeDrawer2;
	Biome biome;

	Camera camera;

	@Override
	public void create() {
		batch = new PolygonSpriteBatch();
		tile = new Tile();
		drawer = new TileDrawer();

		// biomeDrawer = new BiomeDrawer();
		// biome = new Biome(0);
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		drawer.drawTile(tile, batch, 300, 300);
		// biomeDrawer.drawBiome(biome, batch, 50, 0, 0);
		// biomeDrawer.drawBiome(biome, batch, 125, 41, 60);
		// biomeDrawer.drawBiome(biome, batch, 125, 130, 120);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
