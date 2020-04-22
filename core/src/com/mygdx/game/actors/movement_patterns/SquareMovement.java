package com.mygdx.game.actors.movement_patterns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;

public class SquareMovement extends MovementPattern{

    private float centerX;
    private float centerY;
    private float x;
    private float y;
    private float squareWidth;
    private float squareHeight;
    private ShapeRenderer visualMovementPattern;

    public SquareMovement(float centerX, float centerY){
        this.centerX = centerX;
        this.centerY = centerY;
        squareWidth = MyGdxGame.WIDTH/2;
        squareHeight = MyGdxGame.HEIGHT/5;
        y = this.centerY- (squareHeight/2);
        x = this.centerX;
    }

    public ShapeRenderer getVisualMovementPattern() {
        return visualMovementPattern;
    }

    @Override
    public float[] move(float delta) {
        //CHANGE SPEED HERE BY CHANGEING AMOUNT OF STEPS
        delta *= 200;

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

    public void renderMovementPattern(){
        visualMovementPattern = new ShapeRenderer();
        visualMovementPattern.begin(ShapeRenderer.ShapeType.Point);
        float angle =0;
        for (float i=0; i-1<360; i+=0.1){
            float x = centerX+150 + (float)Math.cos(angle + i)*(+50);
            float y = centerY+125 + (float)Math.sin(angle + i)*(+50);
            angle+=i;
            visualMovementPattern.point(x,y,0);
            //System.out.println("render x " + centerX + " render y "+centerY);
        }
        visualMovementPattern.setColor(Color.WHITE);
        //movementPattern.translate(MyGdxGame.WIDTH/4, MyGdxGame.HEIGHT/2,100);
        visualMovementPattern.end();
        visualMovementPattern.dispose();
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
