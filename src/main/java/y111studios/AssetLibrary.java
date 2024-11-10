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
        manager.load(AssetPaths.START_SCREEN.getPath(), Texture.class);
        manager.load(AssetPaths.MAP_BACKGROUND.getPath(), Texture.class);
        manager.load(AssetPaths.MENU.getPath(), Texture.class);
        manager.load(AssetPaths.ACCOMMODATION_MENU.getPath(), Texture.class);
        manager.load(AssetPaths.CATERING_MENU.getPath(), Texture.class);
        manager.load(AssetPaths.TEACHING_MENU.getPath(), Texture.class);
        manager.load(AssetPaths.PAUSE.getPath(), Texture.class);
        manager.load(AssetPaths.ACC1.getPath(), Texture.class);
        manager.load(AssetPaths.ACC2.getPath(), Texture.class);
        manager.load(AssetPaths.ACC3.getPath(), Texture.class);
        manager.load(AssetPaths.ACC4.getPath(), Texture.class);
        manager.load(AssetPaths.ACC5.getPath(), Texture.class);
        manager.load(AssetPaths.CATER1.getPath(), Texture.class);
        manager.load(AssetPaths.CATER2.getPath(), Texture.class);
        manager.load(AssetPaths.CATER3.getPath(), Texture.class);
        manager.load(AssetPaths.REC1.getPath(), Texture.class);
        manager.load(AssetPaths.REC2.getPath(), Texture.class);
        manager.load(AssetPaths.TEACH1.getPath(), Texture.class);
        manager.load(AssetPaths.TEACH2.getPath(), Texture.class);
        manager.load(AssetPaths.TEACH3.getPath(), Texture.class);
        manager.load(AssetPaths.TEACH4.getPath(), Texture.class);
        manager.load(AssetPaths.TEACH5.getPath(), Texture.class);
        manager.finishLoading();
    }

}
