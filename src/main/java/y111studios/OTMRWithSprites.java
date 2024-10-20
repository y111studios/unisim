package y111studios;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class OTMRWithSprites extends OrthogonalTiledMapRenderer{

    SpriteBatch batch;

    public OTMRWithSprites(TiledMap map, SpriteBatch batch) {
        super(map);
        this.batch = batch;
    }

    @Override
    public void renderObject(MapObject object) {
        if (object instanceof TextureMapObject) {
            TextureMapObject tmo = (TextureMapObject)object;
            batch.begin();
            batch.draw(tmo.getTextureRegion(), tmo.getX(), tmo.getY());
            batch.end();
        }
    }

}
