package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;


public class PowerUps extends PowerUpBall {

    private List<PowerUpBall> powerUpBalls = new ArrayList<PowerUpBall>();

    private GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();
    private PowerUpBall powerUpBall;

    private PlayerBall playerBall;
    private PlayerBall player2Ball;

    private ComputerBall computerBall;

    private Texture txtreSprite;

    private String actorName;


    public PowerUps(Texture texture, String actorName, ComputerBall computerBall, PlayerBall playerBall) {
        super(texture, actorName);

        this.computerBall = computerBall;
        this.playerBall = playerBall;

        this.setActorName(actorName);

        txtreSprite = assMan.getManager().get(assMan.TEXTURE_POWERUP_TIME, Texture.class);
        powerUpBall = new PowerUpBall(txtreSprite, "computerBall");
        powerUpBall.getSprite().setSize(60, 60);
        powerUpBall.getSprite().setColor(255,255,255,1);
        powerUpBall.setMovementPattern(new CircularMovement(powerUpBall, MyGdxGame.WIDTH / 5, 7*(MyGdxGame.WIDTH /20), MyGdxGame.HEIGHT / 2, 0));
        powerUpBall.getMovementPattern().getVisualMovementPattern().setColor(computerBall.getSprite().getColor());
    }

    public void addNewBall(Texture txtSpr){
        getPowerUpBall().getSprite().setTexture(txtSpr);
        powerUpBalls.add(getPowerUpBall());
    }

    public void reduceSpeed(){
        if (checkCollisionPowerUpBall() && (getActorName().equals("Reduce speed")) || (checkCollisionPowerUpBallPlayer2() && (getActorName().equals("Reduce speed")))){
            float currSpeed = computerBall.getSpeedMultiplier();
            if (currSpeed == 1) {
                currSpeed = 1;
            } else {
                currSpeed--;
            }

            computerBall.setSpeedMultiplier(currSpeed);
            powerUpBall.addAction(Actions.removeActor());
        }
    }

    public void reduceBall(ComputerBall addComputerBall, ComputerBall addComputerBall2){
        if (checkCollisionPowerUpBall() && (getActorName().equals("Reduce ball")) || checkCollisionPowerUpBallPlayer2() && (getActorName().equals("Reduce ball"))){
            addComputerBall.addAction(Actions.removeActor());
            addComputerBall2.addAction(Actions.removeActor());
            powerUpBall.addAction(Actions.removeActor());
        }
    }

    public void addPowerUpPoint(){
        if (checkCollisionPowerUpBall() && (getActorName().equals("Increase points")) || checkCollisionPowerUpBallPlayer2() && (getActorName().equals("Increase points"))){
            float currSpeed = computerBall.getSpeedMultiplier();
            playerBall.score = playerBall.score+20;
            computerBall.setSpeedMultiplier(currSpeed);
            powerUpBall.addAction(Actions.removeActor());
        }
    }


    public boolean checkCollisionPowerUpBall(){
        return (playerBall.sprite.getBoundingRectangle().overlaps(getPowerUpBall().getSprite().getBoundingRectangle()));
    }

    public boolean checkCollisionPowerUpBallPlayer2(){
        return (player2Ball.sprite.getBoundingRectangle().overlaps(getPowerUpBall().getSprite().getBoundingRectangle()));
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

    public PlayerBall getPlayer2Ball() {
        return player2Ball;
    }

    public void setPlayer2Ball(PlayerBall player2Ball) {
        this.player2Ball = player2Ball;
    }
}
