package y111studios.position;

import lombok.Getter;

/**
 * Represents a position on a grid.
 */
public class GridPosition {

    private @Getter int x;
    private @Getter int y;

    /**
     * Creates a new grid position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException if x is negative
     * @throws IllegalArgumentException if y is negative
     */
    public GridPosition(int x, int y) {
        if (x < 0) {
            throw new IllegalArgumentException("X must not be negative");
        }
        if (y < 0) {
            throw new IllegalArgumentException("Y must not be negative");
        }
        if (x > 75) {
            throw new IllegalArgumentException("X must not be greater than 75");
        }
        if (y > 75) {
            throw new IllegalArgumentException("X must not be greater than 75");
        }
        this.x = x;
        this.y = y;
    }

}
