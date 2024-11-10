package y111studios.buildings.premade_variants;

/**
 * An enum representing the different predefined variants a
 * {@link y111studios.buildings.TeachingBuilding TeachingBuilding}.
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.TeachingBuilding
 * TeachingBuilding}.
 */
public enum TeachingVariant implements VariantProperties {
    SMALL_CLASSROOM(5, 5);

    private final int width;
    private final int height;

    TeachingVariant(int width, int height) {
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
    public Class<? extends VariantProperties> getVariantClass() {
        return TeachingVariant.class;
    }

}
