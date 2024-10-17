package y111studios;

import com.badlogic.gdx.assets.AssetManager;
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
        return Holder.INSTANCE;
    }

    public void init(){
        manager = new AssetManager();
        initialised = true;
        preload();
    }

    private void preload(){
        manager.load("src/main/java/y111studios/assets/monke.png", Texture.class);
        manager.finishLoading();
    }

}
