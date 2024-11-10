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
        manager.load(AssetPaths.START_SCREEN, Texture.class);
        manager.load(AssetPaths.MAP_BACKGROUND, Texture.class);
        manager.load(AssetPaths.MENU, Texture.class);
        manager.load(AssetPaths.ACCOMMODATION_MENU, Texture.class);
        manager.load(AssetPaths.CATERING_MENU, Texture.class);
        manager.load(AssetPaths.TEACHING_MENU, Texture.class);
        manager.load(AssetPaths.ACC1, Texture.class);
        manager.load(AssetPaths.ACC2, Texture.class);
        manager.load(AssetPaths.ACC3, Texture.class);
        manager.load(AssetPaths.ACC4, Texture.class);
        manager.load(AssetPaths.ACC5, Texture.class);
        manager.load(AssetPaths.CATER1, Texture.class);
        manager.load(AssetPaths.CATER2, Texture.class);
        manager.load(AssetPaths.CATER3, Texture.class);
        manager.load(AssetPaths.REC1, Texture.class);
        manager.load(AssetPaths.REC2, Texture.class);
        manager.load(AssetPaths.TEACH1, Texture.class);
        manager.load(AssetPaths.TEACH2, Texture.class);
        manager.load(AssetPaths.TEACH3, Texture.class);
        manager.load(AssetPaths.TEACH4, Texture.class);
        manager.load(AssetPaths.TEACH5, Texture.class);
        manager.finishLoading();
    }

}
