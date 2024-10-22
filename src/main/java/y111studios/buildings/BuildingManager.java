package y111studios.buildings;

/**
 * A manager class to maintain the buildings and the count of them.
 * <p>
 * This class provides functionality to place buildings and keep track of the number of buildings.
 * The maximum number of buildings that can be placed is {@link #MAX_BUILDINGS MAX_BUILDINGS}
 * ({@value #MAX_BUILDINGS}). This is a fixed size array of buildings, helped by a the building
 * counter.
 * </p>
 * 
 * @see BuildingCounter
 */
public class BuildingManager {

    /**
     * The maximum number of buildings that can be placed.
     */
    private final static int MAX_BUILDINGS = 125;

    /**
     * Fixed sized array containing the buildings.
     * <p>
     * This will always contain exactly {@link #MAX_BUILDINGS MAX_BUILDINGS}
     * ({@value #MAX_BUILDINGS}) elements
     * </p>
     */
    private Building[] buildings;
    /**
     * The building counter to keep track of the number of buildings placed.
     */
    private BuildingCounter counter;

    /**
     * Initializes the building manager with a null-initialized array of buildings and a building
     * counter.
     */
    public BuildingManager() {
        this.buildings = new Building[MAX_BUILDINGS];
        this.counter = new BuildingCounter();
    }

}
