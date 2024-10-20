package y111studios;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap {

    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;

    public TiledGameMap(final Main game) {
        map = game.assetLib.manager.get("src/main/java/y111studios/assets/map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    public void render(OrthographicCamera camera) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

}
