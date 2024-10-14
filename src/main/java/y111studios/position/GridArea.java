package y111studios.position;

import lombok.Getter;

public class GridArea {

    private @Getter GridPosition origin;
    private @Getter int width;
    private @Getter int height;

    public GridArea(GridPosition origin, int width, int height) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin must not be null");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.origin = origin;
        this.width = width;
        this.height = height;
    }

    public GridArea(int x, int y, int width, int height) {
        this(new GridPosition(x, y), width, height);
    }

}
