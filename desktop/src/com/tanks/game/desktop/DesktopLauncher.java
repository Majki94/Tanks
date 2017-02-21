package com.tanks.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tanks.game.MyGdxGame;

public class DesktopLauncher {

	private static final double korFak = 0.75;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)(1280 * korFak);
		config.height = (int)(720 * korFak);
		new LwjglApplication(new MyGdxGame(), config);
	}
}
