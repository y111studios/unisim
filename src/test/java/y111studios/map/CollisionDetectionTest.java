package y111studios.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CollisionDetectionTest {

    @ParameterizedTest
    @MethodSource("provideCanPlaceBuildingCases")
    void canPlaceBuilding(CollisionDetection cd, int x, int y, int width, int height,
            boolean expected) {
        assertEquals(expected, cd.canPlaceBuilding(x, y, width, height));
    }

    private static Stream<Arguments> provideCanPlaceBuildingCases() {
        CollisionDetection square = new CollisionDetection(10, 10);
        Stream<Arguments> squareCases = Stream.of(
            Arguments.of(square, 1, 1, 3, 3, true), // Typical case
            Arguments.of(square, 0, 0, 4, 4, true), // Case: start corner of map
            Arguments.of(square, 9, 9, 1, 1, true), // Case: end corner of map
            Arguments.of(square, 10, 8, 1, 1, false), // Case: x out of bounds
            Arguments.of(square, 8, 10, 1, 1, false), // Case: y out of bounds
            Arguments.of(square, 9, 8, 10, 1, false), // Case: x + width out of bounds
            Arguments.of(square, 8, 9, 1, 10, false), // Case: y + height out of bounds
            Arguments.of(square, -1, 8, 1, 1, false), // Case: negative x
            Arguments.of(square, 8, -1, 1, 1, false), // Case: negative y
            Arguments.of(square, 8, 8, -1, 1, false), // Case: negative width
            Arguments.of(square, 8, 8, 1, -1, false), // Case: negative height
            Arguments.of(square, 8, 8, 0, 1, false), // Case: 0 width
            Arguments.of(square, 8, 8, 1, 0, false) // Case: 0 height
        );
        CollisionDetection yRect = new CollisionDetection(10, 20);
        Stream<Arguments> yRectCases = Stream.of(
            Arguments.of(yRect, 1, 1, 3, 3, true), // Typical case
            Arguments.of(yRect, 4, 15, 4, 3, true), // Case: correct check of y vs. height
            Arguments.of(yRect, 15, 6, 2, 3, false) // Case: correct check of x vs. width
        );
        CollisionDetection xRect = new CollisionDetection(20, 10);
        Stream<Arguments> xRectCases = Stream.of(
            Arguments.of(xRect, 1, 1, 3, 3, true), // Typical case
            Arguments.of(xRect, 15, 4, 3, 4, true), // Case: correct check of y vs. height
            Arguments.of(xRect, 6, 15, 3, 2, false) // Case: correct check of x vs. width
        );
        Stream<Arguments> rectCases = Stream.concat(xRectCases, yRectCases);
        return Stream.concat(squareCases, rectCases);
    }

}
