package com.dabro.music;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;

/**
 * Created by Daniel on 26.05.2017.
 */
public class Loading {

    int x;
    int y;
    int frame = 0;
    boolean isloading;

    BitmapFont font;
    Texture loading;
    TextureRegion[][] regions;
    Sprite loadingsprite;
    CharSequence str = "Loading";


    Loading (int x, int y){
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        loading = new Texture("LoadingCircle.png");
        regions = TextureRegion.split(loading, loading.getWidth()/8, loading.getHeight());
        loadingsprite = new Sprite(regions[0][0]);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                frame++;
                if(frame == 8)
                    frame = 0;
                loadingsprite.setRegion(regions[0][frame]);
            }
        }, 0, 1/17f);
    }

    public void render(SpriteBatch batch){
        if(isloading) {
            loadingsprite.setPosition(x, y);
            loadingsprite.draw(batch);
            font.setColor(1f,1f,1f,1);
            font.draw(batch, str, x+str.length()/2+20, y-20);
        }

    }

    public void update(boolean isloading){
        this.isloading = isloading;
    }

}
