package y111studios.map;

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
    
     
	  /**
	   * Checks if a given building's values are valid
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return true if the building will have valid bounds if placed on the map, false otherwise
	 */
	private boolean boundChecker(int x, int y, int width, int height) {
		
		if (y > buildingGrid.length || x > buildingGrid[0].length) {
			return false;
			
		}
		else if (x + width > buildingGrid[0].length || y + height > buildingGrid.length ) {
			
			return false;
			
		}
		else
			return true;
		
    	
    }
    
    
    public boolean canPlaceBuilding(int x, int y, int width, int height) {
    	
    	if (!boundChecker(x, y, width, height)) {
    		return false;
    	}
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
     * Attempts to place a building on the grid at the specified coordinates. If the building can be
     * placed, it marks the grid cells as occupied.
     *
     * @param x the x-coordinate of the top-left corner of the building
     * @param y the y-coordinate of the top-left corner of the building
     * @param width the width of the building
     * @param height the height of the building
     */
    public boolean placeBuilding(int x, int y, int width, int height) {
        if (canPlaceBuilding(x, y, width, height)) {
            for (int i = x; i < x + width; i++) {
                for (int j = y; j < y + height; j++) {
                    buildingGrid[i][j] = false;
                }
            }
            return true;
        } else
        	return false;
    }

    /**
     * Removes a building from the grid by setting the specified area to true.
     *
     * @param x the x-coordinate of the top-left corner of the building
     * @param y the y-coordinate of the top-left corner of the building
     * @param width the width of the building
     * @param height the height of the building
     */
    public boolean removeBuilding(int x, int y, int width, int height) {
        if (canPlaceBuilding(x, y, width, height)) {
			for (int i = x; i < x + width; i++) {
				for (int j = y; j < y + height; j++) {
					buildingGrid[i][j] = true;
				}
			}
			return true;
		} else
			return false;
    }
}
