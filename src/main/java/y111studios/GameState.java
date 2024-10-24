package y111studios;

import y111studios.buildings.BuildingManager;
import y111studios.clock.Clock;
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
public class GameState {

    Clock clock;
    BuildingManager buildingManager;
    CollisionDetection collisionDetection;

    /**
     * Constructor for the GameState class. Initializes
     * 
     * @param width width of the game map
     * @param height height of the game map
     */
    public GameState(int width, int height) {
        clock = new Clock();
        buildingManager = new BuildingManager();
        collisionDetection = new CollisionDetection(width, height);
    }

}
