package com.veega.visui.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.veega.visui.VisUIDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		LwjglApplicationConfiguration c = new LwjglApplicationConfiguration();
		c.width = 1280;
		c.height = 720;
		new LwjglApplication(new VisUIDemo(), c);
	}
}
