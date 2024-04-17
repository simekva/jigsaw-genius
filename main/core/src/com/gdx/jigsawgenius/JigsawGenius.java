package com.gdx.jigsawgenius;

import com.badlogic.gdx.Game;
import com.gdx.jigsawgenius.model.Assets;
import com.gdx.jigsawgenius.view.MainMenuScreen;

public class JigsawGenius extends Game {

	Assets assets;
	Game game;

	public JigsawGenius() {
		game = this;
	}

	@Override
	public void create() {
		assets = new Assets();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public Assets getAssets() {
		return this.assets;
	}
}