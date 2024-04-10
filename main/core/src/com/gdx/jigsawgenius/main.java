package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.model.TileManager;
import com.gdx.jigsawgenius.view.TileDrawer;

public class main extends ApplicationAdapter {
	// private Assets assets;
	Assets assets;
	PolygonSpriteBatch batch;
	TileDrawer drawer;

	TileManager manager;
	Tile tile;
	Tile tile2;

	OrthographicCamera camera;

	@Override
	public void create() {
		manager = new TileManager(1, 1);
		tile = manager.generateRandomTile();
		tile2 = manager.generateRandomTile();
		assets = new Assets();
		batch = new PolygonSpriteBatch();
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
		drawer.drawTile(assets, tile, batch, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		batch.draw(new Texture("point.png"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		batch.end();
		assets.manager.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.manager.clear();
	}
}
