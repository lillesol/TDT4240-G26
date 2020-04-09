package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.actors.movement_patterns.MovementPattern;

public class PlayerBall extends Actor {
    public Sprite sprite;
    public MovementPattern movementPattern;
    public float speedMultiplier;
    public int score;
    private boolean otherside;
    private Vector2 startpos;

    public PlayerBall(Texture texture, final String actorName) {
        sprite = new Sprite(texture);
        //movementPattern = new CircularMovement(MyGdxGame.WIDTH / 5, MyGdxGame.WIDTH / 4, 0, 90);
        // movementPattern = new SquareMovement(MyGdxGame.WIDTH/5, 0);
        startpos = new Vector2(sprite.getX(), sprite.getY());
        setPos(startpos.x, startpos.y);
        speedMultiplier = 1;
        score = 0;
        otherside = false;
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

    public void setPos(float x, float y) {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void move(float delta) {
        float multipliedDelta = delta * this.speedMultiplier;
        float[] position = movementPattern.move(multipliedDelta);

        // MAYBE MOVE TO SPRITE ADJUSTMENT TO setPos
        setPos(position[0] + (sprite.getWidth() / 2), position[1] - (sprite.getHeight() / 2));

        if (updateScore(position)) {
            this.score++;
        }
    }

    private boolean updateScore(float[] pos) {
        if (pos[0] > 200) {
            otherside = true;
        }
        if (pos[0] < 40 && otherside) {
            otherside = false;
            return true;
        }
        return false;
    }

    @Override
    public void act(float delta) {
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
