package com.mygdx.game.actors.movement_patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;

public class SquareMovement extends MovementPattern{

    private float centerX;
    private float centerY;
    private float x;
    private float y;
    private float squareWidth;
    private float squareHeight;

    public SquareMovement(float centerX, float centerY){
        this.centerX = centerX;
        this.centerY = centerY;
        squareWidth = MyGdxGame.WIDTH/2;
        squareHeight = MyGdxGame.HEIGHT/5;
        y = this.centerY- (squareHeight/2);
        x = this.centerX;
    }

    @Override
    public float[] move(float delta) {
        //CHANGE SPEED HERE BY CHANGEING AMOUNT OF STEPS
        delta *= 200;

        if (x <= centerX-(squareWidth/2)&& y<= centerY+(squareHeight /2)){
            this.y += delta;
        }else if (x <= centerX+(squareWidth/2)&& y >= centerY+(squareHeight /2)){
            this.x += delta;
        }else  if (x >= centerX+(squareWidth/2)&& y>= centerY-(squareHeight /2)){
            this.y -= delta;
        }else if (x >= centerX-(squareWidth/2)&& y <= centerY-(squareHeight /2)){
            this.x -= delta;
        }

        return new float[]{x,y};
    }
}
