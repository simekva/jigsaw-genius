package com.gdx.jigsawgenius;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// This is the DesktopLauncher for the application. You can run this from a code editor.
// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Jigsaw Genius");
		config.setWindowedMode(1000, 800);
		new Lwjgl3Application(new JigsawGenius(), config);
	}
}
