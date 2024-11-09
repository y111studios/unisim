package y111studios.buildings;

/**
 * An enumeration of the different types of buildings that can be placed.
 */
public enum BuildingType {
    ACCOMMODATION, CATERING, RECREATION, TEACHING;

    /**
     * Returns the building type of the specified building instance.
     * 
     * @param building Building instance to determine the type of.
     * @return BuildingType
     */
    public static BuildingType fromBuilding(Building building) {
        if (building instanceof AccomodationBuilding) {
            return ACCOMMODATION;
        } else if (building instanceof CateringBuilding) {
            return CATERING;
        } else if (building instanceof RecreationBuilding) {
            return RECREATION;
        } else if (building instanceof TeachingBuilding) {
            return TEACHING;
        }
        throw new IllegalArgumentException("Building type not recognised.");
    }
}
