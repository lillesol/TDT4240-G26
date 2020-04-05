package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.actors.ComputerBall;
import com.mygdx.game.actors.PlayerBall;
import com.mygdx.game.actors.PowerUpBall;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class SingleplayerScreen extends AbstractScreen {

    private PlayerBall playerBall;
    private ComputerBall computerBall;
    private PowerUpBall powerUpBall;

    private Texture txtreBall;
    public List<PowerUpBall> powerUps = new ArrayList<PowerUpBall>();



    public SingleplayerScreen() {
        super();
        txtreBall = new Texture(Gdx.files.internal("quantum-horizon/raw/globe_3.png"));
    }

    public boolean checkCollision(){
        return (playerBall.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    public boolean checkCollisionPowerUpBall(){
        return (playerBall.sprite.getBoundingRectangle().overlaps(powerUpBall.getSprite().getBoundingRectangle()));
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());

        //Adding playerBall
        playerBall = new PlayerBall(txtreBall, "playerBall");
        playerBall.sprite.setSize(100,100);
        playerBall.setPos(500,400);
        playerBall.sprite.setColor(0,1,0,1);
        addActor(playerBall);

        //Adding computerBall
        computerBall = new ComputerBall(txtreBall, "computerBall");
        computerBall.sprite.setSize(100,100);
        computerBall.setPos(1,0);
        addActor(computerBall);

        //Power Up Ball
        powerUpBall = new PowerUpBall(txtreBall, "powerUpBall");
        powerUpBall.getSprite().setSize(100,100);
        powerUpBall.setPos(1,0);
        powerUpBall.getSprite().setColor(128,0,128,1);
        addActor(powerUpBall);
        powerUps.add(powerUpBall);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        powerUpBall.setSpeedMultiplier(2);

        if (Gdx.input.isTouched()){
            playerBall.setSpeedMultiplier(5);
        }else {
            playerBall.setSpeedMultiplier(1);
        }


        if (checkCollisionPowerUpBall()){
            float currSpeed = computerBall.getSpeedMultiplier();
            if (currSpeed == 1){ currSpeed = 1;} else { currSpeed-- ; }

            computerBall.setSpeedMultiplier(currSpeed);
            for (int i = powerUps.size() - 1; i >= 0; i--) {
                powerUps.get(i).remove();
            }
        }

        if (checkCollision()){
            ScreenManager.getInstance().showScreen(ScreenEnum.SINGLE_PLAYER);
        }
        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
