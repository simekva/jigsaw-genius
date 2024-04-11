package com.gdx.jigsawgenius;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.jigsawgenius.controller.GameInputProcessor;
import com.gdx.jigsawgenius.controller.GameLogicController;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.model.Biome;
import com.gdx.jigsawgenius.model.Tile;
import com.gdx.jigsawgenius.view.AdjacentTileDrawer;
import com.gdx.jigsawgenius.view.BiomeDrawer;
import com.gdx.jigsawgenius.view.BoardDrawer;
import com.gdx.jigsawgenius.view.TileDrawer;

public class main extends ApplicationAdapter {

	Assets assets;
	SpriteBatch batch;
	OrthographicCamera camera;

	static GameLogicController controller;
	AdjacentTileDrawer drawer;

	GameInputProcessor inputProcessor;

	// TESTING
	Biome biome;
	BiomeDrawer biomeDrawer;
	Tile tile;
	TileDrawer tileDrawer;
	BoardDrawer boardDrawer;

	@Override
	public void create() {

		assets = new Assets();
		batch = new SpriteBatch();

		inputProcessor = new GameInputProcessor(this);
		Gdx.input.setInputProcessor(inputProcessor);

		camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.zoom = 50;
		camera.position.set(Assets.WORLD_SIZE / 2, Assets.WORLD_SIZE / 2, 0);
		camera.update();

		controller = new GameLogicController();
		drawer = new AdjacentTileDrawer(controller.getBoard().getTile(0, 0), controller.getBoard());
		System.out.println(camera.position.x);

		// TESTING
		biome = new Biome(0);
		biomeDrawer = new BiomeDrawer();
		tile = new Tile();
		tileDrawer = new TileDrawer();
		boardDrawer = new BoardDrawer();
	}

	@Override
	public void render() {
		assets.manager.finishLoading();
		handleCamera();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		// drawer.drawAdjacentTiles(assets, batch, Assets.WORLD_SIZE / 2,
		// Assets.WORLD_SIZE / 2);

		// biomeDrawer.drawBiome(assets, biome, batch, 500, 500, 30);
		// tileDrawer.drawTile(assets, tile, batch, 500, 500);
		// tileDrawer.drawTile(assets, tile, batch, 500 + Assets.pieceHeight * 2, 500);
		boardDrawer.drawBoard(controller.getBoard(), assets, batch, 500, 500);
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

	public void placeTile(int x, int y) {
		controller.placeTile(x, y);
	}

	public Camera getCamera() {
		return this.camera;
	}

}
