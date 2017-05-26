package com.dabro.music;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MusicPlayer extends ApplicationAdapter {
	SpriteBatch batch;
	ScreenManager sm;


	boolean searching = false;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		sm = new ScreenManager();
		sm.push(new MainScreen(sm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}
}
