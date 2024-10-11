package y111studios;

import com.badlogic.gdx.assets.AssetManager;

public class AssetLibrary {

    private static AssetLibrary instance;
    private AssetManager manager;
    private boolean initialised;
    
    private AssetLibrary(){
        initialised = false;
    }

    private static class LazyHolder {
        private static final AssetLibrary INSTANCE = new AssetLibrary();
    }

    public static AssetLibrary getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init(){
        manager = new AssetManager();
        initialised = true;
        preload();
    }

    private void preload(){
    }

}
