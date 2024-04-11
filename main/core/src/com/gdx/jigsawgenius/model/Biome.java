package com.gdx.jigsawgenius.model;

public class Biome {

    /**
     * id for the biome.
     */
    private int id;

    /**
     * Returns a Side object with a terraintype decided by the parameter.
     *
     * @param number number
     */
    public Biome(final int number) {
        if (number <= Assets.getNumberOfAssets() - 1) {
            this.id = number;
        } else {
            throw new IllegalArgumentException(
                    "Invalid number in generating side.");
        }
    }

    /**
     * Returns the biomes id.
     *
     * @return id
     */
    public final int getBiomeID() {
        return this.id;
    }
}
