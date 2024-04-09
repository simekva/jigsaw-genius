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

    public AssetManager manager;

    public Assets() {
        manager = new AssetManager();

        for (int i = 0; i < Assets.getNumberOfAssets(); i++) {
            manager.load(Assets.getAssetsURLs().get(i), Texture.class);
        }
    }

    public String getAssetURL(int n) {
        return Assets.getAssetsURLs().get(n);
    }

    private static int getNumberOfAssets() {
        return Assets.getAssetsURLs().size();
    }

    private static List<String> getAssetsURLs() {
        List<String> initializedStrings = new ArrayList<>();
        try {
            Class<?> clazz = Assets.class;
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (field.getType() == String.class && field.get(clazz) != null) {
                    initializedStrings.add((String) field.get(clazz));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            // Handle exception as per your requirement
        }
        return initializedStrings;
    }

    public static void main(String[] args) {
        Assets assets = new Assets();
        assets.manager.finishLoading();

        Biome biome = new Biome(0);

        Texture plainsTexture = assets.manager.get(Biome.getLegalTerrainTypes()[0]);

        // Texture plainsTexture = assets.manager.get(Assets.PLAINSURL, Texture.class);
    }
}
