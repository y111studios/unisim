package y111studios.position;

import lombok.Getter;

public class GridPosition {

    private @Getter int x;
    private @Getter int y;

    public GridPosition(int x, int y) {
        if (x < 0) {
            throw new IllegalArgumentException("X must not be negative");
        }
        if (y < 0) {
            throw new IllegalArgumentException("Y must not be negative");
        }
        this.x = x;
        this.y = y;
    }

}
