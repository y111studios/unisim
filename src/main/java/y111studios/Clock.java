package y111studios;

import java.time.Instant;
import java.time.Duration;

/**
 * A clock object to keep track of the time elapsed since its creation.
 * 
 * <p>
 * The clock has a maximum duration of 5 minutes (300 seconds). After this time has elapsed, the
 * clock will return true when the {@link #isTimeUp()} method is called.
 * </p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #start} - The time at which the clock was created.</li>
 * </ul>
 * 
 * <h3>Constants</h3>
 * <ul>
 * <li>{@link #MAX_DURATION} - The maximum duration of the clock.</li>
 * </ul>
 */
public class Clock {

    /**
     * The maximum duration of the clock.
     */
    private final static Duration MAX_DURATION = Duration.ofSeconds(300);

    private Instant start;

    /**
     * Initializes the clock with the current time.
     */
    public Clock() {
        start = Instant.now();
    }

    /**
     * Gets a {@link Duration} representing the elapsed time since the clock was created.
     * 
     * @return The elapsed time since the clock was created.
     */
    private Duration getElapsedTime() {
        Instant now = Instant.now();
        return Duration.between(start, now);
    }

    /**
     * Returns whether the maximum duration of the clock has been reached.
     * 
     * @return True if the maximum duration has been reached, false otherwise.
     */
    public boolean isTimeUp() {
        return getElapsedTime().compareTo(MAX_DURATION) >= 0;
    }

}
