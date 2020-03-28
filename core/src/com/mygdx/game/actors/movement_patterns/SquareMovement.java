package com.mygdx.game.actors.movement_patterns;

import com.mygdx.game.MyGdxGame;

public class SquareMovement extends MovementPattern{

    private float centerX;
    private float centerY;
    private float x;
    private float y;
    private float squareWidth;
    private float squareLenght;

    public SquareMovement(float centerX, float centerY){
        this.centerX = centerX;
        this.centerY = centerY;
        squareWidth = MyGdxGame.WIDTH/10;
        squareLenght = MyGdxGame.HEIGHT/10;
        y = this.centerY-squareLenght;
        x = this.centerX;
    }

    public float[] move(float delta) {
        //CHANGE SPEED HERE
        delta = 200*delta;

        if (x <= centerX-(squareWidth/2)&& y<= centerY+(squareLenght/2)){
            this.y += delta;
        }else if (x <= centerX+(squareWidth/2)&& y >= centerY+(squareLenght/2)){
            this.x += delta;
        }else  if (x >= centerX+(squareWidth/2)&& y>= centerY-(squareLenght/2)){
            this.y -= delta;
        }if (x >= centerX-(squareWidth/2)&& y <= centerY-(squareLenght/2)){
            this.x -= delta;
        }

        return new float[]{x,y};
    }
}
