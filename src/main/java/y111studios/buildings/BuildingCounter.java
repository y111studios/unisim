package y111studios.buildings;

import java.util.HashMap;
import java.util.Map;

/**
 * A building counter to keep track of the number of buildings placed on the university campus.
 */
public class BuildingCounter {

    private final Map<BuildingType, Integer> buildingMap; // A dictionary to store the count of each building type.
    private int count; // A field to track the total number of buildings

    /**
     * Initializes the building counter with the numbers of all building types set to 0.
     */
    public BuildingCounter() {
        buildingMap = new HashMap<>();
        for (BuildingType type : BuildingType.values()) {
            buildingMap.put(type, 0);
        }
        count = 0; // Initialise the total number of buildings to be 0.
    }

    /**
     * Increments the count for the specified building type and the total count.
     * 
     * @param type The type of building to increment.
     */
    public void placeBuilding(BuildingType type) {
        buildingMap.put(type, buildingMap.get(type) + 1);
        count++; // Increment the total number of buildings when one is placed.
    }

    /**
     * Returns the count for the specified building type.
     * 
     * @param type The type of building to retrieve the count for.
     * @return The count for the specified building type.
     */
    public int getBuildingCount(BuildingType type) {
        return buildingMap.get(type);
    }

    /**
     * Returns the total number of buildings placed.
     * 
     * @return The total number of buildings placed.
     */
    public int getTotalCount() {
        return count;
    }

    /**
     * Resets the building counter to its initial state, setting all building counts to 0 and the total buildings placed to 0.
     */
    public void reset() {
        for (BuildingType type : BuildingType.values()) {
            buildingMap.put(type, 0);
        }
        count = 0;
    }
    
}