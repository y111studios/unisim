package y111studios.buildings.premade_variants;

import lombok.Getter;

/**
 * An enum representing the different predefined variants of recreation buildings. Each variant has
 * contains these fields:
 * <ul>
 * <li>{@code width} - The width of the building.</li>
 * <li>{@code height} - The height of the building.</li>
 * </ul>
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.RecreationBuilding
 * RecreationBuilding}.
 * 
 * <h3>Example usage:</h3>
 * 
 * <pre>
 * RecreationVariant park = RecreationVariant.PARK;
 * int width = park.getWidth();
 * int height = park.getHeight();
 * </pre>
 * 
 * 
 * <h3>Enum Constants:</h3>
 * <ul>
 * <li>{@link #PARK PARK}</li>
 * </ul>
 */
public enum RecreationVariant {
    PARK(2, 2);

    private final @Getter int width;
    private final @Getter int height;

    RecreationVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
