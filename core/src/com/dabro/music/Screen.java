package com.dabro.music;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Daniel on 26.05.2017.
 */
public abstract class Screen {

    OrthographicCamera cam;
    String pathname = "C:/Users/Daniel/Music";

    protected ScreenManager sm;

    protected Screen(ScreenManager sm){
        this.sm = sm;
        cam = new OrthographicCamera(1920,1080);
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb, ShapeRenderer sr);
    public abstract void dispose();

}
