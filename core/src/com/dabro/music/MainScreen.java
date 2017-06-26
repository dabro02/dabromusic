package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public class MainScreen extends Screen{


    BitmapFont font;
    SearchSongs searchSongs;
    LoadSongs loadSongs;
    ArrayList<String> songs;
    ScrollPane songslist;

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
        playpausePosition = new Vector3( Gdx.graphics.getWidth()-420+80,Gdx.graphics.getHeight()-120,0);
        skipBackwardPosition = new Vector3(Gdx.graphics.getWidth()-420, Gdx.graphics.getHeight()-120,0);
        skipForwardPosition = new Vector3(Gdx.graphics.getWidth()-420+160, Gdx.graphics.getHeight()-120,0);
        font = new BitmapFont(false);
        loading = new Loading( Gdx.graphics.getWidth()/2-80, Gdx.graphics.getHeight()/2);
        searchSongs = new SearchSongs();
        loadSongs = new LoadSongs();
        songs = loadSongs.songs;
        player = new Player(songs);
        songslist = new ScrollPane(new Actor());

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
            try {
                player.update(songs);
            } catch (Exception e) {
            }
            player.playPause();
        } else {

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                try {
                    player.update(songs);
                } catch (Exception e) {
                }
                player.shallplay=true;
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
                    try {
                        player.update(songs);
                    }
                    catch(Exception e){}
                    player.playPause();
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
        if(Gdx.input.getX() >= skipForwardPosition.x && Gdx.input.getX() <= skipForwardPosition.x+skipForwardButton.getWidth() && Gdx.graphics.getHeight()-Gdx.input.getY() >= skipForwardPosition.y && Gdx.graphics.getHeight()-Gdx.input.getY() <= skipForwardPosition.y+skipForwardButton.getHeight() ){
            if(Gdx.input.justTouched()){
               player.oneForward();
            }
            skipForwardButton= new Texture("SkipforwardPointed.png");
        }
        else{
            skipForwardButton = new Texture("Skipforward.png");
        }

            //SkipBackward Button
            if(Gdx.input.getX() >= skipBackwardPosition.x && Gdx.input.getX() <= skipBackwardPosition.x+skipBackwardButton.getWidth() && Gdx.graphics.getHeight()-Gdx.input.getY() >= skipBackwardPosition.y && Gdx.graphics.getHeight()-Gdx.input.getY() <= skipBackwardPosition.y+skipBackwardButton.getHeight() ){
                if(Gdx.input.justTouched()){
                    player.oneBack();
                }
                    skipBackwardButton= new Texture("SkipbackwardPointed.png");
            }
                else{
                skipBackwardButton = new Texture("Skipbackward.png");
            }



    }

    @Override
    public void update(float dt) {
        handleInput();
        loading.update(isloading);
        playpausePosition.set(Gdx.graphics.getWidth()-350+80,Gdx.graphics.getHeight()-120,0);
        skipBackwardPosition.set(Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()-120,0);
        skipForwardPosition.set(Gdx.graphics.getWidth()-350+160, Gdx.graphics.getHeight()-120,0);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.setTransformMatrix(cam.view);
        sb.setProjectionMatrix(cam.projection);
        sb.begin();
        sb.draw(playpauseButton, playpausePosition.x,playpausePosition.y);
        sb.draw(skipBackwardButton, skipBackwardPosition.x, skipBackwardPosition.y);
        sb.draw(skipForwardButton, skipForwardPosition.x, skipForwardPosition.y);
        try{
        font.draw(sb,player.getChrSequence(), Gdx.graphics.getWidth()-250-(player.getChrSequence().length()/2*6), Gdx.graphics.getHeight()-140 );}
        catch(Exception e){
        }
        loading.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

    }

}
