package y111studios;

import java.time.Duration;
import y111studios.buildings.BuildingManager;
import y111studios.clock.Clock;
import y111studios.clock.GameTimer;
import y111studios.map.CollisionDetection;

/**
 * A class representing the sum state of the game. This class contains the clock, building manager
 * and collision detection. This class is used to manage the state of the game by exposing the
 * necessary methods to alter the state of the game in a controlled manner.
 * 
 * @see Clock
 * @see BuildingManager
 * @see CollisionDetection
 */
public class GameState implements GameTimer {

    private GameTimer timer;
    BuildingManager buildingManager;
    CollisionDetection collisionDetection;

    /**
     * Constructor for the GameState class. Initializes
     * 
     * @param width width of the game map
     * @param height height of the game map
     */
    public GameState(int width, int height) {
        timer = new Clock();
        buildingManager = new BuildingManager();
        collisionDetection = new CollisionDetection(width, height);
    }

    // GameTimer methods

    @Override
    public boolean isPaused() {
        return timer.isPaused();
    }

    @Override
    public void pause() {
        timer.pause();
    }

    @Override
    public void resume() {
        timer.resume();
    }

    @Override
    public boolean isTimeUp() {
        return timer.isTimeUp();
    }

    @Override
    public Duration timeRemaining() {
        return timer.timeRemaining();
    }

}
