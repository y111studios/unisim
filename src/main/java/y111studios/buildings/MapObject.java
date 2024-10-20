package y111studios.buildings;

import y111studios.position.GridArea;

/**
 * A class representing a map object within the game. This class is the superclass of all objects
 * that can be placed onto the game map.
 */
public abstract class MapObject {

    protected GridArea area;

    public MapObject(GridArea area) {
        if (area == null) {
            throw new IllegalArgumentException("GridArea must not be null");
        }
        this.area = area;
    }

}
