package com.dabro.music;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public class SearchSongs {

    File directory;
    ArrayList<File> songs;

    SearchSongs () {
        songs = new ArrayList<File>();
    }

    public void search(String pathname) {
        try {
            directory = new File(pathname);
            File[] files = directory.listFiles();
            for (File file : files) {

                if (file.isDirectory()) {
                    search(file.getAbsolutePath());
                } else if (file.getAbsolutePath().substring(file.getAbsolutePath().length() - 3, file.getAbsolutePath().length()).equalsIgnoreCase("mp3")) {
                    songs.add(file);
                }
            }
        }
        catch (Exception e){

        }
    }

    public void save(){
        File mp3files = new File("geheimeSongs.list");
        if (!mp3files.exists()) {
            try {
                mp3files.createNewFile();
            } catch (Exception e) {
            }
        }

        try {
            PrintWriter fw = new PrintWriter(mp3files);
            for (int i = 0; i < songs.size(); ++i) {
                fw.write(songs.get(i).getAbsolutePath() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
