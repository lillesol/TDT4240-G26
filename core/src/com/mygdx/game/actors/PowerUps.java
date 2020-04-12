package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.movement_patterns.CircularMovement;

import java.util.ArrayList;
import java.util.List;


public class PowerUps extends PowerUpBall {

    private List<PowerUpBall> powerUpBalls = new ArrayList<PowerUpBall>();

    private PowerUpBall powerUpBall;

    private PlayerBall playerBall;

    private ComputerBall computerBall;

    private Texture txtreSprite;

    private String actorName;


    public PowerUps(Texture texture, String actorName, ComputerBall computerBall, PlayerBall playerBall) {
        super(texture, actorName);

        this.computerBall = computerBall;
        this.playerBall = playerBall;

        this.setActorName(actorName);

        txtreSprite = new Texture(Gdx.files.internal("powerUpIcons/time.png"));
        powerUpBall = new PowerUpBall(txtreSprite, "computerBall");
        //setPowerUpBall(new PowerUpBall(txtreSprite, "computerBall"));
        powerUpBall.getSprite().setSize(80, 80);
        powerUpBall.getSprite().setColor(255,255,255,1);
        powerUpBall.setMovementPattern(new CircularMovement(getPowerUpBall(), MyGdxGame.WIDTH / 5, 7*(MyGdxGame.WIDTH /20), MyGdxGame.HEIGHT / 2, 0));
        powerUpBall.getMovementPattern().getVisualMovementPattern().setColor(computerBall.getSprite().getColor());

    }

    public void addNewBall(Texture txtSpr){
        getPowerUpBall().getSprite().setTexture(txtSpr);
        powerUpBalls.add(getPowerUpBall());
    }

    public void reduceSpeed(){
        if (checkCollisionPowerUpBall() && (getActorName().equals("Reduce speed"))){
            float currSpeed = computerBall.getSpeedMultiplier();
            if (currSpeed == 1) {
                currSpeed = 1;
            } else {
                currSpeed--;
            }

            computerBall.setSpeedMultiplier(currSpeed);
            for (int i = powerUpBalls.size() - 1; i >= 0; i--) {
                powerUpBalls.get(i).setPosition(1000, 1000);
                powerUpBalls.get(i).remove();
            }
        }
    }

    public void reduceBall(ComputerBall addComputerBall){
        if (checkCollisionPowerUpBall() && (getActorName().equals("Reduce ball"))){
            addComputerBall.addAction(Actions.removeActor());

            for (int i = powerUpBalls.size() - 1; i >= 0; i--) {
                powerUpBalls.get(i).setPosition(1000, 1000);
                powerUpBalls.get(i).remove();
            }
        }
    }

    public void addPowerUpPoint(){
        if (checkCollisionPowerUpBall() && (getActorName().equals("Increase points"))){
            float currSpeed = computerBall.getSpeedMultiplier();
            playerBall.score = playerBall.score+20;
            computerBall.setSpeedMultiplier(currSpeed);
            for (int i = powerUpBalls.size() - 1; i >= 0; i--) {
                powerUpBalls.get(i).setPosition(1000, 1000);
                powerUpBalls.get(i).remove();
            }
        }
    }


    public boolean checkCollisionPowerUpBall(){
        return (playerBall.sprite.getBoundingRectangle().overlaps(getPowerUpBall().getSprite().getBoundingRectangle()));
    }

    public PowerUpBall getPowerUpBall() {
        return powerUpBall;
    }

    public void setPowerUpBall(PowerUpBall powerUpBall) {
        this.powerUpBall = powerUpBall;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}
