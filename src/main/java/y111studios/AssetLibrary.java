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
        //singleton as only one should exist
        return Holder.INSTANCE;
    }

    public void init(){
        manager = new AssetManager(new InternalFileHandleResolver());
        initialised = true;
        preload();
    }

    public void dispose() {
        manager.dispose();
    }

    /**
     * Loads all the assets before the game starts to make it run
     * smoother
     */
    private void preload(){
        manager.load("assets/StartScreen.png", Texture.class);
        manager.finishLoading();
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("assets/map.tmx", TiledMap.class);
        manager.load("assets/Cursor.png", Texture.class);
        manager.load("assets/Building.png", Texture.class);
        manager.finishLoading();
    }

}
