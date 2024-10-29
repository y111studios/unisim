package y111studios.map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CollisionDetectionTest {

    @Test
    void canPlaceBuilding() {
        CollisionDetection cd = new CollisionDetection(10, 10);
        // Typical case that should work
        assertTrue(cd.canPlaceBuilding(1, 1, 3, 3));
        // Case of x being out of bounds
        assertDoesNotThrow(() -> cd.canPlaceBuilding(10, 8, 1, 1));
        assertFalse(cd.canPlaceBuilding(10, 8, 1, 1));
        // Case of y being out of bounds
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, 10, 1, 1));
        assertFalse(cd.canPlaceBuilding(10, 8, 1, 1));
        // Case of x + width being out of bounds
        assertDoesNotThrow(() -> cd.canPlaceBuilding(9, 8, 10, 1));
        assertFalse(cd.canPlaceBuilding(10, 8, 1, 1));
        // Case of y + height being out of bounds
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, 9, 1, 10));
        assertFalse(cd.canPlaceBuilding(10, 8, 1, 1));
        // Case of negative x
        assertDoesNotThrow(() -> cd.canPlaceBuilding(-1, 8, 1, 1));
        assertFalse(cd.canPlaceBuilding(-1, 8, 1, 1));
        // Case of negative y
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, -1, 1, 1));
        assertFalse(cd.canPlaceBuilding(8, -1, 1, 1));
        // Case of negative width
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, 8, -1, 1));
        assertFalse(cd.canPlaceBuilding(8, 8, -1, 1));
        // Case of negative height
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, 8, 1, -1));
        assertFalse(cd.canPlaceBuilding(8, 8, 1, -1));
        // Case of 0 width
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, 8, 0, 1));
        assertFalse(cd.canPlaceBuilding(8, 8, 0, 1));
        // Case of 0 height
        assertDoesNotThrow(() -> cd.canPlaceBuilding(8, 8, 1, 0));
        assertFalse(cd.canPlaceBuilding(8, 8, 1, 0));
    }

}
