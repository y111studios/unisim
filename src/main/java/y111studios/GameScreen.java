package y111studios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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

    boolean createBuilding;
    int cursorX;
    int cursorY;

    OrthographicCamera camera;

    public GameScreen(final Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        createBuilding = false;
        map = game.assetLib.manager.get("src/main/java/y111studios/assets/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map) {
            @Override
            public void renderObject(MapObject object) {
                if (object instanceof TextureMapObject) {
                    TextureMapObject tmo = (TextureMapObject)object;
                    game.spritebatch.draw(tmo.getTextureRegion(), tmo.getX(), tmo.getY());
                }
            
            }
        };
        Texture cursorTexture = game.assetLib.manager.get("src/main/java/y111studios/assets/Cursor.png");
        cursorLayer = map.getLayers().get("Cursor layer");
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
                    game.dispose();
                    Gdx.app.exit();
                    System.exit(-1);
                } else { 
                    TextureMapObject tmo = (TextureMapObject)map.getLayers().get("Cursor layer").getObjects().get(0);
                    if (keyCode == Input.Keys.DOWN) {
                        tmo.setY(tmo.getY() - TILE_SIZE);
                    } else if (keyCode == Input.Keys.LEFT) {
                        tmo.setX(tmo.getX() - TILE_SIZE);
                    } else if (keyCode == Input.Keys.RIGHT) {
                        tmo.setX(tmo.getX() + TILE_SIZE);
                    } else if (keyCode == Input.Keys.UP) {
                        tmo.setY(tmo.getY() + TILE_SIZE);
                    }
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                createBuilding = true;
                cursorX = screenX;
                cursorY = screenY;
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 0);

        camera.update();
        game.spritebatch.setProjectionMatrix(camera.combined);

        game.spritebatch.begin();
        renderer.setView(camera);
        renderer.render();
        game.font.draw(game.spritebatch, "Press space to exit", 100, 150);
        if (createBuilding) {
            game.shape.begin(ShapeType.Filled);
            game.shape.setColor(Color.WHITE);
            game.shape.circle(cursorX, 400 - cursorY, 10);
            game.shape.end();
            createBuilding = false;
        }
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
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

}
