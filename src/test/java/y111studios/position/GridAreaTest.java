package y111studios.position;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GridAreaTest {

    @Tag("Constructor")
    @Test
    void preventNullCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GridArea(null, 1, 1);
        });
    }

    @Tag("Constructor")
    @Test
    void preventZeroSizedCreations() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GridArea(0, 0, 0, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new GridArea(0, 0, 1, 0);
        });
    }

}
