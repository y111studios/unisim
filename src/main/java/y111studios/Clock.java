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

    private Duration totalDuration;
    private boolean paused;
    private Instant start;

    /**
     * Initializes the clock with the current time.
     */
    public Clock() {
        this(Instant.now());
    }

    /**
     * Initializes the clock with the specified start time.
     * 
     * @param start The start time of the clock.
     */
    Clock(Instant start) {
        this.totalDuration = Duration.ZERO;
        this.paused = false;
        this.start = start;
    }

    /**
     * Gets a {@link Duration} representing the elapsed time since the clock was last started. If
     * the clock is paused, this method returns 0.
     * 
     * @return The elapsed time since the clock was last started.
     */
    private Duration elapsedSessionTime() {
        Instant now = Instant.now();
        if (paused) {
            return Duration.ZERO;
        } else {
            return Duration.between(start, now);
        }
    }

    /**
     * Gets a {@link Duration} representing the total elapsed time since the clock was created,
     * excluding the time the clock was paused.
     * 
     * @return The total counted time since the clock was created.
     */
    private Duration totalElapsedTime() {
        Duration sessionTime = elapsedSessionTime();
        return totalDuration.plusNanos(sessionTime.toNanos());
    }

    /**
     * Returns whether the maximum duration of the clock has been reached.
     * 
     * @return True if the maximum duration has been reached, false otherwise.
     */
    public boolean isTimeUp() {
        return totalElapsedTime().compareTo(MAX_DURATION) >= 0;
    }

    /**
     * Pauses the clock.
     * 
     * <p>
     * If the clock is already paused, this method does nothing.
     * </p>
     */
    public void pause() {
        if (!paused) {
            Duration session = elapsedSessionTime();
            totalDuration = totalDuration.plusNanos(session.toNanos());
            paused = true;
            start = null;
        }
    }

    /**
     * Resumes the clock.
     * 
     * <p>
     * If the clock is not paused, this method does nothing.
     * </p>
     */
    public void resume() {
        if (paused) {
            start = Instant.now();
            paused = false;
        }
    }

}
