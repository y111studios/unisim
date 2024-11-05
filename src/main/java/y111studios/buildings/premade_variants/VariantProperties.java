package y111studios.buildings.premade_variants;

public interface VariantProperties{
    int getWidth();

    int getHeight();

    Class<? extends Enum<?>> getVariantClass();
}
