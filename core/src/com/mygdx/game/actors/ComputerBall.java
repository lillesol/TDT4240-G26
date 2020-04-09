package com.mygdx.game.actors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.actors.movement_patterns.MovementPattern;
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

public class ComputerBall extends Actor{
    public Sprite sprite;
    private MovementPattern movementPattern;
    private float speedMultiplier;
    public float score;

    public ComputerBall(Texture texture, final String actorName) {
        sprite = new Sprite(texture);
        sprite.setPosition(getX(),getY());
        setSize(sprite.getWidth(),sprite.getHeight());
        speedMultiplier = 1;
        score = 0;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public MovementPattern getMovementPattern() {
        return movementPattern;
    }

    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public void setMovementPattern(MovementPattern movementPattern) {
        this.movementPattern = movementPattern;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        sprite.setSize(width,height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x,y);
        sprite.setPosition(x,y);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    }

    public void move(float delta){
        float multipliedDelta = delta*this.speedMultiplier;
        float[] position = movementPattern.move(multipliedDelta);
        //MAYBE MOVE TO SPRITE ADJUSTMENT TO setPos
        setPosition(position[0]-(sprite.getWidth()/2),position[1]-(sprite.getHeight()/2));
    }

    @Override
    public void act(float delta) {
        this.score += delta;
        super.act(delta);
        move(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        batch.end();
        movementPattern.getVisualMovementPattern().setProjectionMatrix(batch.getProjectionMatrix());
        movementPattern.renderMovementPattern();
        batch.begin();
    }
}

