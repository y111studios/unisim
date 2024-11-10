package y111studios.buildings.premade_variants;

import y111studios.AssetPaths;

/**
 * An enum representing the different predefined variants an
 * {@link y111studios.buildings.AccomodationBuilding AccomodationBuilding}.
 * 
 * This enum is used in conjunction with the {@link y111studios.buildings.BuildingFactory
 * BuildingFactory} class to create instances of {@link y111studios.buildings.AccomodationBuilding
 * AccomodationBuilding}.
 */
public enum AccomodationVariant implements VariantProperties {
    SMALL_HOUSE(5, 5, AssetPaths.ACC1),
    MEDIUM_HOUSE(3, 3, AssetPaths.ACC2),
    MODERN_FLAT(2, 2, AssetPaths.ACC3),
    LUXURY_FLAT(4, 4, AssetPaths.ACC4),
    PRIVATE_HOUSE(4, 4, AssetPaths.ACC5);

    private final int width;
    private final int height;
    private final AssetPaths texturePath;

    AccomodationVariant(int width, int height, AssetPaths texturePath) {
        this.width = width;
        this.height = height;
        this.texturePath = texturePath;
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
    public AssetPaths getTexturePath() {
        return texturePath;
    }

    @Override
    public Class<? extends VariantProperties> getVariantClass() {
        return AccomodationVariant.class;
    }

}
