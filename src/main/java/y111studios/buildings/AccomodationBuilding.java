package y111studios.buildings;

import y111studios.buildings.premade_variants.AccomodationVariant;
import y111studios.position.GridPosition;

/**
 * A class representing an accomodation building within the game. This class extends the
 * {@link Building} class.
 * 
 * @see Building
 */
public class AccomodationBuilding extends Building {

    /**
     * Creates a new accomodation building at the given position of the given variant.
     * 
     * @param position The position of the building
     * @param variant The variant type of the building
     */
    public AccomodationBuilding(GridPosition position, AccomodationVariant variant) {
        super(position, variant);
    }

}
