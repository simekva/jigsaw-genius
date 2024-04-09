package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class main extends ApplicationAdapter {

	// private Assets assets;
	SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
