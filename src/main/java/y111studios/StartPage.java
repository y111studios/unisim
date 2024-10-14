package y111studios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StartPage extends Stage{

    private boolean visible = true;

    public StartPage(Texture texture, final SwitchStage switchStage) {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Image image = new Image(texture);

        table.add(image);

        addActor(table);
    }

    @Override
    public void draw() {
        act(Gdx.graphics.getDeltaTime());
        if (visible) {
            super.draw();
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
