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
import java.util.Iterator;
import y111studios.position.GridPosition;
import y111studios.buildings.Building;
import y111studios.buildings.BuildingFactory;
import y111studios.buildings.premade_variants.*;

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
     * Width of map in tiles.
     */
    public static final int TILE_WIDTH = 75;
    /**
     * Height of map in tiles.
     */
    public static final int TILE_HEIGHT = 75;
    /**
     * The game object.
     */
    final Main game;
    /**
     * The game state object.
     */
    GameState gameState;
    /**
     * The texture for the game map.
     */
    Texture gameMap;
    /**
     * The texture for the building menu.
     */
    Texture menu;
    /**
     * The texture for the accommodation menu text.
     */
    Texture accommodationMenu;
    /**
     * The texture for the catering menu text.
     */
    Texture cateringMenu;
    /**
     * The texture for the teaching menu text.
     */
    Texture teachingMenu;
    /**
     * The current tab of the menu.
     */
    int menuTab;
    /**
     * The current item of the menu.
     */
    int menuItem;
    /**
     * The textures for the buildings.
     */
    Texture[] buildingTextures;
    /**
     * The variants for the buildings.
     */
    VariantProperties[] buildingVariants;
    /**
     * The viewport to keep proportions consistent when resizing.
     */
    FitViewport viewport;
    /**
     * Whether the game is paused.
     */
    boolean paused;
    /**
     * The texture for the pause menu.
     */
    Texture pauseMenu;
    
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
    private class GraphicsObject {

        /**
         * The tile coordinates of the object.
         */
        GridPosition coords;
        /**
         * The depth/height of the object.
         */
        int depth;
        /**
         * The texture for the object to use.
         */
        Texture texture;

        /**
         * Initializes the given object at set coordinates.
         */
        public GraphicsObject(GridPosition coords, int depth, Texture texture) {
            this.coords = coords;
            this.depth = depth;
            this.texture = texture;
        }

    }

    /**
     * The list of objects to be rendered.
     */
    ArrayList<GraphicsObject> objects;

    /**
     * Adds an object to the game.
     * 
     * @param object The object to add.
     * @return Whether the object was added.
     */
    public boolean addObject(VariantProperties variant, GridPosition coords) {
        Building building = BuildingFactory.createBuilding(variant, coords);
        return gameState.push(building);
        // if(!gameState.push(building)) {
        //     return false;
        // }
        // int i;
        // for(i = 0; i < objects.size(); i++) {
        //     if(objects.get(i).coords.getY() > coords.getY()) {
        //         break;
        //     }
        //     if(objects.get(i).coords.getY() == coords.getY() && objects.get(i).coords.getX() < coords.getX()) {
        //         break;
        //     }
        // }
        // objects.add(i, new GraphicsObject(coords, variant.getHeight(), texture));
        // return true;
    }

    /**
     * Removes an object from the game.
     * 
     * @param coords The tile coordinates of the object to remove.
     * @return Whether an object was removed.
     */
    public boolean removeObject(GridPosition coords) {
        if(!gameState.removePosition(coords)) {
            return false;
        }
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i).coords.getX() == coords.getX() && objects.get(i).coords.getY() == coords.getY()) {
                objects.remove(i);
                return true;
            }
        }
        // We should never get here
        return false;
    }

    /**
     * Sets up the camera and loads the background
     * 
     * @param game Reference to game manager
     */
    public MapScreen(final Main game) {
        this.game = game;
        this.gameState = new GameState(TILE_WIDTH, TILE_HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT);
        viewport.getCamera().position.set(WIDTH / 2f, HEIGHT / 2f, 0);
        viewport.getCamera().update();
        gameMap = game.assetLib.manager.get(AssetPaths.MAP_BACKGROUND.getPath());
        menu = game.assetLib.manager.get(AssetPaths.MENU.getPath());
        accommodationMenu = game.assetLib.manager.get(AssetPaths.ACCOMMODATION_MENU.getPath());
        cateringMenu = game.assetLib.manager.get(AssetPaths.CATERING_MENU.getPath());
        teachingMenu = game.assetLib.manager.get(AssetPaths.TEACHING_MENU.getPath());
        pauseMenu = game.assetLib.manager.get(AssetPaths.PAUSE.getPath());
        menuTab = 0;
        menuItem = -1;
        camera = new Camera(2000, 1000);
        objects = new ArrayList<GraphicsObject>();
        buildingTextures = new Texture[] {game.assetLib.manager.get(AssetPaths.ACC1.getPath()), game.assetLib.manager.get(AssetPaths.ACC2.getPath()), game.assetLib.manager.get(AssetPaths.ACC3.getPath()),
                                          game.assetLib.manager.get(AssetPaths.ACC4.getPath()), game.assetLib.manager.get(AssetPaths.ACC5.getPath()), game.assetLib.manager.get(AssetPaths.CATER1.getPath()),
                                          game.assetLib.manager.get(AssetPaths.CATER2.getPath()), game.assetLib.manager.get(AssetPaths.CATER3.getPath()), game.assetLib.manager.get(AssetPaths.REC1.getPath()),
                                          game.assetLib.manager.get(AssetPaths.REC2.getPath()), game.assetLib.manager.get(AssetPaths.TEACH1.getPath()), game.assetLib.manager.get(AssetPaths.TEACH2.getPath()),
                                          game.assetLib.manager.get(AssetPaths.TEACH3.getPath()), game.assetLib.manager.get(AssetPaths.TEACH4.getPath()), game.assetLib.manager.get(AssetPaths.TEACH5.getPath())};
        buildingVariants = new VariantProperties[] {TeachingVariant.SMALL_CLASSROOM};
        paused = false;
        gameState.resume();
        addObject(buildingVariants[0], new GridPosition(3, 6));
    }

    /**
     * Handles input for panning and zooming the camera.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.ESCAPE) {
                    paused = !paused;
                    camera.addVelocity(-1 * camera.vx, -1 * camera.vy);
                    return true;
                }
                if(paused) {
                    return true;
                }
                if(keyCode == Input.Keys.RIGHT || keyCode == Input.Keys.D) {
                    camera.addVelocity(8, 0);
                } else if(keyCode == Input.Keys.LEFT || keyCode == Input.Keys.A) {
                    camera.addVelocity(-8, 0);
                } else if(keyCode == Input.Keys.DOWN || keyCode == Input.Keys.S) {
                    camera.addVelocity(0, 8);
                } else if(keyCode == Input.Keys.UP || keyCode == Input.Keys.W) {
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
                if(paused) {
                    return true;
                }
                if(keyCode == Input.Keys.RIGHT || keyCode == Input.Keys.D) {
                    camera.addVelocity(-8, 0);
                } else if(keyCode == Input.Keys.LEFT || keyCode == Input.Keys.A) {
                    camera.addVelocity(8, 0);
                } else if(keyCode == Input.Keys.DOWN || keyCode == Input.Keys.S) {
                    camera.addVelocity(0, -8);
                } else if(keyCode == Input.Keys.UP || keyCode == Input.Keys.W) {
                    camera.addVelocity(0, 8);
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(paused) {
                    return true;
                }
                Vector3 screenPos = viewport.getCamera().unproject(new Vector3(screenX, screenY, 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
                System.out.println((int)screenPos.x + " " + (int)screenPos.y);
                if(screenPos.y < 100) {
                    if(screenPos.y > 80) {
                        if(screenPos.x < 155) {
                            menuTab = 0;
                        } else if(screenPos.x > 245 && screenPos.x < 395) {
                            menuTab = 1;
                        } else if(screenPos.x > 490) {
                            menuTab = 2;
                        }
                        menuItem = -1;
                    } else if(screenPos.y < 75 && screenPos.y > 10){
                        int newItem = (int)((screenPos.x - 10) / 80);
                        if(newItem == menuItem || newItem > 4) {
                            menuItem = -1;
                        } else {
                            menuItem = newItem;
                        }
                        System.out.println(menuItem);
                    }
                    return true;
                }
                GridPosition test;
                try {
                    test = pixelToTile((int)(screenPos.x * camera.scale), (int)(screenPos.y * camera.scale));
                    System.out.println(test.getX() + " " + test.getY());
                } catch(IllegalArgumentException e) {
                    System.out.println("Out of bounds!");
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return true;
            }
        });
    }

    /**
     * Converts building tile coordinates to pixel coordinates. Must account for camera.scale and building depth separately.
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
     * @return A {@link GridPosition} containing the tile coordinates.
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
        if(paused) {
            game.spritebatch.setColor(0.5f, 0.5f, 0.5f, 1);
        } else {
            game.spritebatch.setColor(1, 1, 1, 1);
        }
        game.spritebatch.draw(gameMap, 0, 0, WIDTH, HEIGHT, camera.x, camera.y, (int)(WIDTH * camera.scale), (int)(HEIGHT * camera.scale), false, false);
        Iterator<Building> buildingIterator = gameState.buildingIterator();
        while (buildingIterator.hasNext()) {
            Building building = buildingIterator.next();
            Texture texture = game.assetLib.manager.get(building.getTexturePath().getPath());
            int[] pixelCoords = tileToPixel(building.getArea().getOrigin());
            game.spritebatch.draw(texture, (int)(pixelCoords[0] / camera.scale), (int)((pixelCoords[1] - building.getArea().getHeight() * 16) / camera.scale), (int)(2 * texture.getWidth() / camera.scale), (int)(2 * texture.getHeight() / camera.scale), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }
        game.spritebatch.draw(menu, (menuTab - 2) * 243, 0, 1126, 100, 0, 0, menu.getWidth(), menu.getHeight(), false, false);
        game.spritebatch.draw(accommodationMenu, 5, 85);
        game.spritebatch.draw(cateringMenu, 248, 85);
        game.spritebatch.draw(teachingMenu, 491, 85);
        
        for(int i = 0; i < 5; i++) {
            if(i == menuItem || paused) {
                game.spritebatch.setColor(1, 1, 1, 0.5f);
            } else {
                game.spritebatch.setColor(1, 1, 1, 1);
            }
            int j = i;
            if(menuTab > 0) {
                j += menuTab * 5;
            }
            game.spritebatch.draw(buildingTextures[j], 10 + i * 80, 15, 50, (int)((float)buildingTextures[j].getHeight() / buildingTextures[j].getWidth() * 50), 0, 0, buildingTextures[j].getWidth(), buildingTextures[j].getHeight(), false, false);
        }
        
        if(paused) {
            game.spritebatch.setColor(1, 1, 1, 1);
            game.spritebatch.draw(pauseMenu, 220, 204);
        }

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
