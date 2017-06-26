package com.dabro.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Stack;

/**
 * Created by Daniel on 26.05.2017.
 */
public class ScreenManager {

    private Stack<Screen> screens;

    public ScreenManager (){
        screens = new Stack<Screen>();
    }

    public void push(Screen screen){
        screens.push(screen);
    }

    public  void pop() {
        screens.pop();
    }

    public void set (Screen screen){
        screens.pop();
        screens.push(screen);
    }

    public void update(float dt){
        screens.peek().update(dt);
        resize();
    }

    public void resize(){
        screens.peek().cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        screens.peek().cam.update();
    }

    public void render(SpriteBatch sb, ShapeRenderer sr){
        screens.peek().render(sb,sr);
    }

}
