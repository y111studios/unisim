package y111studios.buildings;

import lombok.Getter;
import y111studios.position.GridArea;
import y111studios.position.GridPosition;

/**
 * A class representing a map object within the game. This class is the superclass of all objects
 * that can be placed onto the game map.
 */
public abstract class MapObject {

    protected @Getter GridArea area;

    protected MapObject(GridArea area) {
        if (area == null) {
            throw new IllegalArgumentException("GridArea must not be null");
        }
        this.area = area;
    }

    /**
     * Returns if the position is contained within the area of this map object.
     * 
     * @param position the GridPosition to check
     * @return true if the specified position is within this area, false otherwise
     * 
     * @see GridArea#contains(GridPosition)
     */
    public boolean contains(GridPosition position) {
        return area.contains(position);
    }

}
