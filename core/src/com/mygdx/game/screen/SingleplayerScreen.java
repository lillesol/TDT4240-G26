package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.ComputerBall;
import com.mygdx.game.actors.PlayerBall;
import com.mygdx.game.actors.PowerUps;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class SingleplayerScreen extends AbstractScreen {

    private PlayerBall playerBall;

    private ComputerBall computerBall;
    private ComputerBall additionalComputerBall;
    private ComputerBall additionalComputerBall1;

    private List<ComputerBall> computerBallArr;

    private Texture txtreBall;
    private Texture txtreTime;
    private Texture txtrePoints;
    private Texture txtreReduce;

    private Label score;

    private PowerUps powerUps;

    public SingleplayerScreen() {
        super();
        txtreBall = new Texture(Gdx.files.internal("quantum-horizon/raw/globe_3.png"));
        txtreTime = new Texture(Gdx.files.internal("powerUpIcons/time.png"));
        txtrePoints = new Texture(Gdx.files.internal("powerUpIcons/point.png"));
        txtreReduce = new Texture(Gdx.files.internal("powerUpIcons/reduce.png"));
    }

    public boolean checkCollision(ComputerBall computerBall) {
        return (playerBall.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    public void choosePowerUp(int playerScore){
        // Reduce speed
        if (playerScore >= 25 && playerScore < 35) {
            if (playerScore == 25){
                powerUps.setActorName("Reduce speed");
                powerUps.addNewBall(txtreTime);
                addActor(powerUps.getPowerUpBall());
            }
            powerUps.reduceSpeed();
        }

        // Reduce ball
        if (playerScore >= 45 && playerScore < 55) {
            if (playerScore == 45){
                powerUps.setActorName("Reduce ball");
                powerUps.addNewBall(txtreReduce);
                addActor(powerUps.getPowerUpBall());
            }
            powerUps.reduceBall(additionalComputerBall, additionalComputerBall1);
        }

        // Increase points
        if (playerScore >= 65) {
            if (playerScore == 65){
                powerUps.setActorName("Increase points");
                powerUps.addNewBall(txtrePoints);
                addActor(powerUps.getPowerUpBall());
            }
            powerUps.addPowerUpPoint();
        }

        // Add additional ball
        if (playerScore % 30 == 0 && playerScore != 0){
            if (playerScore/30 == 1){
                addActor(additionalComputerBall);
            }else if(playerScore/30 == 2){
                addActor(additionalComputerBall1);
            }
        }
    }

    @Override
    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        // Adding playerBall
        playerBall = new PlayerBall(txtreBall, "playerBall");
        playerBall.setSize(55,55);
        playerBall.setMovementPattern(new CircularMovement(playerBall,MyGdxGame.WIDTH / 5, 4*(MyGdxGame.WIDTH/12), MyGdxGame.HEIGHT / 3, 190));
        // playerBall.setMovementPattern( new SquareMovement(MyGdxGame.WIDTH/5,MyGdxGame.HEIGHT/4));
        playerBall.sprite.setColor(0, 1, 0, 1);
        playerBall.getMovementPattern().getVisualMovementPattern().setColor(playerBall.sprite.getColor());
        addActor(playerBall);

        // Adding computerBall
        computerBall = new ComputerBall(txtreBall, "computerBall");
        computerBall.sprite.setSize(55, 55);
        computerBall.setMovementPattern(new CircularMovement(computerBall,MyGdxGame.WIDTH / 5, 4*(MyGdxGame.WIDTH/12), MyGdxGame.HEIGHT / 2, 0));
        // computerBall.setMovementPattern(new SquareMovement(MyGdxGame.WIDTH/5, 2*MyGdxGame.HEIGHT/4));
        computerBall.getMovementPattern().getVisualMovementPattern().setColor(computerBall.getSprite().getColor());
        addActor(computerBall);

        additionalComputerBall = new ComputerBall(txtreBall, "computerBall");
        additionalComputerBall.sprite.setSize(55, 55);
        additionalComputerBall.setMovementPattern(new CircularMovement(computerBall,MyGdxGame.WIDTH / 5, 4*(MyGdxGame.WIDTH/12), MyGdxGame.HEIGHT / 2, 0));

        additionalComputerBall1 = new ComputerBall(txtreBall, "computerBall");
        additionalComputerBall1.sprite.setSize(55, 55);
        additionalComputerBall1.setMovementPattern(new CircularMovement(computerBall,MyGdxGame.WIDTH / 5, 4*(MyGdxGame.WIDTH/12), MyGdxGame.HEIGHT / 2, 0));

        computerBallArr = new ArrayList<>();
        computerBallArr.add(additionalComputerBall);
        computerBallArr.add(additionalComputerBall1);

        powerUps = new PowerUps(txtreTime, "", computerBall, playerBall);
        powerUps.setPlayer2Ball(playerBall);
        powerUps.getPowerUpBall().setMovementPattern(new CircularMovement(computerBall,MyGdxGame.WIDTH / 5, 4*(MyGdxGame.WIDTH/12), MyGdxGame.HEIGHT / 2, 0));
        powerUps.getPowerUpBall().getMovementPattern().getVisualMovementPattern().setColor(computerBall.getSprite().getColor());

        //Adding ScoreBoard
        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        score = new Label("Highcore: "+String.valueOf(playerBall.score), skin);
        //score.setOrigin(MyGdxGame.WIDTH/5, 7*MyGdxGame.HEIGHT/10);
        score.setPosition(MyGdxGame.WIDTH/4, 7*MyGdxGame.HEIGHT/10);
        addActor(score);
    }

    @Override
    public void render(float delta) {
        int playerScore = (int)(playerBall.score-computerBall.score);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        score.setText("Score: "+ playerScore);

        if (Gdx.input.isTouched()) {
            playerBall.setSpeedMultiplier(5);

        } else {
            playerBall.setSpeedMultiplier(1);
        }

        // Powerups
        choosePowerUp(playerScore);

        // Speedup every 20't point
        if (playerScore % 20 == 0 && playerScore != 0){
            computerBall.setSpeedMultiplier((int)(playerScore/20)+1);
        }

        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();

        // Must be here or it will collide sprites at default location
        if (checkCollision(computerBall) || checkCollision(additionalComputerBall) || checkCollision(additionalComputerBall1)) {
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
