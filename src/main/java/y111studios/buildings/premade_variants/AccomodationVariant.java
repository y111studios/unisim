package y111studios.buildings.premade_variants;

import lombok.Getter;

public enum AccomodationVariant {
    SMALL_HOUSE(1, 1);

    private final @Getter int width;
    private final @Getter int height;

    AccomodationVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
