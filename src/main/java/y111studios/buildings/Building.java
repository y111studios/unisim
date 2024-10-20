package y111studios.buildings;

import y111studios.position.GridArea;

/**
 * A class representing a building within the game. This class extends the
 * {@link MapObject} class.
 * 
 * @see MapObject
 */
public abstract class Building extends MapObject {

    public Building(GridArea area) {
        super(area);
    }

}
