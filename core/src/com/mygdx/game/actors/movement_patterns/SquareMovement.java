package com.mygdx.game.actors.movement_patterns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class SquareMovement extends MovementPattern{

    private float centerX;
    private float centerY;
    private float x;
    private float y;
    private float squareWidth;
    private float squareHeight;
    private ShapeRenderer visualMovementPattern;
    private Actor actor;

    public SquareMovement(Actor actor, float centerX, float centerY){
        this.actor = actor;
        this.centerX = centerX;
        this.centerY = centerY;
        squareWidth = MyGdxGame.WIDTH/2;
        squareHeight = MyGdxGame.HEIGHT/5;
        y = this.centerY- (squareHeight/2);
        x = this.centerX;
        visualMovementPattern = new ShapeRenderer();
        synchronize();
    }

    public ShapeRenderer getVisualMovementPattern() {
        return visualMovementPattern;
    }

    public void synchronize(){
        actor.setPosition(x,y);
    }

    @Override
    public float[] move(float delta) {
        //CHANGE BASE SPEED HERE BY CHANGEING AMOUNT OF STEPS
        delta *= 150;

        if (x <= centerX-(squareWidth/2)&& y<= centerY+(squareHeight /2)){
            this.y += delta;
        }else if (x <= centerX+(squareWidth/2)&& y >= centerY+(squareHeight /2)){
            this.x += delta;
        }else if (x >= centerX+(squareWidth/2)&& y>= centerY-(squareHeight /2)){
            this.y -= delta;
        }else if (x >= centerX-(squareWidth/2)&& y <= centerY-(squareHeight /2)){
            this.x -= delta;
        }

        return new float[]{x,y};
    }

    @Override
    public void renderMovementPattern(){
        visualMovementPattern.begin(ShapeRenderer.ShapeType.Point);
        for (float x = centerX-(squareWidth/2)-1; x<=centerX+(squareWidth/2)+1;x+=0.1){
            for (float y = centerY-(squareHeight/2)-1; y <= centerY+(squareHeight/2)+1; y+=0.1){
                if (x <= centerX-(squareWidth/2)&& y<= centerY+(squareHeight /2)){
                    visualMovementPattern.point(x,y,0);
                }else if (x <= centerX+(squareWidth/2)&& y >= centerY+(squareHeight /2)){
                    visualMovementPattern.point(x,y,0);
                }else if (x >= centerX+(squareWidth/2)&& y>= centerY-(squareHeight /2)){
                    visualMovementPattern.point(x,y,0);
                }else if (x >= centerX-(squareWidth/2)&& y <= centerY-(squareHeight /2)){
                    visualMovementPattern.point(x,y,0);
                }
            }
        }
        //visualMovementPattern.setColor(Color.WHITE);
        //movementPattern.translate(MyGdxGame.WIDTH/4, MyGdxGame.HEIGHT/2,100);
        visualMovementPattern.end();
        //visualMovementPattern.dispose();
    }
    /*
    @Override
    public ShapeRenderer renderMovementPattern() {
        ShapeRenderer movementPattern = new ShapeRenderer();
        movementPattern.begin(ShapeRenderer.ShapeType.Line);
        movementPattern.setColor(Color.WHITE);
        movementPattern.rect(centerX,centerY,squareWidth,squareHeight);
        movementPattern.end();
        return movementPattern;
    }
    */
}
