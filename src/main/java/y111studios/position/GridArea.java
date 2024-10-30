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

    public int getX() {
        return origin.getX();
    }

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
        GridPosition end = getEndPosition();
        return other.getX() >= origin.getX() && other.getX() < end.getX()
                && other.getY() >= origin.getY() && other.getY() < end.getY();
    }

    /**
     * Gets the GridPosition for the bottom-right corner of this area.
     * 
     * @return GridPosition representing the opposite corner to the origin
     */
    private GridPosition getEndPosition() {
        return new GridPosition(origin.getX() + width, origin.getY() + height);
    }

}
