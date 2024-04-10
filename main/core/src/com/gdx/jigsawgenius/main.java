package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.GameInputProcessor;
import com.gdx.jigsawgenius.view.AdjacentTileDrawer;
import com.gdx.jigsawgenius.view.TileDrawer;

public class main extends ApplicationAdapter {

	static final int WORLD_WIDTH = 10000;
	static final int WORLD_HEIGHT = 10000;
	Assets assets;
	PolygonSpriteBatch batch;
	OrthographicCamera camera;

	static GameLogicController controller;
	AdjacentTileDrawer drawer;
	TileDrawer tileDrawer;

	GameInputProcessor inputProcessor;

	@Override
	public void create() {

		assets = new Assets();
		batch = new PolygonSpriteBatch();

		inputProcessor = new GameInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.zoom = 25;
		camera.update();

		controller = new GameLogicController();
		drawer = new AdjacentTileDrawer(controller.getBoard().getTile(0, 0), controller.getBoard());
		System.out.println(camera.position.x);
	}

	@Override
	public void render() {
		assets.manager.finishLoading();
		handleCamera();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		drawer.drawAdjacentTiles(assets, batch, 0, 0);
		batch.end();
		assets.manager.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.manager.clear();
	}

	private void handleCamera() {
		if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.A)) {
			camera.zoom += 10;
			System.out.println("zooming");
		}
		if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.Q)) {
			camera.zoom -= 10;
		}
		if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
			camera.translate(-5, 0, 0);
			System.out.println(camera.position.x);
		}
		if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {

			camera.translate(5, 0, 0);
			System.out.println(camera.position.x);
		}
		if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
			camera.translate(0, -5, 0);
		}
		if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
			camera.translate(0, 5, 0);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = 30f;
		camera.viewportHeight = 30f * height / width;
		camera.update();
	}

	@Override
	public void resume() {
	}

	@Override
	public void pause() {
	}

	public static void placeTile(int x, int y) {
		controller.placeTile(x, y);
	}

}
