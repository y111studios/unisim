package y111studios.buildings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
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

    private void fillManager() {
        while (manager.isFull() == false) {
            manager.push(building);
        }
        assertTrue(manager.isFull());
    }

    private void pushToManager(int count) {
        for (int i = 0; i < count; i++) {
            manager.push(building);
        }
        assertTrue(manager.getCount() == count);
    }

    @Test
    void pushNull() {
        assertTrue(manager.push(null));
        assertTrue(manager.isEmpty());
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

    @Test
    void removeIndex() {
        // Setup the manager with 5 buildings
        pushToManager(5);

        // Bounds check
        assertFalse(manager.removeIndex(-1));
        assertFalse(manager.removeIndex(1000));
        assertEquals(5, manager.getCount()); // Removal should fail

        // Remove the building at index 2
        assertTrue(manager.removeIndex(2));
        assertEquals(4, manager.getCount()); // Removal should be successful

        // Remove the building at the end
        assertTrue(manager.removeIndex(3));
        assertEquals(3, manager.getCount()); // Removal should be successful

        // Remove the building at the start
        assertTrue(manager.removeIndex(0));
        assertEquals(2, manager.getCount()); // Removal should be successful
    }

    @Test
    void removeFromFullManager() {
        // Fill the manager
        fillManager();

        // Remove the building at index 2
        assertTrue(manager.removeIndex(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1000})
    void removeFromEmptyManager(int index) {
        // Removing from an empty manager should not throw an exception
        assertFalse(manager.removeIndex(index));
        assertTrue(manager.isEmpty()); // This should be a no-op
    }

    @Test
    void exhaustivelyRemove() {
        // Setup the manager with 10 buildings
        pushToManager(10);

        // Remove all buildings
        for (int i = 0; i < 10; i++) {
            assertTrue(manager.removeIndex(0));
        }
        assertEquals(0, manager.getCount());
    }

    @Test
    void isEmpty() {
        assertTrue(manager.isEmpty());
        pushToManager(1);
        assertFalse(manager.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("providePositionsAndResults")
    void removePosition(GridPosition position, boolean result) {
        pushToManager(1);
        assertEquals(manager.removePosition(position), result);
    }

    static Stream<Arguments> providePositionsAndResults() {
        return Stream.of(Arguments.of(null, false), Arguments.of(new GridPosition(0, 0), true),
                Arguments.of(new GridPosition(1, 1), false),
                Arguments.of(new GridPosition(1, 0), false),
                Arguments.of(new GridPosition(0, 1), false));
    }

    @Test
    void getBuilding() {
        pushToManager(1);

        assertEquals(manager.getBuilding(ZERO), building);
        assertEquals(manager.getBuilding(new GridPosition(1, 1)), null);
    }

}
