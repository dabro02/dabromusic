package com.dabro.music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public class LoadSongs {

    ArrayList<String> songs;
    FileReader fr;
    BufferedReader br;
    String file;


    LoadSongs() {
        songs = new ArrayList<String>();
        if(new File("geheimeSongs.list").exists()){
            setSongs();
        }
    }

    public void readSongs() {
        try {
            songs.clear();
            fr = new FileReader("geheimeSongs.list");
            br = new BufferedReader(fr);
            while((file = br.readLine()) != null){
                songs.add(file);
            }

        }
        catch (Exception e){}


    }

    public void setSongs() {
        readSongs();

    }

}
