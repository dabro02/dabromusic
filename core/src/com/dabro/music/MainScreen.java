package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public class MainScreen extends Screen{

    BitmapFont font;
    SearchSongs searchSongs;
    LoadSongs loadSongs;
    ArrayList<String> songs;

    Texture playpauseButton, skipForwardButton, skipBackwardButton;
    Vector3 playpausePosition, skipForwardPosition, skipBackwardPosition;


    Thread thread2;

    Loading loading;
    boolean isloading = false;

    Player player;




    public MainScreen(ScreenManager sm) {
        super(sm);
        playpauseButton = new Texture("Play.png");
        skipBackwardButton = new Texture("Skipbackward.png");
        skipForwardButton = new Texture("Skipforward.png");
        playpausePosition = new Vector3(1500,950,0);
        skipBackwardPosition = new Vector3(1400, 950,0);
        skipForwardPosition = new Vector3(1600, 950,0);
        font = new BitmapFont(false);
        loading = new Loading( 1920/2-80, 1080/2);
        searchSongs = new SearchSongs();
        loadSongs = new LoadSongs();
        songs = loadSongs.songs;
        player = new Player(songs);
        System.out.println(player);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.O) && !isloading) {
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
                    try {
                        player.update(songs);
                        player.newSong();
                    } catch (Exception e) {
                    }
                }
            });
            thread2.start();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playpausePosition.set(1502, 948, 0);
            try {
                player.update(songs);
            } catch (Exception e) {
            }
            player.firststart = true;
            player.playPause();
        } else {

            playpausePosition.set(1500,950,0);

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                try {
                    player.update(songs);
                } catch (Exception e) {
                }
                player.firststart = true;
                player.newSong();
            }
    }

            /*else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                try {
                    player.update(songs);
                }
                catch(Exception e){}
                player.oneBack();
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
                try {
                    player.update(songs);
                }
                catch(Exception e){}
                player.oneForward();
            }*/

            //Play/Pause Button
            if(Gdx.input.getX() >= playpausePosition.x && Gdx.input.getX() <= playpausePosition.x+playpauseButton.getWidth() && Gdx.graphics.getHeight()-Gdx.input.getY() >= playpausePosition.y && Gdx.graphics.getHeight()-Gdx.input.getY() <= playpausePosition.y+playpauseButton.getHeight() ){
                if(Gdx.input.justTouched()){
                    playpausePosition.set(1502,948,0);
                    try {
                        player.update(songs);
                    }
                    catch(Exception e){}
                    player.playPause();
                }
                else{
                    playpausePosition.set(1500,950,0);
                }
                if(!player.isPlaying())
                    playpauseButton= new Texture("PlayPointed.png");
                else
                    playpauseButton = new Texture("PausePointed.png");
            }
            else {
                if (!player.isPlaying())
                    playpauseButton = new Texture("Play.png");
                else
                    playpauseButton = new Texture("Pause.png");
            }

            //SkipForward Button
            if(true){

            }

            //SkipBackward Button
            if(true){

            }



    }

    @Override
    public void update(float dt) {
        handleInput();


        loading.update(isloading);
        try{
            System.out.println(player.firststart);
        }
        catch(Exception e){

        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playpauseButton, playpausePosition.x,playpausePosition.y);
        try{
        font.draw(sb,player.getChrSequence(), 200, 1000 );}
        catch(Exception e){
        }
        loading.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

    }

}
