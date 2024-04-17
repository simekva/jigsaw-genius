package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Camera;

public interface ScreenInterface {

    public void show();

    public void render(float delta);

    public void dispose();

    public void handleCamera();

    public void resize(int width, int height);

    public void placeTile(int x, int y);

    public Camera getCamera();

}
