package y111studios.utils;

import lombok.Getter;

/**
 * Utility object that represents a range of integers from start to end. The range is inclusive of
 * the start value and exclusive to the end value. This class is immutable.
 */
public class IntRange {

    /**
     * The start value of the range (inclusive).
     */
    private @Getter final int start;
    /**
     * The end value of the range (exclusive).
     */
    private @Getter final int end;

    /**
     * Creates a new range with the specified start and end values.
     * 
     * @param start first value in the range (inclusive)
     * @param end last value in the range (exclusive)
     * 
     * @throws IllegalArgumentException if the start value is greater than the end value
     */
    public IntRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start must be less than or equal to end");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if the specified value is within the range.
     * 
     * @param value the value to check
     * @return true if the value is within the range, false otherwise
     */
    public boolean contains(int value) {
        return value >= start && value < end;
    }

}
