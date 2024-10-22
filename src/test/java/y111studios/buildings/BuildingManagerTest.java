package y111studios.buildings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import y111studios.buildings.premade_variants.AccomodationVariant;
import y111studios.position.GridPosition;

public class BuildingManagerTest {

    private BuildingManager manager;

    final static GridPosition ZERO = new GridPosition(0, 0);
    final static Building building =
            BuildingFactory.createAccomodationBuilding(AccomodationVariant.SMALL_HOUSE, ZERO);

    @BeforeEach
    void setUp() {
        manager = new BuildingManager();
    }

    @Test
    void pushNull() {
        assertTrue(manager.push(null));
        assertEquals(0, manager.getCount());
    }

    @Test
    void pushBuilding() {
        int count = 0;
        while (manager.isFull() == false) {
            manager.push(building);
            assertEquals(manager.getCount(), ++count);
        }
        assertTrue(manager.isFull());
        assertFalse(manager.push(building));
    }

}
