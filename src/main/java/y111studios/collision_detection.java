package y111studios;

public class CollisionDetection {
    private boolean[][] buildingGrid;

    public CollisionDetection(int xSize, int ySize) {
        buildingGrid = new boolean[xSize][ySize];
    }

    public boolean canPlaceBuilding(int x, int y) {
        return !buildingGrid[x][y];
    }

    public void placeBuilding(int x, int y) {
        if (canPlaceBuilding(x, y)) {
            buildingGrid[x][y] = true;
        } else {
            throw new IllegalArgumentException("Building cannot be placed at the specified location.");
        }
    }

    public void removeBuilding(int x, int y) {
        buildingGrid[x][y] = false;
    }
}