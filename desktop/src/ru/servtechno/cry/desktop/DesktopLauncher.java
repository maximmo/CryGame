package ru.servtechno.cry.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.servtechno.cry.CryGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = CryGame.WIDTH;
		config.height = CryGame.HEIGHT;
		config.title = CryGame.TITLE;
		new LwjglApplication(new CryGame(), config);
	}
}
