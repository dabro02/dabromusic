package com.dabro.music;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public abstract class Screen {

    String pathname = "C:/Users/Daniel/Music";

    protected ScreenManager sm;

    protected Screen(ScreenManager sm){
        this.sm = sm;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}
