package org.cistercian.submario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.cistercian.submario.SubMarioGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sauntering Seahorse";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new SubMarioGame(), config);
	}
}
