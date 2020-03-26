package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

import java.lang.Math.*;

public class PlayerBall extends Actor {
    private Sprite sprite;
    private float angle = 0;

    public PlayerBall(Texture texture, final String actorName) {
        sprite = new Sprite(texture);
        setPos(sprite.getX(), sprite.getY());

    }

    public void setPos(float x, float y) {
        sprite.setPosition(x,y);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    }

    public void move(float delta){
        float radius = MyGdxGame.WIDTH/10;
        float circlePointX = MyGdxGame.HEIGHT/4;
        float circleCenterY = MyGdxGame.WIDTH/5;
        float x = circlePointX + (float)Math.cos(this.angle + delta)*radius;
        float y = circleCenterY + (float)Math.sin(this.angle+ delta)*radius;
        this.angle += delta;
        //System.out.println("x: "+ x +"\n y: " +y);
        System.out.println("delta " + delta);
        setPos(x,y);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
