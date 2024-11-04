package y111studios.position;

import lombok.Getter;
import y111studios.utils.IntRange;

public class GridArea {

    private @Getter GridPosition origin;
    private @Getter int width;
    private @Getter int height;

    /**
     * The continuous range of x-coordinates that are within this GridArea. This field is
     * lazily-initialised with the first call to {@link #getXRange()}.
     */
    private IntRange xRange = null;
    /**
     * The continuous range of y-coordinates that are within this GridArea. This field is
     * lazily-initialised with the first call to {@link #getYRange()}.
     */
    private IntRange yRange = null;

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

    /**
     * Gets the x-coordinate of the origin of this GridArea.
     * 
     * @return the x-coordinate of the origin
     */
    public int getX() {
        return origin.getX();
    }

    /**
     * Gets the y-coordinate of the origin of this GridArea.
     * 
     * @return the y-coordinate of the origin
     */
    public int getY() {
        return origin.getY();
    }

    /**
     * Checks if the specified GridPosition is within this GridArea.
     * 
     * <p>
     * This method returns true if the x-coordinate of the other position is within the range
     * [origin.x, origin.x + width) and the y-coordinate is within the range [origin.y, origin.y +
     * height). If other is {@Code null}, this method returns false.
     * </p>
     * 
     * @param other the GridPosition to check
     * @return true if the specified position is within this area, false otherwise
     */
    public boolean contains(GridPosition other) {
        if (other == null) {
            return false;
        }
        return getXRange().contains(other.getX()) && getYRange().contains(other.getY());
    }

    /**
     * Returns the range of x-coordinates that are within the GridArea.
     * 
     * @return the range of x-coordinates
     */
    public IntRange getXRange() {
        if (xRange == null) {
            xRange = calculateXRange();
        }
        return xRange;
    }

    /**
     * Returns the range of y-coordinates that are within the GridArea.
     * 
     * @return the range of y-coordinates
     */
    public IntRange getYRange() {
        if (yRange == null) {
            yRange = calculateYRange();
        }
        return yRange;
    }

    /**
     * Returns a new instance of IntRange that represents the x-coordinates of this GridArea.
     * 
     * @return the range of x-coordinates
     */
    private IntRange calculateXRange() {
        return new IntRange(origin.getX(), origin.getX() + width);
    }

    /**
     * Returns a new instance of IntRange that represents the y-coordinates of this GridArea.
     * 
     * @return the range of y-coordinates
     */
    private IntRange calculateYRange() {
        return new IntRange(origin.getY(), origin.getY() + height);
    }

}
