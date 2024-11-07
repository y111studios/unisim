package y111studios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 * A class to interact with LibGDX to render the game window.
 */
public class MapScreen extends ScreenAdapter {

    /**
     * Proportional width of the display.
     */
    static final int WIDTH = 640;
    /**
     * Proportional height of the display.
     */
    static final int HEIGHT = 480;
    /**
     * The game object.
     */
    final Main game;
    /**
     * The texture for the game map.
     */
    Texture gameMap;
    /**
     * The viewport to keep proportions consistent when resizing.
     */
    FitViewport viewport;
    
    /**
     * Stores the camera position, velocity and scale.
     */
    private class Camera {

        /**
         * The camera's x coordinate.
         */
        int x;
        /**
         * The camera's y coordinate.
         */
        int y;
        /**
         * The camera's horizontal velocity.
         */
        int vx;
        /**
         * The camera's vertical velocity.
         */
        int vy;
        /**
         * The camera's scale.
         */
        float scale;

        /**
         * Initializes the camera at the given coordinates.
         */
        public Camera(int x, int y) {
            this.x = x;
            this.y = y;
            vx = 0;
            vy = 0;
            scale = 2.25f;
        }

        /**
         * Updates camera position based on velocity.
         */
        public void shift() {
            x += vx * scale;
            if(x > 5110 - WIDTH * scale) x = 5110 - (int)(WIDTH * scale);
            if(x < 0) x = 0;
            y += vy * scale;
            if(y > 2680 - HEIGHT * scale) y = 2680 - (int)(HEIGHT * scale);
            if(y < 0) y = 0;
        }

        /**
         * Changes the velocity of the camera.
         * 
         * @param vx The change to horizontal velocity.
         * @param vy The change to vertical velocity.
         */
        public void addVelocity(int vx, int vy) {
            this.vx += vx;
            this.vy += vy;
        }

    }

    /**
     * The game camera.
     */
    Camera camera;

    /**
     * Stores the information necessary to render an object.
     */
    private class MapObject {

        int x;
        int y;
        int width;
        Texture texture;

        public MapObject(int x, int y, int width, Texture texture) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.texture = texture;
        }

    }

    /**
     * The list of objects to be rendered.
     */
    ArrayList<MapObject> objects;

    public void addObject(int x, int y) {
        // to be completed when merging
    }

    /**
     * Removes an object from the game.
     * 
     * @param x The x coordinate of the object to remove.
     * @param y The y coordinate of the object to remove.
     */
    public void removeObject(int x, int y) {
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i).x == x && objects.get(i).y == y) {
                objects.remove(i);
                return;
            }
        }
    }

    /**
     * Sets up the camera and loads the background
     * 
     * @param game Reference to game manager
     */
    public MapScreen(final Main game) {
        this.game = game;
        viewport = new FitViewport(WIDTH, HEIGHT);
        gameMap = game.assetLib.manager.get(AssetPaths.MAP_BACKGROUND);
        camera = new Camera(2000, 1000);
        objects = new ArrayList<MapObject>();
        objects.add(new MapObject(3, 1, 3, game.assetLib.manager.get(AssetPaths.TEST_BUILDING)));
    }

    /**
     * Handles input for panning and zooming the camera.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.RIGHT) {
                    camera.addVelocity(8, 0);
                } else if(keyCode == Input.Keys.LEFT) {
                    camera.addVelocity(-8, 0);
                } else if(keyCode == Input.Keys.DOWN) {
                    camera.addVelocity(0, 8);
                } else if(keyCode == Input.Keys.UP) {
                    camera.addVelocity(0, -8);
                } else if(keyCode == Input.Keys.X) {
                    if(camera.scale < 5) camera.scale *= 1.5;
                } else if(keyCode == Input.Keys.Z) {
                    if(camera.scale > 0.5) camera.scale /= 1.5;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keyCode) {
                if(keyCode == Input.Keys.RIGHT) {
                    camera.addVelocity(-8, 0);
                } else if(keyCode == Input.Keys.LEFT) {
                    camera.addVelocity(8, 0);
                } else if(keyCode == Input.Keys.DOWN) {
                    camera.addVelocity(0, -8);
                } else if(keyCode == Input.Keys.UP) {
                    camera.addVelocity(0, 8);
                }
                return true;
            }
        });
    }

    /**
     * Renders the game each tick.
     * 
     * @param delta The time since the previous tick.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        viewport.apply();
        
        camera.shift();

        game.spritebatch.begin();
        game.spritebatch.draw(gameMap, 0, 0, WIDTH, HEIGHT, camera.x, camera.y, (int)(WIDTH * camera.scale), (int)(HEIGHT * camera.scale), false, false);
        objects.forEach( (o) -> {
            int x = 161 + o.x * 32 - camera.x;
            int y = -1343 + (o.x - 1 * o.y - o.width) * 16 + camera.y + (int)(HEIGHT * camera.scale);
            game.spritebatch.draw(o.texture, (int)(x / camera.scale), (int)(y / camera.scale), (int)(2 * o.texture.getWidth() / camera.scale), (int)(2 * o.texture.getHeight() / camera.scale), 0, 0, o.texture.getWidth(), o.texture.getHeight(), false, false);
        } );
        game.spritebatch.end();
    }

    /**
     * Handles resizing of the game window.
     * 
     * @param width The new width of the window.
     * @param height The new height of the window.
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * Handles hiding of the game window.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Handles closing of the game window.
     */
    @Override
    public void dispose() {
        game.dispose();
    }

}
