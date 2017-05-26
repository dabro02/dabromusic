package com.dabro.music.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dabro.music.MusicPlayer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title= "dabromusic";
		config.height = 1080;
		config.width = 1920;
		new LwjglApplication(new MusicPlayer(), config);
	}
}
