package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.model.Assets;

public class main extends ApplicationAdapter {
	// private Assets assets;
	Assets assets;
	PolygonSpriteBatch batch;
	OrthographicCamera camera;

	@Override
	public void create() {
		assets = new Assets();
		batch = new PolygonSpriteBatch();

		camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
	}

	@Override
	public void render() {
		assets.manager.finishLoading();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.end();
		assets.manager.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.manager.clear();
	}
}
