package y111studios.buildings.premade_variants;

import y111studios.buildings.CateringBuilding;
import y111studios.buildings.Building;

/**
 * An enum representing the different predefined variants of catering buildings. Each variant has
 * contains these fields:
 * <ul>
 * <li>{@code width} - The width of the building.</li>
 * <li>{@code height} - The height of the building.</li>
 * </ul>
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.CateringBuilding
 * CateringBuilding}.
 * 
 * <h3>Example usage:</h3>
 * 
 * <pre>
 * CateringVariant fastFood = CateringVariant.FAST_FOOD;
 * int width = fastFood.getWidth();
 * int height = fastFood.getHeight();
 * </pre>
 * 
 * 
 * <h3>Enum Constants:</h3>
 * <ul>
 * <li>{@link #FAST_FOOD FAST_FOOD}</li>
 * </ul>
 */
public enum CateringVariant implements VariantProperties {
    FAST_FOOD(2, 1);

    private final static Class<? extends Building> buildingClass = CateringBuilding.class;

    private final int width;
    private final int height;

    CateringVariant(int width, int height) {
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
}
