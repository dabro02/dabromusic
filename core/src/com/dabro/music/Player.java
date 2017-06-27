package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

import java.io.File;
import java.security.Policy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Daniel on 31.05.2017.
 */
public class Player implements Music {

    Parameters params;
    FileBasedConfigurationBuilder<FileBasedConfiguration> builder;
    Configuration config;

    boolean shallplay = false;
    int playstatus = 0;
    int song = 0;

    Stack<String> playedSongs;
    Stack<String> poppedSongs;

    ArrayList<String> songs;
    Music music;
    MathUtils math;
    String actualsong;
    String[] segs;


    Player(ArrayList<String> songs){
        params = new Parameters();
            playedSongs = new Stack<String>();
            poppedSongs = new Stack<String>();
            math = new MathUtils();
            try {
                //builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class).configure(params.fileBased());
            this.songs = songs;
            newSong();
            shallplay = true;
        }
        catch(Exception e){}
    }

    public void newSong() {
        try{
        music.stop();}
        catch (Exception e){}
        try{
            if(playstatus == 0){
            actualsong = songs.get(math.random(0, songs.size()));
        }
        else if(playstatus == 1){
            actualsong = songs.get(song++);
        }

        music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
        playedSongs.push(actualsong);
        actualsong = actualsong.substring(0, actualsong.length()-4);
        segs = actualsong.split("\\\\");
        if(shallplay) {
            music.play();
        }
            /*builder.configure(params.fileBased().setFile(new File(playedSongs.peek())));
            config = builder.getConfiguration();
            config.getProperties("hi");*/
        }
        catch (Exception e){}
    }


    public void playPause(){
        if(isPlaying()){
            pause();
            System.out.println("pause");
            shallplay = false;
        }
        else{
            play();
            System.out.println("play");
            shallplay = true;
        }
    }

    public CharSequence getChrSequence(){
        if(segs[segs.length-1].length() > 55){
            return (CharSequence) new String(segs[segs.length-1].substring(0,55)+" ...");
        }
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


    public void oneBack() {
        try{
            music.stop();}
        catch (Exception e){}
        try{
            if(playedSongs.size()>1){
                poppedSongs.push(playedSongs.peek());
                playedSongs.pop();}
            actualsong = playedSongs.peek();
            music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
            actualsong = actualsong.substring(0, actualsong.length()-4);
            segs = actualsong.split("\\\\");
            if(shallplay)
            music.play();
        }
        catch (Exception e){}
    }

    public void oneForward() {
        if(poppedSongs.isEmpty()){
        newSong();}
        else{
            actualsong = poppedSongs.peek();
            music = Gdx.audio.newMusic(Gdx.files.internal((String) actualsong));
            actualsong = actualsong.substring(0, actualsong.length()-4);
            segs = actualsong.split("\\\\");
            if(shallplay)
                music.play();
            playedSongs.push(poppedSongs.peek());
            poppedSongs.pop();
        }
    }
}





