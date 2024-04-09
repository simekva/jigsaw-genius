package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.view.BiomeDrawer;

public class main extends ApplicationAdapter {

	// private Assets assets;
	SpriteBatch batch;
	BiomeDrawer drawer;
	Biome biome = new Biome(0);

	@Override
	public void create() {
		batch = new SpriteBatch();
		drawer = new BiomeDrawer();
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		drawer.drawBiome(biome, batch, 0, 0, 100, 100, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
