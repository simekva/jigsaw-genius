package com.gdx.jigsawgenius;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Screen {
    void create();
    void render(SpriteBatch batch);
    void dispose();
}
