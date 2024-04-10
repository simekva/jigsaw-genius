package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;
import com.gdx.jigsawgenius.view.BiomeDrawer;
import com.gdx.jigsawgenius.view.TileDrawer;

public class main extends ApplicationAdapter {
	// private Assets assets;
	Assets assets;
	PolygonSpriteBatch batch;
	Tile tile;
	TileManager manager;
	TileDrawer drawer;
	BiomeDrawer biomeDrawer;
	BiomeDrawer biomeDrawer2;
	Biome biome;

	OrthographicCamera camera;

	@Override
	public void create() {
		assets = new Assets();
		batch = new PolygonSpriteBatch();
		manager = new TileManager(1, 1);
		tile = manager.generateRandomTile();
		drawer = new TileDrawer();

		camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		// biomeDrawer = new BiomeDrawer();
		// biome = new Biome(0);
	}

	@Override
	public void render() {
		assets.manager.finishLoading();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		drawer.drawTile(assets, tile, batch, 300, 200);
		// biomeDrawer.drawBiome(biome, batch, 50, 0, 0);
		// biomeDrawer.drawBiome(biome, batch, 125, 41, 60);
		// biomeDrawer.drawBiome(biome, batch, 125, 130, 120);
		batch.end();
		assets.manager.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.manager.clear();
	}
}
