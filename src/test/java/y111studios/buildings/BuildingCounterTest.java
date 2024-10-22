package y111studios.buildings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingCounterTest {
    
    private BuildingCounter buildingCounter;

    @BeforeEach
    void setUp() {
        buildingCounter = new BuildingCounter();
    }

    @Test
    void testInitialCount() {

        assertEquals(0, buildingCounter.getBuildingCount(BuildingType.ACCOMMODATION));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingType.CATERING));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingType.RECREATION));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingType.TEACHING));
        assertEquals(0, buildingCounter.getTotalCount());

    }

    @Test
    void testPlaceBuilding() {
        
        buildingCounter.placeBuilding(BuildingType.ACCOMMODATION);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingType.ACCOMMODATION));
        assertEquals(1, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingType.CATERING);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingType.CATERING));
        assertEquals(2, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingType.RECREATION);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingType.RECREATION));
        assertEquals(3, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingType.TEACHING);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingType.TEACHING));
        assertEquals(4, buildingCounter.getTotalCount());

    }

    @Test
    void testMultipleBuildings() {

        buildingCounter.placeBuilding(BuildingType.ACCOMMODATION);
        buildingCounter.placeBuilding(BuildingType.ACCOMMODATION);
        assertEquals(2, buildingCounter.getBuildingCount(BuildingType.ACCOMMODATION));
        assertEquals(2, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingType.CATERING);
        buildingCounter.placeBuilding(BuildingType.CATERING);
        buildingCounter.placeBuilding(BuildingType.CATERING);        
        assertEquals(3, buildingCounter.getBuildingCount(BuildingType.CATERING));
        assertEquals(5, buildingCounter.getTotalCount());

    }

    @Test
    void testReset() {

        buildingCounter.placeBuilding(BuildingType.RECREATION);
        buildingCounter.placeBuilding(BuildingType.TEACHING);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingType.RECREATION));
        assertEquals(1, buildingCounter.getBuildingCount(BuildingType.TEACHING));
        assertEquals(2, buildingCounter.getTotalCount());

        buildingCounter.reset();
        assertEquals(0, buildingCounter.getBuildingCount(BuildingType.RECREATION));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingType.TEACHING));
        assertEquals(0, buildingCounter.getTotalCount());

    }

}