package com.gdx.jigsawgenius;

/* 
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
*/

import com.badlogic.gdx.Game;
import com.gdx.jigsawgenius.menu.Assets;
import com.gdx.jigsawgenius.menu.MainMenuScreen;

public class main extends Game {

    @Override
    public void create() {
        Assets.load(); // Load assets if necessary
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose(); // Dispose of assets if necessary
    }
}

