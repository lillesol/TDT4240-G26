package com.mygdx.game.actors.movement_patterns;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class MovementPattern{

    private float centerX;
    private float centerY;
    private float currentX;
    private float currentY;

    public MovementPattern(){

    }

    //returns the instance of the visual movement pattern
    public abstract ShapeRenderer getVisualMovementPattern();

    //Takes in delta which denotes the time between frames, and returns
    //the position in the next frame
    public abstract float[] move(float delta);

    //renders a movementpattern, but doesn't dispose;
    public abstract void renderMovementPattern();

}
