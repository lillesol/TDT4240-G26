package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private Vector3 position;
    private Vector3 velocity;
    private static final int PLAYERSPEED = 5;

    private Texture bird;

    public Player(int x,int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("");
    }

    public void update(float dt){
        velocity.add(velocity.x,velocity.y,0);
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,0);
        velocity.scl(1/dt);
    }
}
