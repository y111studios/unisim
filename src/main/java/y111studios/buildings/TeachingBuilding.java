package y111studios.buildings;

import y111studios.buildings.premade_variants.TeachingVariant;
import y111studios.position.GridPosition;

/**
 * A class representing an teaching building within the game. This class extends the
 * {@link Building} class.
 * 
 * @see Building
 */
public class TeachingBuilding extends Building {

    public TeachingBuilding(GridPosition position, TeachingVariant variant) {
        super(position, variant.getWidth(), variant.getHeight());
    }

}
