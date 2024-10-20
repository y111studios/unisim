package y111studios;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap {

    static final int TILE_SIZE = 32;
    static final int WIDTH = 30;
    static final int HEIGHT = 20;
    
    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;

    public TiledGameMap(final Main game) {
        map = game.assetLib.manager.get("src/main/java/y111studios/assets/map.tmx");
        mapRenderer = new OTMRWithSprites(map, game.spritebatch);
    }

    public void render(OrthographicCamera camera) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public MapLayer getLayer(String name) {
        return map.getLayers().get(name);
    }

}
