package y111studios.buildings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingCounterTest {
    
    private BuildingCounter buildingCounter;

    /**
     * Initializes a new instance of BuildingCounter before each test, ensuring that
     * each test runs with a fresh counter.
     */
    @BeforeEach
    void setUp() {
        buildingCounter = new BuildingCounter();
    }

    /**
     * Tests that all the building counts and the total count are initially zero when
     * a new counter is created.
     */
    @Test
    void testInitialCount() {

        assertEquals(0, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.ACCOMMODATION));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.CATERING));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.RECREATION));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.TEACHING));
        assertEquals(0, buildingCounter.getTotalCount());

    }

    /**
     * Tests the placing of one of each building type, and verifies that the counts are updated correctly.
     */
    @Test
    void testPlaceBuilding() {
        
        buildingCounter.placeBuilding(BuildingCounter.BuildingType.ACCOMMODATION);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.ACCOMMODATION));
        assertEquals(1, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingCounter.BuildingType.CATERING);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.CATERING));
        assertEquals(2, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingCounter.BuildingType.RECREATION);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.RECREATION));
        assertEquals(3, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingCounter.BuildingType.TEACHING);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.TEACHING));
        assertEquals(4, buildingCounter.getTotalCount());

    }

    /**
     * Tests the placing of multiple buildings of the same type, and verifies that the
     * counts are updated correctly.
     */
    @Test
    void testMultipleBuildings() {

        buildingCounter.placeBuilding(BuildingCounter.BuildingType.ACCOMMODATION);
        buildingCounter.placeBuilding(BuildingCounter.BuildingType.ACCOMMODATION);
        assertEquals(2, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.ACCOMMODATION));
        assertEquals(2, buildingCounter.getTotalCount());

        buildingCounter.placeBuilding(BuildingCounter.BuildingType.CATERING);
        buildingCounter.placeBuilding(BuildingCounter.BuildingType.CATERING);
        buildingCounter.placeBuilding(BuildingCounter.BuildingType.CATERING);        
        assertEquals(3, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.CATERING));
        assertEquals(5, buildingCounter.getTotalCount());

    }

    /**
     * Tests the reset functionality of the counter by placing some buildings, and
     * then resetting the counter and verifying that all counts return to zero.
     */
    @Test
    void testReset() {

        buildingCounter.placeBuilding(BuildingCounter.BuildingType.RECREATION);
        buildingCounter.placeBuilding(BuildingCounter.BuildingType.TEACHING);
        assertEquals(1, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.RECREATION));
        assertEquals(1, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.TEACHING));
        assertEquals(2, buildingCounter.getTotalCount());

        // Reset the counter.
        buildingCounter.reset();
        assertEquals(0, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.RECREATION));
        assertEquals(0, buildingCounter.getBuildingCount(BuildingCounter.BuildingType.TEACHING));
        assertEquals(0, buildingCounter.getTotalCount());

    }

}