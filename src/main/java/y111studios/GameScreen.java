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
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen{

    final Main game;

    TiledGameMap map;
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
        map = new TiledGameMap(game);
        Texture cursorTexture = game.assetLib.manager.get("src/main/java/y111studios/assets/Cursor.png");
        cursorLayer = map.getLayer("Cursor layer");
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
                    TextureMapObject tmo = (TextureMapObject)map.getLayer("Cursor layer").getObjects().get(0);
                    if (keyCode == Input.Keys.DOWN) {
                        System.out.println(tmo.getY() + TiledGameMap.TILE_SIZE);
                        tmo.setY(tmo.getY() + TiledGameMap.TILE_SIZE);
                    } else if (keyCode == Input.Keys.LEFT) {
                        tmo.setX(tmo.getX() - TiledGameMap.TILE_SIZE);
                    } else if (keyCode == Input.Keys.RIGHT) {
                        tmo.setX(tmo.getX() + TiledGameMap.TILE_SIZE);
                    } else if (keyCode == Input.Keys.UP) {
                        tmo.setY(tmo.getY() - TiledGameMap.TILE_SIZE);
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

        map.render(camera);

        game.spritebatch.begin();
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
