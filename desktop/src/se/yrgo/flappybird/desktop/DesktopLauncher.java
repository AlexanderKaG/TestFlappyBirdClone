package se.yrgo.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.yrgo.flappybird.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TestFlappyBirdClone";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new Game(), config);
	}
}
