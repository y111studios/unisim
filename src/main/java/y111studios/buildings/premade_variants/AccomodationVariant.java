package y111studios.buildings.premade_variants;

/**
 * An enum representing the different predefined variants an
 * {@link y111studios.buildings.AccomodationBuilding AccomodationBuilding}.
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.AccomodationBuilding
 * AccomodationBuilding}.
 */
public enum AccomodationVariant implements VariantProperties {
    SMALL_HOUSE(1, 1);

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
    public Class<? extends VariantProperties> getVariantClass() {
        return AccomodationVariant.class;
    }

}
