package se.yrgo.flappybird.desktop;

import se.yrgo.flappybird.Constants;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.yrgo.flappybird.FlappyBirdGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "TestFlappyBirdClone";
        config.width = Constants.getWidth();
        config.height = Constants.getHeight();
        new LwjglApplication(new FlappyBirdGame(), config);
    }
}
