package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.actors.movement_patterns.MovementPattern;

public class PowerUpBall extends Actor {
    private Sprite sprite;
    private MovementPattern movementPattern;
    private float speedMultiplier;

    public PowerUpBall(Texture texture, final String actorName) {
        setSprite(new Sprite(texture));
        setMovementPattern(new CircularMovement(MyGdxGame.WIDTH/5, MyGdxGame.WIDTH/4, MyGdxGame.HEIGHT/3, 270));
        //movementPattern = new SquareMovement(MyGdxGame.WIDTH/5, 0);
        setPos(getSprite().getX(), getSprite().getY());

        setSpeedMultiplier(1);
    }

    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public void setPos(float x, float y) {
        getSprite().setPosition(x,y);
        setBounds(getSprite().getX(), getSprite().getY(), getSprite().getWidth(), getSprite().getHeight());

    }

    public void move(float delta){
        float multipliedDelta = delta* this.getSpeedMultiplier();
        float[] position = getMovementPattern().move(multipliedDelta);
        setPos(position[0],position[1]);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        getSprite().draw(batch);
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public MovementPattern getMovementPattern() {
        return movementPattern;
    }

    public void setMovementPattern(MovementPattern movementPattern) {
        this.movementPattern = movementPattern;
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }
}
