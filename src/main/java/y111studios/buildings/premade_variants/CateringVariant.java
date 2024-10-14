package y111studios.buildings.premade_variants;

import lombok.Getter;

public enum CateringVariant {
    FAST_FOOD(2, 1);

    private final @Getter int width;
    private final @Getter int height;

    CateringVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
