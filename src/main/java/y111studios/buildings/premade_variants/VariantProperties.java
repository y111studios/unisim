package y111studios.buildings.premade_variants;

import y111studios.buildings.Building;

public interface VariantProperties{
    int getWidth();

    int getHeight();

    Class<? extends Building> getBuildingClass();
}
