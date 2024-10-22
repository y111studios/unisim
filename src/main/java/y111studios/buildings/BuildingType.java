package y111studios.buildings;

public enum BuildingType {
    ACCOMMODATION, CATERING, RECREATION, TEACHING;

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
