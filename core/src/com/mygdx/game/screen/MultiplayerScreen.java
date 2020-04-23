package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.ComputerBall;
import com.mygdx.game.actors.PlayerBall;
import com.mygdx.game.actors.PowerUps;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class MultiplayerScreen extends AbstractScreen {

    private PlayerBall playerBall;
    private PlayerBall player2Ball;

    private ComputerBall computerBall;
    private ComputerBall additionalComputerBall;

    private List<ComputerBall> computerBallArr;

    private Label scoreboard;

    private GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();

    private Texture txtreBall;
    private Texture txtreTime;
    private Texture txtrePoints;
    private Texture txtreReduce;

    private PowerUps powerUps;

    public MultiplayerScreen() {
        super();

        txtreBall = assMan.getManager().get(assMan.TEXTURE_BALL, Texture.class);
        txtreTime = assMan.getManager().get(assMan.TEXTURE_POWERUP_TIME, Texture.class);
        txtrePoints = assMan.getManager().get(assMan.TEXTURE_POWERUP_POINTS, Texture.class);
        txtreReduce = assMan.getManager().get(assMan.TEXTURE_POWERUP_REDUCE, Texture.class);

    }

    public boolean checkPlayer1Collision(ComputerBall computerBall){
        return (playerBall.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    public boolean checkPlayer2collision(ComputerBall computerBall){
        return (player2Ball.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    //For mobile version with touch IF
    // "i" denotes finger id fingers at once
    public void multipleInputUpdate(){
        for (int id=0 ; id<3; id++ ) {
            if (Gdx.input.isTouched(id)) {
                if (Gdx.input.getY(id) > MyGdxGame.HEIGHT/2){
                    playerBall.setSpeedMultiplier(5);
                } else if (Gdx.input.getY(id) < MyGdxGame.HEIGHT/2) {
                    player2Ball.setSpeedMultiplier(5);
                }
            }else {
                playerBall.setSpeedMultiplier(1);
                player2Ball.setSpeedMultiplier(1);
            }
        }
    }

    public void singleInputUpdate(){
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getY() > MyGdxGame.HEIGHT/2){
                playerBall.setSpeedMultiplier(5);
            }
            if (Gdx.input.getY() < MyGdxGame.HEIGHT/2) {
                player2Ball.setSpeedMultiplier(5);
            }
        }else {
            playerBall.setSpeedMultiplier(1);
            player2Ball.setSpeedMultiplier(1);
        }
    }

    public void choosePowerUp(int player1Score, int player2Score){
        // Reduce speed
        if (player1Score >= 25 || player2Score >= 25) {
            if (player1Score == 25 || player2Score == 25){
                powerUps.setActorName("Reduce speed");
                powerUps.addNewBall(txtreTime);
                addActor(powerUps.getPowerUpBall());
            }
            powerUps.reduceSpeed();
        }

        // Reduce ball
        if (player1Score >= 45|| player2Score >= 45) {
            if (player1Score == 45 || player2Score == 45){
                powerUps.setActorName("Reduce ball");
                powerUps.addNewBall(txtreReduce);
                addActor(powerUps.getPowerUpBall());
            }
            powerUps.reduceBall(additionalComputerBall);
        }

        // Increase points
        if (player1Score >= 65 || player2Score >= 65) {
            if (player1Score == 65 || player2Score == 65){
                powerUps.setActorName("Increase points");
                powerUps.addNewBall(txtrePoints);
                addActor(powerUps.getPowerUpBall());
            }
            powerUps.addPowerUpPoint();
        }

        // Add additional ball
        if (player1Score % 30 == 0 && player1Score != 0 || player2Score % 30 == 0 && player2Score != 0){
            if (player1Score/30 == 1 || player2Score/30 == 1){
                addActor(additionalComputerBall);
            }
        }
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());

        //Adding playerBall
        playerBall = new PlayerBall(txtreBall, "playerBall");
        playerBall.sprite.setSize(55,55);
        playerBall.setMovementPattern(new CircularMovement(playerBall,MyGdxGame.WIDTH/5, 4*(MyGdxGame.WIDTH/12), 2*MyGdxGame.HEIGHT/10, 0));
        playerBall.sprite.setColor(0,1,0,1);
        playerBall.getMovementPattern().getVisualMovementPattern().setColor(playerBall.sprite.getColor());
        addActor(playerBall);

        //Adding player2Ball
        player2Ball = new PlayerBall(txtreBall, "player2Ball");
        player2Ball.sprite.setColor(0,0,1,1);
        player2Ball.sprite.setSize(55,55);
        player2Ball.setMovementPattern(new CircularMovement(player2Ball,MyGdxGame.WIDTH/5, 4*(MyGdxGame.WIDTH/12), 6*MyGdxGame.HEIGHT/10, 0));
        player2Ball.getMovementPattern().getVisualMovementPattern().setColor(player2Ball.sprite.getColor());
        addActor(player2Ball);

        //Adding computerBall
        computerBall = new ComputerBall(txtreBall, "computerBall");
        computerBall.sprite.setSize(55,55);
        computerBall.setMovementPattern(new CircularMovement(computerBall,MyGdxGame.WIDTH/5, 4*(MyGdxGame.WIDTH/12), 4*MyGdxGame.HEIGHT/10, 270));
        computerBall.sprite.setColor(1,0,0,1);
        computerBall.getMovementPattern().getVisualMovementPattern().setColor(computerBall.sprite.getColor());
        addActor(computerBall);

        additionalComputerBall = new ComputerBall(txtreBall, "computerBall");
        additionalComputerBall.sprite.setSize(55,55);
        additionalComputerBall.setMovementPattern(new CircularMovement(additionalComputerBall,MyGdxGame.WIDTH/5, 4*(MyGdxGame.WIDTH/12), 4*MyGdxGame.HEIGHT/10, 270));
        additionalComputerBall.sprite.setColor(1,0,0,1);
        additionalComputerBall.getMovementPattern().getVisualMovementPattern().setColor(additionalComputerBall.sprite.getColor());

        powerUps = new PowerUps(txtreTime, "", computerBall, playerBall);
        powerUps.setPlayer2Ball(player2Ball);
        powerUps.getPowerUpBall().setMovementPattern(new CircularMovement(powerUps,MyGdxGame.WIDTH/5, 4*(MyGdxGame.WIDTH/12), 4*MyGdxGame.HEIGHT/10, 270));
        powerUps.getPowerUpBall().getMovementPattern().getVisualMovementPattern().setColor(computerBall.getSprite().getColor());
        computerBallArr = new ArrayList<>();
        computerBallArr.add(additionalComputerBall);

        //Adding Score

        Skin skin = assMan.getManager().get(assMan.SKIN, Skin.class);

        scoreboard = new Label(String.valueOf(playerBall.score + String.valueOf(player2Ball.score)), skin);
        //score.setOrigin(MyGdxGame.WIDTH/5, 7*MyGdxGame.HEIGHT/10);
        scoreboard.setPosition(MyGdxGame.WIDTH/4, 4*MyGdxGame.HEIGHT/10);

        addActor(scoreboard);
    }

    @Override
    public void render(float delta) {
        int player1Score = (int)(playerBall.score-computerBall.score);
        int player2Score = (int)(player2Ball.score-computerBall.score);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //update scoreboard
        scoreboard.setText("Score: "+player1Score +" : "+player2Score);


        //only for desktop driven version
        singleInputUpdate();
        //for version on mobile
        //multipleInputUpdate();

        // Powerups
        choosePowerUp(player1Score, player2Score);

        // Speedup every 20't point
        if (player1Score % 20 == 0 && player1Score != 0){
            computerBall.setSpeedMultiplier((int)(player1Score/20)+1);
        } else if (player2Score % 20 == 0 && player2Score != 0){
            computerBall.setSpeedMultiplier((int)(player2Score/20)+1);
        }

        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();

        // Must be here or it will collide sprites at default location
        if (checkPlayer1Collision(computerBall) || checkPlayer1Collision(additionalComputerBall)){
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
        }else if(checkPlayer2collision(computerBall) || checkPlayer2collision(additionalComputerBall)){
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
