package com.mygdx.game.actors.movement_patterns;

public abstract class MovementPattern {

    private float centerX;
    private float centerY;

    public MovementPattern(){

    }

    public abstract float[] move(float delta);

}
