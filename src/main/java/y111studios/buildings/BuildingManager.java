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
    private final static int MAX_BUILDINGS = 100;

    /**
     * Fixed sized array containing the buildings.
     * <p>
     * This will always contain exactly {@link #MAX_BUILDINGS MAX_BUILDINGS}
     * ({@value #MAX_BUILDINGS}) elements
     * </p>
     * <p>
     * This array is null-initialized and will contain null elements until a building is placed. The
     * array should not contain any null elements in the range of 0 to the total number of buildings
     * placed.
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

    /**
     * Pushes a building into the array of buildings.
     * <p>
     * This method will add the building to the array of buildings and increment the building
     * counter. This method then returns true if the building was successfully added, and false if
     * the building could not be added as the maximum number of buildings had been reached.
     * </p>
     * <p>
     * If the building is null, this method does nothing as there is no building to add and just
     * short-circuits true.
     * </p>
     * 
     * @param building The building to add to the array of buildings.
     * @return if the building was successfully added
     */
    public boolean push(Building building) {
        if (building == null) {
            return true;
        }
        final int index = counter.getTotalCount();
        if (this.isFull()) {
            return false;
        }
        buildings[index] = building;
        counter.placeBuilding(building);
        return true;
    }

    /**
     * Gets the number of buildings placed.
     * 
     * @return The number of buildings placed
     */
    public int getCount() {
        return counter.getTotalCount();
    }

    /**
     * Returns if the building manager is full.
     * 
     * <p>
     * This method returns true if the number of buildings placed is equal to {@link #MAX_BUILDINGS
     * MAX_BUILDINGS} ({@value #MAX_BUILDINGS}), and false otherwise.
     * </p>
     * 
     * @return if the building manager is full
     */
    public boolean isFull() {
        return getCount() >= MAX_BUILDINGS;
    }

}
