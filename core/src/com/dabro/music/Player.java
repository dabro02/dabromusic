package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 31.05.2017.
 */
public class Player implements Music {

    ArrayList<String> playedSongs;

    ArrayList<String> songs;
    Music music;
    MathUtils math;
    String actualsong;
    String[] segs;
    int i = 1;

    Player(ArrayList<String> songs){
            playedSongs = new ArrayList<String>();
            math = new MathUtils();
            try {
            this.songs = songs;
            actualsong = songs.get(math.random(0, songs.size()));
            playedSongs.add(actualsong);
            music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
            actualsong = actualsong.substring(0, actualsong.length() - 4);
            segs = actualsong.split("\\\\");
        }
        catch(Exception e){}
    }

    public void newSong() {
        try{
        music.stop();}
        catch (Exception e){}
        try{
        actualsong = songs.get(math.random(0, songs.size()));
        playedSongs.add(actualsong);
        music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
        actualsong = actualsong.substring(0, actualsong.length()-4);
        segs = actualsong.split("\\\\");
        music.play();}
        catch (Exception e){}
    }
/*
    public void oneBack() {
        i++;
        try{
            music.stop();}
        catch (Exception e){}


        try{
        if(i >1){
            actualsong = playedSongs.get(playedSongs.size()-i);}
        else{
            i = 2;}
        music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
        actualsong = actualsong.substring(0, actualsong.length()-4);
        segs = actualsong.split("\\\\");
        music.play();}
        catch (Exception e){}
        System.out.println(i);
    }

    public void oneForward() {
        i--;
        try{
            music.stop();}
        catch (Exception e){}

        try{
            if(i < playedSongs.size()){
            actualsong = playedSongs.get(playedSongs.size()-i);}
        else{
            i = playedSongs.size()-1;}
            music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
            actualsong = actualsong.substring(0, actualsong.length()-4);
            segs = actualsong.split("\\\\");
            music.play();}
        catch (Exception e){}
        System.out.println(i);
    }
*/

    public void playPause(){
        if(isPlaying()){
            pause();
            System.out.println("pause");
        }
        else{
            play();
            System.out.println("play");
        }
    }

    public CharSequence getChrSequence(){
        return (CharSequence) segs[segs.length-1];
    }

    @Override
    public void play() {
        if(!isPlaying()){
            music.play();
        }
    }

    @Override
    public void pause() {
        if(isPlaying()){
            music.pause();
        }
    }

    @Override
    public void stop() {
        if(isPlaying()){
            music.stop();
        }
    }

    @Override
    public boolean isPlaying() {
        return music.isPlaying();
    }

    @Override
    public void setLooping(boolean isLooping) {
        music.setLooping(isLooping);
    }

    @Override
    public boolean isLooping() {
        return music.isLooping();
    }

    @Override
    public void setVolume(float volume) {
        music.setVolume(volume);
    }

    @Override
    public float getVolume() {

        return music.getVolume();
    }

    @Override
    public void setPan(float pan, float volume) {
        music.setPan(pan, volume);
    }

    @Override
    public void setPosition(float position) {
        music.setPosition(position);
    }

    @Override
    public float getPosition() {
        return music.getPosition();
    }

    @Override
    public void dispose() {
        music.dispose();
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        music.setOnCompletionListener(listener);
    }

    public void update(ArrayList<String> songs){
        this.songs = songs;
    }
}
