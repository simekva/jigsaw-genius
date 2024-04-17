package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static final String PLAINSURL = "plains.PNG";
    public static final String VILLAGEURL = "village.PNG";
    public static final String FIELDURL = "field.PNG";
    public static final String FORESTURL = "forest.PNG";
    public static final String DESERTURL = "desert.PNG";
    public static float pieceHeight;
    public static float pieceWidth;
    public static float tileWidth;
    public static float tileHeight;

    public static int WORLD_SIZE = 1000;

    public AssetManager manager;

    /**
     * Sets up the asset manager and loads it.
     */
    public Assets() {
        manager = new AssetManager();

        for (String url : getAssetsURLs()) {
            manager.load(url, Texture.class);
        }
        manager.finishLoading();
        Texture texture = manager.get(getAssetURL(0), Texture.class);
        pieceHeight = texture.getHeight();
        pieceWidth = texture.getWidth();

        tileWidth = 2 * pieceHeight;
        tileHeight = 2 * pieceWidth;
    }

    /**
     * Get specific asset.
     *
     * @param n
     * @return asset
     */
    public static String getAssetURL(final int n) {
        return Assets.getAssetsURLs().get(n);
    }

    /**
     * Return number of assets.
     *
     * @return number of assets
     */
    public static int getNumberOfAssets() {
        return Assets.getAssetsURLs().size();
    }

    private static List<String> getAssetsURLs() {
        List<String> initializedStrings = new ArrayList<>();
        try {
            Class<?> clazz = Assets.class;
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (field.getType() == String.class
                        && field.get(clazz) != null) {
                    initializedStrings.add((String) field.get(clazz));
                }
            }
        } catch (Exception e) {
        }
        return initializedStrings;
    }
}
