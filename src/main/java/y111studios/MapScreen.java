package y111studios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import y111studios.position.GridPosition;

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

        /**
         * The tile coordinates of the object.
         */
        GridPosition coords;
        /**
         * The width/depth of the object.
         */
        int width;
        /**
         * The texture for the object to use.
         */
        Texture texture;

        /**
         * Initializes the given object at set coordinates.
         */
        public MapObject(GridPosition coords, int width, Texture texture) {
            this.coords = coords;
            this.width = width;
            this.texture = texture;
        }

    }

    /**
     * The list of objects to be rendered.
     */
    ArrayList<MapObject> objects;

    public void addObject(GridPosition coords) {
        // to be completed when merging
    }

    /**
     * Removes an object from the game.
     * 
     * @param coords The tile coordinates of the object to remove.
     */
    public void removeObject(GridPosition coords) {
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i).coords.getX() == coords.getX() && objects.get(i).coords.getY() == coords.getY()) {
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
        viewport.getCamera().position.set(WIDTH / 2f, HEIGHT / 2f, 0);
        viewport.getCamera().update();
        gameMap = game.assetLib.manager.get(AssetPaths.MAP_BACKGROUND);
        camera = new Camera(2000, 1000);
        objects = new ArrayList<MapObject>();
        objects.add(new MapObject(new GridPosition(2, 6), 3, game.assetLib.manager.get(AssetPaths.TEST_BUILDING)));
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

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 screenPos = viewport.getCamera().unproject(new Vector3(screenX, screenY, 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
                System.out.println((int)screenPos.x + " " + (int)screenPos.y);
                GridPosition test;
                try {
                    test = pixelToTile((int)(screenPos.x * camera.scale), (int)(screenPos.y * camera.scale));
                    System.out.println(test.getX() + " " + test.getY());
                } catch(IllegalArgumentException e) {
                    System.out.println("Out of bounds!");
                }
                return true;
            }
        });
    }

    /**
     * Converts building tile coordinates to pixel coordinates. Must account for camera.scale and building width separately.
     * 
     * @param coords The tile coordinates to convert.
     * @return The pixel coordinates.
     */
    public int[] tileToPixel(GridPosition coords) {
        int pixelX = 129 + (coords.getX() + coords.getY()) * 32 - camera.x;
        int pixelY = -1343 + (coords.getX() - coords.getY()) * 16 + camera.y + (int)(HEIGHT * camera.scale);
        return new int[] {pixelX, pixelY};
    }

    /**
     * Converts pixel coordinates to tile coordinates. Must account for camera.scale separately.
     * 
     * @param x The x pixel coordinate to convert.
     * @param y The y pixel coordinate to convert.
     * @return A GridPosition containing the tile coordinates.
     * @throws IllegalArgumentException if the GridPosition is invalid.
     */
    public GridPosition pixelToTile(int x, int y) {
        int sum = (x + camera.x - 129) / 32;
        int diff = (y - camera.y - (int)(HEIGHT * camera.scale) + 1343) / 16;
        int tileY = (sum - diff) / 2;
        int tileX = sum - tileY;
        return new GridPosition(tileX, tileY);
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
            int[] pixelCoords = tileToPixel(o.coords);
            game.spritebatch.draw(o.texture, (int)(pixelCoords[0] / camera.scale), (int)((pixelCoords[1] - o.width * 16) / camera.scale), (int)(2 * o.texture.getWidth() / camera.scale), (int)(2 * o.texture.getHeight() / camera.scale), 0, 0, o.texture.getWidth(), o.texture.getHeight(), false, false);
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
