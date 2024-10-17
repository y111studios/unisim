package y111studios;


// 2D array - True = can build, False = can't build
public class CollisionDetection {
    private boolean[][] buildingGrid;

    public CollisionDetection(int xSize, int ySize) {
        buildingGrid = new boolean[xSize][ySize];
    }

    /**
     * Checks if a building can be placed on the terrain at the specified coordinates.
     *
     * @param x the x-coordinate of the top-left corner of the building
     * @param y the y-coordinate of the top-left corner of the building
     * @param width the width of the building
     * @param height the height of the building
     * @return true if the building can be placed at the specified location, false otherwise
     */
    public boolean canPlaceBuilding(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if ((buildingGrid[i][j]) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Attempts to place a building on the grid at the specified coordinates.
     * If the building can be placed, it marks the grid cells as occupied.
     *
     * @param x the x-coordinate of the top-left corner of the building
     * @param y the y-coordinate of the top-left corner of the building
     * @param width the width of the building
     * @param height the height of the building
     */
    public void placeBuilding(int x, int y, int width, int height) {
        if (canPlaceBuilding(x, y, width, height)) {
            for (int i = x; i < x + width; i++) {
                for (int j = y; j < y + height; j++) {
                    buildingGrid[i][j] = false;
                }
            }
        }
    }

    /**
     * Removes a building from the grid by setting the specified area to true.
     *
     * @param x the x-coordinate of the top-left corner of the building
     * @param y the y-coordinate of the top-left corner of the building
     * @param width the width of the building
     * @param height the height of the building
     */
    public void removeBuilding(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                buildingGrid[i][j] = true;
            }
        }
    }
}