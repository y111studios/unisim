package y111studios.buildings.premade_variants;

import lombok.Getter;

/**
 * An enum representing the different predefined variants of teaching buildings. Each variant has
 * contains these fields:
 * <ul>
 * <li>{@code width} - The width of the building.</li>
 * <li>{@code height} - The height of the building.</li>
 * </ul>
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.TeachingBuilding
 * TeachingBuilding}.
 * 
 * <h3>Example usage:</h3>
 * 
 * <pre>
 * TeachingVariant smallClassroom = TeachingVariant.SMALL_CLASSROOM;
 * int width = smallClassroom.getWidth();
 * int height = smallClassroom.getHeight();
 * </pre>
 * 
 * 
 * <h3>Enum Constants:</h3>
 * <ul>
 * <li>{@link #SMALL_CLASSROOM SMALL_CLASSROOM}</li>
 * </ul>
 */
public enum TeachingVariant {
    SMALL_CLASSROOM(2, 2);

    private final @Getter int width;
    private final @Getter int height;

    TeachingVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
