package y111studios.buildings.premade_variants;

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
     * Returns the class object of the variant.
     * 
     * @return the class of the variant
     */
    Class<? extends Enum<?>> getVariantClass();
}
