package y111studios.map;

// 2D array - True = tile occupied, False = tile empty
import y111studios.position.GridArea;

public class CollisionDetection {
    private boolean[][] buildingGrid;
    private GridArea mapArea;

    public CollisionDetection(int width, int height) {
        buildingGrid = new boolean[width][height];
        mapArea = new GridArea(0, 0, width, height);
    }

    /**
     * Checks if a given building's values are valid
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @return true if the building will have valid bounds if placed on the map, false otherwise
     */
    private boolean withinBounds(GridArea area) {
        return mapArea.contains(area);
    }

    /**
     * Checks if a building can be placed on the terrain in the given area.
     *
     * @param area the area to check
     */
    public boolean canPlaceBuilding(GridArea area) {
        if (!withinBounds(area)) {
            return false;
        }
        for (int x = area.getX(); x < area.getX() + area.getWidth(); x++) {
            for (int y = area.getY(); y < area.getY() + area.getHeight(); y++) {
                if (buildingGrid[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Attempts to place a building on the grid in the given area. If the build placed, it marks the
     * grid cells as occupied.
     *
     * @param area the area to place the building
     */
    public void placeBuilding(GridArea area) {
        if (!canPlaceBuilding(area)) {
            return;
        }
        for (int x = area.getX(); x < area.getX() + area.getWidth(); x++) {
            for (int y = area.getY(); y < area.getY() + area.getHeight(); y++) {
                buildingGrid[x][y] = true;
            }
        }
    }

    /**
     * Removes a building from the grid in the given area.
     *
     * @param area the area to remove the building from
     */
    public void removeBuilding(GridArea area) {
        for (int x = area.getX(); x < area.getX() + area.getWidth(); x++) {
            for (int y = area.getY(); y < area.getY() + area.getHeight(); y++) {
                buildingGrid[x][y] = false;
            }
        }
    }
}
