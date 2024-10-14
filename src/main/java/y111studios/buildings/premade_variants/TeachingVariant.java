package y111studios.buildings.premade_variants;

import lombok.Getter;

public enum TeachingVariant {
    SMALL_CLASSROOM(2, 2);

    private final @Getter int width;
    private final @Getter int height;

    TeachingVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
