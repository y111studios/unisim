package y111studios.buildings;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


import y111studios.buildings.premade_variants.AccomodationVariant;
import y111studios.buildings.premade_variants.CateringVariant;
import y111studios.buildings.premade_variants.RecreationVariant;
import y111studios.position.GridPosition;

public class BuildingFactoryTest {

    private static final GridPosition DEFAULT_POSITION = new GridPosition(0, 0);

    @Nested
    @Tag("Constructor")
    class ConstructorTests {

        @Test
        void preventNullCreation() {
            assertThrows(IllegalArgumentException.class, () -> {
                BuildingFactory.createAccomodationBuilding(null, DEFAULT_POSITION);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                BuildingFactory.createAccomodationBuilding(AccomodationVariant.SMALL_HOUSE, null);
            });
        }

        @ParameterizedTest
        @EnumSource(value = AccomodationVariant.class)
        void accomodationBuildingCreation(AccomodationVariant variant) {
            assertDoesNotThrow(() -> {
                BuildingFactory.createAccomodationBuilding(variant, DEFAULT_POSITION);
            });
        }

        @ParameterizedTest
        @EnumSource(value = CateringVariant.class)
        void cateringBuildingCreation(CateringVariant variant) {
            assertDoesNotThrow(() -> {
                BuildingFactory.createCateringBuilding(variant, DEFAULT_POSITION);
            });
        }

        @ParameterizedTest
        @EnumSource(value = RecreationVariant.class)
        void recreationBuildingCreation(RecreationVariant variant) {
            assertDoesNotThrow(() -> {
                BuildingFactory.createRecreationBuilding(variant, DEFAULT_POSITION);
            });
        }

    }

}
