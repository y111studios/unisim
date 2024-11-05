package y111studios.buildings;

import y111studios.buildings.premade_variants.RecreationVariant;
import y111studios.position.GridPosition;

/**
 * A class representing an recreational building within the game. This class extends the
 * {@link Building} class.
 * 
 * @see Building
 */
public class RecreationBuilding extends Building {

    public RecreationBuilding(GridPosition position, RecreationVariant variant) {
        super(position, variant.getWidth(), variant.getHeight());
    }

}
