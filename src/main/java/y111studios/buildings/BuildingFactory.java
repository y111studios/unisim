package y111studios.buildings;

import y111studios.buildings.premade_variants.AccomodationVariant;
import y111studios.buildings.premade_variants.CateringVariant;
import y111studios.buildings.premade_variants.RecreationVariant;
import y111studios.position.GridArea;
import y111studios.position.GridPosition;

public final class BuildingFactory {

    private BuildingFactory() {
        // Prevent instatiation of Factory class
    }

    public static AccomodationBuilding createAccomodationBuilding(AccomodationVariant variant,
            GridPosition position) {
        if (variant == null) {
            throw new IllegalArgumentException("AccomodationVariant must not be null");
        }
        if (position == null) {
            throw new IllegalArgumentException("GridPosition must not be null");
        }
        GridArea area = new GridArea(position, variant.getWidth(), variant.getHeight());
        return new AccomodationBuilding(area);
    }

    public static CateringBuilding createCateringBuilding(CateringVariant variant,
            GridPosition position) {
        if (variant == null) {
            throw new IllegalArgumentException("CateringVariant must not be null");
        }
        if (position == null) {
            throw new IllegalArgumentException("GridPosition must not be null");
        }
        GridArea area = new GridArea(position, variant.getWidth(), variant.getHeight());
        return new CateringBuilding(area);
    }

    public static RecreationBuilding createRecreationBuilding(RecreationVariant variant,
            GridPosition position) {
        if (variant == null) {
            throw new IllegalArgumentException("RecreationVariant must not be null");
        }
        if (position == null) {
            throw new IllegalArgumentException("GridPosition must not be null");
        }
        GridArea area = new GridArea(position, variant.getWidth(), variant.getHeight());
        return new RecreationBuilding(area);
    }

}
