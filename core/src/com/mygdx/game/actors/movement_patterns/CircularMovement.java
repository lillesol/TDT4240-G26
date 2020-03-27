package com.mygdx.game.actors.movement_patterns;

import com.badlogic.gdx.math.Vector;
import com.mygdx.game.MyGdxGame;

public class CircularMovement extends MovementPattern {

    private float radius;
    private float centerX;
    private float centerY;
    private float currentAngle;


    public CircularMovement(float radius, float centerX, float centerY, float currentAngle){
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.currentAngle = currentAngle;
    }


    @Override
    public float[] move(float delta){
        float x = centerX + (float)Math.cos(currentAngle + delta)*radius;
        float y = centerY + (float)Math.sin(currentAngle + delta)*radius;
        currentAngle+= delta;

        return  new float[] {x,y};
    }
}
