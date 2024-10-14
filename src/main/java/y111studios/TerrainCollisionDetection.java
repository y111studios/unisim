package y111studios;


// 2D array - True = can build, False = can't build
public class TerrainCollisionDetection {
    private boolean[][] buildingGrid;

    public TerrainCollisionDetection(int xSize, int ySize) {
        buildingGrid = new boolean[xSize][ySize];
    }

    // Function to check if a building can be placed at a certain location
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

    // Function to place a building at a certain location by checking collisions and updating the buildingGrid
    public void placeBuilding(int x, int y, int width, int height) {
        if (canPlaceBuilding(x, y, width, height)) {
            for (int i = x; i < x + width; i++) {
                for (int j = y; j < y + height; j++) {
                    buildingGrid[i][j] = false;
                }
            }
        }
    }

    // Removes building by updating the buildingGrid
    public void removeBuilding(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                buildingGrid[i][j] = true;
            }
        }
    }
}