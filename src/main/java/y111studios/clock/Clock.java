package y111studios.clock;

import java.time.Instant;
import java.time.Duration;

/**
 * A clock object to keep track of the time elapsed since its creation.
 * 
 * <p>
 * The clock implements the GameTimer interface so shares the maximum duration of
 * {@value GameTimer#MAX_SECONDS} seconds. After this time has elapsed, the clock will return true
 * when the {@link #isTimeUp()} method is called.
 * </p>
 * 
 * @see GameTimer
 */
public class Clock implements GameTimer {

    private Duration totalDuration;
    private boolean paused;
    private Instant start;

    /**
     * Initializes the clock to being initially paused.
     */
    public Clock() {
        this.totalDuration = Duration.ZERO;
        this.paused = true;
        this.start = null;
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

    // Private helper methods

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

    // Public interface methods

    /**
     * Returns if the clock is paused.
     * 
     * @return True if the clock is paused, false otherwise.
     */
    @Override
    public boolean isPaused() {
        return paused;
    }

    /**
     * Returns whether the maximum duration of the clock has been reached.
     * 
     * @return True if the maximum duration has been reached, false otherwise.
     */
    @Override
    public boolean isTimeUp() {
        return totalElapsedTime().compareTo(MAX_DURATION) >= 0;
    }

    /**
     * Returns the time remaining on the clock.
     * 
     * @return Duration representing the time remaining on the clock.
     */
    @Override
    public Duration timeRemaining() {
        return MAX_DURATION.minus(totalElapsedTime());
    }

    /**
     * Pauses the clock.
     * 
     * <p>
     * If the clock is already paused, this method does nothing.
     * </p>
     */
    @Override
    public void pause() {
        if (paused) {
            return;
        }
        Duration session = elapsedSessionTime();
        totalDuration = totalDuration.plusNanos(session.toNanos());
        paused = true;
        start = null;
    }

    /**
     * Resumes the clock.
     * 
     * <p>
     * If the clock is not paused, this method does nothing.
     * </p>
     */
    @Override
    public void resume() {
        if (!paused) {
            return;
        }
        start = Instant.now();
        paused = false;
    }

}
