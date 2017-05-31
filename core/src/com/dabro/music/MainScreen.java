package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public class MainScreen extends Screen{

    BitmapFont font;
    SearchSongs searchSongs;
    LoadSongs loadSongs;
    ArrayList<File> songs;


    Thread thread2;

    Loading loading;
    boolean isloading = false;

    Player player;




    public MainScreen(ScreenManager sm) {
        super(sm);
        player = new Player(songs);
        font = new BitmapFont(false);
        loading = new Loading( 1920/2-80, 1080/2);
        searchSongs = new SearchSongs();
        loadSongs = new LoadSongs();
        songs = loadSongs.songs;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() && !isloading){
            isloading = true;
            thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    searchSongs.songs.clear();
                    loadSongs.songs.clear();
                    searchSongs.search(MainScreen.super.pathname);
                    searchSongs.save();
                    loadSongs.readSongs();
                    isloading = false;
                }
            });
            thread2.start();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

        }

    }

    @Override
    public void update(float dt) {
        handleInput();


        loading.update(isloading);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for(File file: songs){


        }
        loading.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

    }

}
