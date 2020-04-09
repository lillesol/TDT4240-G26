package com.mygdx.game.actors.movement_patterns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.PlayerBall;

public class CircularMovement extends MovementPattern {

    private float radius;
    private float centerX;
    private float centerY;
    private float currentAngle;
    private ShapeRenderer visualMovementPattern;

    public CircularMovement(float radius, float centerX, float centerY, float currentAngle){
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.currentAngle = currentAngle;
        visualMovementPattern = new ShapeRenderer();
    }

    public ShapeRenderer getVisualMovementPattern() {
        return visualMovementPattern;
    }

    @Override
    public float[] move(float delta){
        float x = centerX + (float)Math.cos(currentAngle + delta)*radius;
        float y = centerY + (float)Math.sin(currentAngle + delta)*radius;
        currentAngle+= delta;
        return new float[] {x,y};
    }
    
    @Override
    public void renderMovementPattern(){
        visualMovementPattern.begin(ShapeRenderer.ShapeType.Point);
        float angle =0;
        for (float i=0; i-1<360; i+=0.05){
            float x = centerX+75 + (float)Math.cos(angle + i)*(radius);
            float y = centerY + (float)Math.sin(angle + i)*(radius);
            angle+=i;
            visualMovementPattern.point(x,y,0);
        }
        visualMovementPattern.end();
        //visualMovementPattern.dispose();

    }

}
