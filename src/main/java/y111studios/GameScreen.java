package y111studios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen{

    static final int TILE_SIZE = 32;
    static final int WIDTH = 30;
    static final int HEIGHT = 20;

    final Main game;

    TiledMap map;
    TiledMapRenderer renderer;
    MapLayer cursorLayer;
    MapLayer buildingLayer;

    OrthographicCamera camera;

    /**
     * Sets up the game camera and creates the map and cursor
     * to indicate where the buildings are being placed
     * 
     * @param game reference to the manager of the game
     */
    public GameScreen(final Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        map = game.assetLib.manager.get("src/main/java/y111studios/assets/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map) {
            @Override
            public void renderObject(MapObject object) {
                if (object instanceof TextureMapObject) {
                    TextureMapObject tmo = (TextureMapObject)object;
                    //draws the object on the screen at the position in the object layer of the map
                    game.spritebatch.draw(tmo.getTextureRegion(), tmo.getX(), tmo.getY());
                }
            }
        };
        Texture cursorTexture = game.assetLib.manager.get("src/main/java/y111studios/assets/Cursor.png");
        cursorLayer = map.getLayers().get("Cursor layer");
        //creates a 32x32 cursor
        TextureMapObject tmo = new TextureMapObject(new TextureRegion(cursorTexture, 32, 32));
        tmo.setX(0);
        tmo.setY(0);
        cursorLayer.getObjects().add(tmo);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.dispose();//prevent memory leaks
                    Gdx.app.exit();
                    System.exit(-1);
                } else { 
                    TextureMapObject tmo = (TextureMapObject)map.getLayers().get("Cursor layer").getObjects().get(0);
                    //moves the cursor by the size of a tile
                    if (keyCode == Input.Keys.DOWN) {
                        tmo.setY(tmo.getY() - TILE_SIZE);
                    } else if (keyCode == Input.Keys.LEFT) {
                        tmo.setX(tmo.getX() - TILE_SIZE);
                    } else if (keyCode == Input.Keys.RIGHT) {
                        tmo.setX(tmo.getX() + TILE_SIZE);
                    } else if (keyCode == Input.Keys.UP) {
                        tmo.setY(tmo.getY() + TILE_SIZE);
                    } else if (keyCode == Input.Keys.ENTER) {
                        float x = tmo.getX();
                        float y = tmo.getY();
                        buildingLayer = map.getLayers().get("Building layer");
                        TextureMapObject tmo2;
                        tmo2 = containsBuilding(x, y, buildingLayer);
                        if (tmo2 != null) {
                            buildingLayer.getObjects().remove(tmo2);
                        } else {
                            Texture buildingTexture = game.assetLib.manager.get("src/main/java/y111studios/assets/Building.png");
                            //move the cursor before placing the building
                            if (x > 0) {
                                tmo.setX(x-TILE_SIZE);
                            } else {
                                tmo.setX(x+(2*TILE_SIZE));
                            }
                            tmo2 = new TextureMapObject(new TextureRegion(buildingTexture, 64, 64));
                            tmo2.setX(x);
                            tmo2.setY(y);
                            //create a building where the cursor was
                            buildingLayer.getObjects().add(tmo2);
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //move the game cursor to where the computer cursor has been pressed
                TextureMapObject tmo = (TextureMapObject)map.getLayers().get("Cursor layer").getObjects().get(0);
                screenX = (screenX / TILE_SIZE) * TILE_SIZE;
                screenY = (screenY / TILE_SIZE) * TILE_SIZE;
                tmo.setX(screenX);
                tmo.setY(640 - screenY);
                return true;
            }
        });
    }

    private TextureMapObject containsObstacle(float x, float y, MapLayer layer) {
        for (MapObject object : layer.getObjects()) {
            if (object instanceof TextureMapObject) {
                TextureMapObject tmo = (TextureMapObject)object;
                if (tmo.getX() == x && tmo.getY() == y) {
                    return tmo;
                }
            }
        }
        return null;
    }

    private TextureMapObject containsBuilding(float x, float y, MapLayer layer) {
        float xTile;
        float yTile;
        for (MapObject object : layer.getObjects()) {
            if (object instanceof TextureMapObject) {
                TextureMapObject tmo = (TextureMapObject)object;
                xTile = tmo.getX();
                yTile = tmo.getY();
                if (xTile == x && yTile == y || xTile == x-TILE_SIZE && yTile == y ||
                    xTile == x && yTile == y-TILE_SIZE || xTile == x-TILE_SIZE && yTile == y-TILE_SIZE) {
                    return tmo;
                }
            }
        }
        return null;
    }

    private boolean isValidTile(int x, int y) {
        if (x >= 0 && y >= 0 && x <= TILE_SIZE * (WIDTH-1) && y <= TILE_SIZE * (HEIGHT-1)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 0);

        camera.update();
        game.spritebatch.setProjectionMatrix(camera.combined);

        game.spritebatch.begin();
        renderer.setView(camera);
        //render the layers separately so the cursor remains visible
        renderer.render(new int[]{0,1,3});
        renderer.render(new int[]{2});
        game.font.draw(game.spritebatch, "Press space to exit", 100, 150);
        game.spritebatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'hide'");
    }

    @Override
    public void dispose() {
        game.dispose();
    }

}
