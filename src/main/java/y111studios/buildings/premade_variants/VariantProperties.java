package y111studios.buildings.premade_variants;

import y111studios.AssetPaths;

/**
 * An interface representing the properties that all variants of a
 * {@link y111studios.buildings.Building Building} must have. This interface is used as a type bound
 * for generic variants to construct a building.
 */
public interface VariantProperties {
    /**
     * Returns the width of the variant.
     * 
     * @return the width of the variant
     */
    int getWidth();

    /**
     * Returns the height of the variant.
     * 
     * @return the height of the variant
     */
    int getHeight();

    /**
     * Get the texture of the variant.
     *
     * @return the texture of the variant
     */
    AssetPaths getTexturePath();

    /**
     * Returns the class object of the variant.
     * 
     * @return the class of the variant
     */
    Class<? extends VariantProperties> getVariantClass();
}
