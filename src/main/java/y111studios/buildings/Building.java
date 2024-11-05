package y111studios.buildings;

import y111studios.position.GridArea;
import y111studios.position.GridPosition;

/**
 * A class representing a building within the game. This class extends the {@link MapObject} class.
 * 
 * @see MapObject
 */
public abstract class Building extends MapObject {

    protected Building(GridArea area) {
        super(area);
    }

    protected Building(GridPosition position, int width, int height) {
        this(new GridArea(position, width, height));
    }

}
