package y111studios;

import y111studios.Main;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        new Main();
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		new Lwjgl3Application(new Main(), config);
    }
}
