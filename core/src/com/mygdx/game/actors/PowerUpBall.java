package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.actors.movement_patterns.MovementPattern;

public class PowerUpBall extends Actor {
    private Sprite sprite;
    private MovementPattern movementPattern;
    private float speedMultiplier;
    private float score;

    public PowerUpBall(Texture texture, final String actorName) {
        setSprite(new Sprite(texture));
        getSprite().setPosition(getX(),getY());
        setSize(getSprite().getWidth(), getSprite().getHeight());
        setSpeedMultiplier(1);
        setScore(0);
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
        getSprite().setSize(width,height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x,y);
        getSprite().setPosition(x,y);
        setBounds(getSprite().getX(), getSprite().getY(), getSprite().getWidth(), getSprite().getHeight());
    }

    public void move(float delta) {
        float multipliedDelta = delta * this.getSpeedMultiplier();
        float[] position = getMovementPattern().move(multipliedDelta);
        // MAYBE MOVE TO SPRITE ADJUSTMENT TO setPos
        setPosition(position[0] - (getSprite().getWidth() / 2), position[1] - (getSprite().getHeight() / 2));
    }



    @Override
    public void act(float delta) {
        this.setScore(this.getScore() + delta* getSpeedMultiplier());
        super.act(delta);
        move(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        getSprite().draw(batch);
        batch.end();
        getMovementPattern().getVisualMovementPattern().setProjectionMatrix(batch.getProjectionMatrix());
        getMovementPattern().renderMovementPattern();
        batch.begin();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
