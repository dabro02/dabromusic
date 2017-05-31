package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Daniel on 31.05.2017.
 */
public class Player {

    ArrayList<File> songs;
    Music music;
    MathUtils math;

    Player(ArrayList<File> songs){
        this.songs = songs;
        math = new MathUtils();
        music = Gdx.audio.newMusic(Gdx.files.internal(songs.get(math.random(0, songs.size())).getAbsolutePath()));
    }



}
