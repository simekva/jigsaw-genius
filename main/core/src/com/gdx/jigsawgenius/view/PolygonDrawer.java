package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.jigsawgenius.model.Config;

public class PolygonDrawer {

    private float[] vertices;

    private float[] calculateVertices() {
        vertices = new float[] {
                0, 0,
                (float) (Config.TILE_SIZE), 0,
                (float) ((Config.TILE_SIZE) / 2), (float) (Config.TILE_SIZE)
        };
        return vertices;
    }

    public PolygonRegion drawPolygon(Texture texture, float x, float y) {
        this.vertices = this.calculateVertices();
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(texture), vertices, new short[] {
                0, 1, 2
        });
        return polyReg;
    }
}
