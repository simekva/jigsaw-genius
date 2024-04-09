package com.gdx.jigsawgenius.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    private static final String PLAINSURL = "plains.PNG";
    private static final String VILLAGEURL = "village.PNG";
    private static final String FIELDURL = "field.PNG";
    private static final String FORESTURL = "forest.PNG";
    private static final String DESERTURL = "desert.PNG";

    public AssetManager manager;

    public Assets() {
        manager = new AssetManager();

        for (int i = 0; i < Assets.getNumberOfAssets(); i++) {
            manager.load(Assets.getAssetsURLs().get(i), Texture.class);
        }
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
}
