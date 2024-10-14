package y111studios;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Loading assests at game start
public class Main extends Game implements SwitchStage{

    private static Main instance;
    private AssetLibrary assetLib;
    private SpriteBatch spritebatch;
    private FitViewport viewport;
    private GamePage gamePage;
    private StartPage startPage;
    private Texture texture;
    // Templates
    // Texture backgroundTexture;
    // Texture bucketTexture;
    // Texture dropTexture;
    // Sound dropSound;
    // Music music;
    // Templates
    @Override
    public void create() {
        texture = new Texture("example.jpg");
        assetLib = AssetLibrary.getInstance();
        assetLib.init();
        spritebatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        gamePage = new GamePage(texture, this);
        startPage = new StartPage(texture, this);
        Gdx.input.setInputProcessor(startPage);
        // Templates
        // backgroundTexture = new Texture("background.png");
        // bucketTexture = new Texture("bucket.png");
        // dropTexture = new Texture("drop.png");
        // Templates
    }

    @Override
    public void startGame() {
        gamePage.setVisible(true);
        Gdx.input.setInputProcessor(gamePage);
    }

    @Override
    public void returnToStart() {
        startPage.setVisible(true);
        Gdx.input.setInputProcessor(startPage);
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
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }
}

