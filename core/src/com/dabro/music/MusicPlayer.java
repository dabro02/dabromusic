package com.dabro.music;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import java.io.File;
import java.util.ArrayList;

public class MusicPlayer extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	CharSequence str = "Loading";
	SearchSongs searchSongs;
	LoadSongs loadSongs;
	ArrayList<File> songs;

	Texture loading;
	TextureRegion[][] regions;
	Sprite loadingsprite;

	int frame = 0;
	boolean searching = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		searchSongs = new SearchSongs();
		loadSongs = new LoadSongs(this);
		loading = new Texture("LoadingCircle.png");
		//Loadinganimation
		regions = TextureRegion.split(loading, loading.getWidth()/8, loading.getHeight());
		loadingsprite = new Sprite(regions[0][0]);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
			 frame++;
			 if(frame == 8)
			 	frame = 0;
				loadingsprite.setRegion(regions[0][frame]);
			}
		}, 0, 1/17f);
		//----------------


		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);

		System.out.println(searchSongs.songs);

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		update();


		batch.begin();

		if (searching){
			batch.setColor(0,0,0,1);
			font.draw(batch,str, 900,500);
			loadingsprite.setPosition(900- str.length()/2*9,520);
			loadingsprite.draw(batch);
		}

		batch.end();
	}

	protected void handleinput(){
		if(Gdx.input.justTouched()){

			new Thread(new Runnable() {
				@Override
				public void run() {
					searching = true;
					searchSongs.songs.clear();
					searchSongs.search("C:/Users/Daniel");
					searchSongs.save();
					loadSongs.setSongs();
					searching = false;
				}
			}).start();
		}
	}

	protected void update() {
		handleinput();
	}
}
