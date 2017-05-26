package com.dabro.music;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public class LoadSongs {

    ArrayList<File> songs;
    FileReader fr;

    MusicPlayer player;

    LoadSongs(MusicPlayer player) {
        this.player = player;
        songs = new ArrayList<File>();
        if(new File("geheimeSongs.list").exists()){
            setSongs();
        }
    }

    public void readSongs() {
        try {
            fr = new FileReader("geheimeSongs.list");
        }
        catch (Exception e){}


    }

    public void setSongs() {
        readSongs();
        this.songs = player.songs;
    }

}
