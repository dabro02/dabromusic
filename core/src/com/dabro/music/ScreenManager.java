package com.dabro.music;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    }

    public void render(SpriteBatch sb){
        screens.peek().render(sb);
    }

}
