package com.gdx.jigsawgenius.model;

public class Side {
    /**
     * Which terraintype the Side object is.
     */
    private String terrainType;

    /**
     * A list of legal terrain types.
     */
    private static String[] legalTerrainTypes = new String[] {
            "plains", "village", "field", "forest", "desert" };

    /**
     * Returns a Side object with a terraintype decided by the parameter.
     *
     * @param number number
     */
    public Side(final int number) {
        if (number <= Side.getLegalTerrainTypes().length) {
            this.terrainType = legalTerrainTypes[number];
        } else {
            throw new IllegalArgumentException(
                    "Invalid number in generating side.");
        }
    }

    /**
     * Returns the terraintype of the side.
     *
     * @return terraintype of the side.
     */
    public String getTerrainType() {
        return this.terrainType;
    }

    /**
     * Returns the legal terrain types.
     *
     * @return legal terrain types.
     */
    public static String[] getLegalTerrainTypes() {
        return legalTerrainTypes;
    }

}
