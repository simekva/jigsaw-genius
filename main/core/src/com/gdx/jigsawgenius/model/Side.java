package com.gdx.jigsawgenius.model;

public class Side {
    private String terrainType;

    String[] legalTerrainTypes = new String[]{"plains", "village", "field", "forest", "desert"};

    public Side(String terrainType) {
        boolean containsString = false;
        for (int i = 0; i < legalTerrainTypes.length; i++) {
            if (legalTerrainTypes[i].equals(terrainType)) {
                containsString = !containsString;
            }
        }
        if (!containsString) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.terrainType = terrainType;
    }

    public Side(int number) {
        if (number <= 5) {
            this.terrainType = legalTerrainTypes[number];
        }
        else {
            throw new IllegalArgumentException("Invalid number in generating side.");
        }
    }
    
    public String getTerrainType() {
        return this.terrainType;
    }
}
