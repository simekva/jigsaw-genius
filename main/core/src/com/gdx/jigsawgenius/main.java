package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.controller.DrawerController;
import com.gdx.jigsawgenius.controller.GameInputController;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;

public class main extends ApplicationAdapter {

	Assets assets;
	SpriteBatch batch;
	OrthographicCamera camera;

	static GameLogicController controller;

	GameInputController inputProcessor;

	DrawerController drawerController;

	@Override
	public void create() {

		assets = new Assets();
		batch = new SpriteBatch();

		inputProcessor = new GameInputController(this);
		Gdx.input.setInputProcessor(inputProcessor);

		camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.zoom = 50;
		camera.position.set(Assets.WORLD_SIZE / 2, Assets.WORLD_SIZE / 2, 0);
		camera.update();

		controller = new GameLogicController(2);
		drawerController = new DrawerController();

	}

	@Override
	public void render() {
		if (assets.manager.update()) {
			handleCamera();
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			ScreenUtils.clear(255, 5, 5, 1);
			batch.begin();

			drawerController.drawBoard(controller.getBoard(), assets, batch, 500, 500);
			batch.end();
		}
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

	public void placeTile(int x, int y) {
		controller.placeTile(x, y);
	}

	public Camera getCamera() {
		return this.camera;
	}

}
