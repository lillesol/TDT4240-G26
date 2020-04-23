package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.actors.movement_patterns.MovementPattern;
import java.lang.Math.*;

public class PlayerBall extends Actor {
    public Sprite sprite;
    private MovementPattern movementPattern;
    private float speedMultiplier;
    public float score;

    public PlayerBall(Texture texture, final String actorName) {
        sprite = new Sprite(texture);
        sprite.setPosition(getX(),getY());
        setSize(sprite.getWidth(),sprite.getHeight());
        //setPosition(sprite.getX(),sprite.getY());
        speedMultiplier = 1;
        score = 0;
    }

    public MovementPattern getMovementPattern() {
        return movementPattern;
    }

    public void setMovementPattern(MovementPattern movementPattern) {
        this.movementPattern = movementPattern;
    }

    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
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
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void move(float delta) {
        float multipliedDelta = delta * this.speedMultiplier;
        float[] position = movementPattern.move(multipliedDelta);
        // MAYBE MOVE TO SPRITE ADJUSTMENT TO setPos
        setPosition(position[0] - (sprite.getWidth() / 2), position[1] - (sprite.getHeight() / 2));
    }

    @Override
    public void act(float delta) {
        this.score += delta*speedMultiplier;
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
