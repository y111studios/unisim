package y111studios.buildings;

import y111studios.buildings.premade_variants.CateringVariant;
import y111studios.position.GridPosition;

/**
 * A class representing an catering building within the game. This class extends the
 * {@link Building} class.
 * 
 * @see Building
 */
public class CateringBuilding extends Building {

    public CateringBuilding(GridPosition position, CateringVariant variant) {
        super(position, variant);
    }

}
