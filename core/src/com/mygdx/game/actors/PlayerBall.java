package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.actors.movement_patterns.MovementPattern;
import com.mygdx.game.actors.movement_patterns.SquareMovement;

import java.lang.Math.*;

public class PlayerBall extends Actor {
    private Sprite sprite;
    private float angle = 0;
    private MovementPattern movementPattern;

    public PlayerBall(Texture texture, final String actorName) {
        sprite = new Sprite(texture);
        movementPattern = new CircularMovement(MyGdxGame.WIDTH/5, MyGdxGame.HEIGHT/4, 0);
        //movementPattern = new SquareMovement(MyGdxGame.WIDTH/5, 0);
        setPos(sprite.getX(), sprite.getY());

    }

    public void setPos(float x, float y) {
        sprite.setPosition(x,y);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    }

    public void move(float delta){
        float[] position = movementPattern.move(delta);
        setPos(position[0],position[1]);

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
