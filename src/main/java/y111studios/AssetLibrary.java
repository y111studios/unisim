package y111studios;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetLibrary {

    private static AssetLibrary instance;
    public AssetManager manager;
    private boolean initialised;
    
    private AssetLibrary(){
        initialised = false;
    }

    private static class Holder {
        private static final AssetLibrary INSTANCE = new AssetLibrary();
    }

    public static AssetLibrary getInstance() {
        return Holder.INSTANCE;
    }

    public void init(){
        manager = new AssetManager();
        initialised = true;
        preload();
    }

    public void dispose() {
        manager.dispose();
    }

    private void preload(){
        manager.load("src/main/java/y111studios/assets/monke.png", Texture.class);
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("src/main/java/y111studios/assets/map.tmx", TiledMap.class);
        manager.load("src/main/java/y111studios/assets/Cursor.png", Texture.class);
        manager.finishLoading();
    }

}
