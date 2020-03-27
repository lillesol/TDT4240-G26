package com.mygdx.game.actors.movement_patterns;

public abstract class MovementPattern {

    private float originX;
    private float originY;

    public MovementPattern(){

    }

    public abstract float[] move(float delta);

}
