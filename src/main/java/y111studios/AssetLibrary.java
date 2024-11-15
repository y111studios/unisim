package y111studios;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

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
        for (AssetPaths path : AssetPaths.values()) {
            manager.load(path.getPath(), Texture.class);
        }
        manager.finishLoading();
    }

}
