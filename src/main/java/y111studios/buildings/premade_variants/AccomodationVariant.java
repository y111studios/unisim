package y111studios.buildings.premade_variants;

import lombok.Getter;

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
public enum AccomodationVariant {
    SMALL_HOUSE(1, 1);

    private final @Getter int width;
    private final @Getter int height;

    AccomodationVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
