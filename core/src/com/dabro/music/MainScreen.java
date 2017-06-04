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
    boolean isskippingf = false, isskippingb= false;


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
        skipBackwardPosition = new Vector3(1420, 950,0);
        skipForwardPosition = new Vector3(1580, 950,0);
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
        if(Gdx.input.getX() >= skipForwardPosition.x && Gdx.input.getX() <= skipForwardPosition.x+skipForwardButton.getWidth() && Gdx.graphics.getHeight()-Gdx.input.getY() >= skipForwardPosition.y && Gdx.graphics.getHeight()-Gdx.input.getY() <= skipForwardPosition.y+skipForwardButton.getHeight() ){
            if(Gdx.input.isTouched()){
                isskippingf = true;
                player.music.setPosition(player.music.getPosition()+0.5f);
                try {
                    player.update(songs);
                }
                catch(Exception e){}
            }
            else{
                isskippingf = false;
            }
            skipForwardButton= new Texture("SkipforwardPointed.png");
        }
        else{
            skipForwardButton = new Texture("Skipforward.png");
        }

            //SkipBackward Button
            if(Gdx.input.getX() >= skipBackwardPosition.x && Gdx.input.getX() <= skipBackwardPosition.x+skipBackwardButton.getWidth() && Gdx.graphics.getHeight()-Gdx.input.getY() >= skipBackwardPosition.y && Gdx.graphics.getHeight()-Gdx.input.getY() <= skipBackwardPosition.y+skipBackwardButton.getHeight() ){
                if(Gdx.input.isTouched()){
                    isskippingb = true;
                    player.music.setPosition(player.music.getPosition()-1f);
                    try {
                        player.update(songs);
                    }
                    catch(Exception e){}
                }
                else{
                    isskippingb = false;
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
        System.out.println(player.music.getPosition());
        /*try{
            if(!player.isPlaying() && player.firststart){
                System.out.println("der status ist: "+ player.firststart+ "           ");
                player.newSong();
            }
        }catch (Exception e){

        }*/
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playpauseButton, playpausePosition.x,playpausePosition.y);
        if(isskippingb)
            sb.draw(skipBackwardButton, skipBackwardPosition.x+2, skipBackwardPosition.y-2);
        else
            sb.draw(skipBackwardButton, skipBackwardPosition.x, skipBackwardPosition.y);
        if(isskippingf)
            sb.draw(skipForwardButton, skipForwardPosition.x+2, skipForwardPosition.y-2);
        else
            sb.draw(skipForwardButton, skipForwardPosition.x, skipForwardPosition.y);

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
