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

    boolean firststart = false;

    ArrayList<String> playedSongs;

    ArrayList<String> songs;
    Music music;
    MathUtils math;
    String actualsong;
    String[] segs;

    Player(ArrayList<String> songs){
            playedSongs = new ArrayList<String>();
            math = new MathUtils();
            try {
            this.songs = songs;
            actualsong = songs.get(math.random(0, songs.size()));
            //playedSongs.add(actualsong);
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
        //playedSongs.add(actualsong);
        music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
        actualsong = actualsong.substring(0, actualsong.length()-4);
        segs = actualsong.split("\\\\");
        if(firststart) {
            music.play();
        }
        }
        catch (Exception e){}
    }


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
        try{
        if(!isPlaying()){
            music.play();
        } }catch (Exception e){
        }
    }

    @Override
    public void pause() {
        try{
        if(isPlaying()){
            music.pause();
        }}catch (Exception e){
    }
    }

    @Override
    public void stop() {
            try{
                if(isPlaying()){
                    music.stop();
                }
            }   catch (Exception e){
        }
    }

    @Override
    public boolean isPlaying() {
        try{
            return music.isPlaying();}
            catch (Exception e){
            return false;
            }

    }

    @Override
    public void setLooping(boolean isLooping) {
        try{
        music.setLooping(isLooping);
    }   catch (Exception e){
    }
    }

    @Override
    public boolean isLooping() {
        try{
        return music.isLooping();
        }   catch (Exception e){
            return false;
        }
    }

    @Override
    public void setVolume(float volume) {
        try{
        music.setVolume(volume);
    }   catch (Exception e){}
    }

    @Override
    public float getVolume() {
    try{
        return music.getVolume();
    }   catch (Exception e){ return 0;}
    }

    @Override
    public void setPan(float pan, float volume) {
        try{
        music.setPan(pan, volume);
    }   catch (Exception e){}
    }

    @Override
    public void setPosition(float position) {
        try{
        music.setPosition(position);
    }   catch (Exception e){}
    }

    @Override
    public float getPosition() {
        try{
        return music.getPosition();
    }   catch (Exception e){return 0;}
    }

    @Override
    public void dispose() {
        try{
        music.dispose();
    }   catch (Exception e){}
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        try{
        music.setOnCompletionListener(listener);
    }   catch (Exception e){}
    }

    public void update(ArrayList<String> songs){
        this.songs = songs;
    }
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