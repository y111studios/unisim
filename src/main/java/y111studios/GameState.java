package y111studios;

import java.time.Duration;
import y111studios.buildings.Building;
import y111studios.buildings.BuildingController;
import y111studios.buildings.BuildingManager;
import y111studios.clock.Clock;
import y111studios.clock.GameTimer;
import y111studios.map.CollisionDetection;
import y111studios.position.GridPosition;

/**
 * A class representing the sum state of the game. This class contains the clock, building manager
 * and collision detection. This class is used to manage the state of the game by exposing the
 * necessary methods to alter the state of the game in a controlled manner.
 * 
 * @see Clock
 * @see BuildingManager
 * @see CollisionDetection
 */
public class GameState implements GameTimer, BuildingController {

    private GameTimer timer;
    BuildingManager buildingManager;
    CollisionDetection collisionDetection;

    /**
     * Constructor for the GameState class.
     * 
     * @param width width of the game map
     * @param height height of the game map
     */
    public GameState(int width, int height) {
        timer = new Clock();
        buildingManager = new BuildingManager();
        collisionDetection = new CollisionDetection(width, height);
    }

    // BuildingController methods

    @Override
    public Building getBuilding(GridPosition position) {
        if (position == null) {
            return null;
        }
        if (collisionDetection.canPlaceBuilding(position)) {
            return null;
        }
        return this.buildingManager.getBuilding(position);
    }

    @Override
    public boolean push(Building building) {
        if (building == null) {
            return true;
        }
        // Cannot push into a full building manager
        if (buildingManager.isFull()) {
            return false;
        }
        // Cannot push if the building area is not empty
        if (collisionDetection.canPlaceBuilding(building.getArea()) == false) {
            return false;
        }
        // Push the building into the building mangager
        this.buildingManager.push(building);
        return true;
    }

    @Override
    public boolean removePosition(GridPosition position) {
        if (position == null) {
            return false;
        }
        // Check if the origin point is used
        if (collisionDetection.canPlaceBuilding(position)) {
            return false;
        }
        // Get building being removed
        final Building building = this.buildingManager.getBuilding(position);
        if (building == null) {
            // This should never happen provided push is correctly implemented
            throw new IllegalStateException("Building not found at position: " + position);
        }
        // Remove the building from the collision detection
        this.collisionDetection.removeBuilding(building.getArea());
        this.buildingManager.removePosition(position);
        return true;
    }

    @Override
    public int getCount() {
        return this.buildingManager.getCount();
    }

    @Override
    public boolean isFull() {
        return this.buildingManager.isFull();
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
