package com.gdx.jigsawgenius.model;

public class Biome {

    private int ID;

    /**
     * A list of legal terrain types.
     */

    /**
     * Returns a Side object with a terraintype decided by the parameter.
     *
     * @param number number
     */
    public Biome(final int number) {
        if (number <= Config.legalTerrainTypes.length) {
            this.ID = number;
        } else {
            throw new IllegalArgumentException(
                    "Invalid number in generating side.");
        }
    }

    public int getBiomeID() {
        return this.ID;
    }
}
