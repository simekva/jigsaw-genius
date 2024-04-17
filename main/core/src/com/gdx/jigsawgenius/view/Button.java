package com.gdx.jigsawgenius.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    /**
     * Texture for button.
     */
    private Texture texture;

    /**
     * Bounds for button.
     */
    private Rectangle bounds;

    /**
     * Consctructor that initialises the button.
     *
     * @param textureInput URL to the texture.
     * @param x            x coordinate to draw it in.
     * @param y            y coordinate to draw it in.
     * @param width        width of the button
     * @param height       heigth of the button
     */
    public Button(final Texture textureInput, final float x, final float y,
            final float width, final float height) {
        this.texture = textureInput;
        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Renders the button in the batch.
     *
     * @param batch to draw the button in.
     */
    public final void render(final SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * Dispose the button.
     */
    public final void dispose() {
        texture.dispose();
    }
}
