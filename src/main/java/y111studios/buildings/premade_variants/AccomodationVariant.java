package y111studios.buildings.premade_variants;

import y111studios.buildings.AccomodationBuilding;
import y111studios.buildings.Building;

/**
 * An enum representing the different predefined variants of accomodation buildings. Each variant
 * has contains these fields:
 * <ul>
 * <li>{@code width} - The width of the building.</li>
 * <li>{@code height} - The height of the building.</li>
 * </ul>
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.AccomodationBuilding
 * AccomodationBuilding}.
 * 
 * <h3>Example usage:</h3>
 * 
 * <pre>
 * AccomodationVariant smallHouse = AccomodationVariant.SMALL_HOUSE;
 * int width = smallHouse.getWidth();
 * int height = smallHouse.getHeight();
 * </pre>
 * 
 * 
 * <h3>Enum Constants:</h3>
 * <ul>
 * <li>{@link #SMALL_HOUSE SMALL_HOUSE}</li>
 * </ul>
 */
public enum AccomodationVariant implements VariantProperties {
    SMALL_HOUSE(1, 1);

    private final static Class<? extends Building> buildingClass = AccomodationBuilding.class;

    private final int width;
    private final int height;

    AccomodationVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Class<? extends Building> getBuildingClass() {
        return buildingClass;
    }

    @Override
    public Class<? extends Enum<?>> getVariantClass() {
        return AccomodationVariant.class;
    }

}
