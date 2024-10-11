package y111studios;

import com.badlogic.gdx.Game;

// Loading assests at game start
public class Main extends Game {

    private static Main instance;
    private AssetLibrary assetLib;
    // Templates
    // Texture backgroundTexture;
    // Texture bucketTexture;
    // Texture dropTexture;
    // Sound dropSound;
    // Music music;
    // Templates
    @Override
    public void create() {
        assetLib = AssetLibrary.getInstance();
        assetLib.init();
        // Templates
        // backgroundTexture = new Texture("background.png");
        // bucketTexture = new Texture("bucket.png");
        // dropTexture = new Texture("drop.png");
        // Templates
    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }
}

