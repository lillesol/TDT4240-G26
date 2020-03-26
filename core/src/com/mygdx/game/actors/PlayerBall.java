package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerBall extends Actor {
    private Sprite sprite;
    public PlayerBall(Texture texture, final String actorName) {
        sprite = new Sprite(texture);
        setPos(sprite.getX(), sprite.getY());
    }

    public void setPos(float x, float y) {
        sprite.setPosition(x,y);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
