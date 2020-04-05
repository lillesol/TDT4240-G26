package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.ComputerBall;
import com.mygdx.game.actors.PlayerBall;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public class MultiplayerScreen extends AbstractScreen {

    private PlayerBall playerBall;
    private PlayerBall player2Ball;
    private ComputerBall computerBall;

    private Texture txtreBall;

    public MultiplayerScreen() {
        super();
        txtreBall = new Texture(Gdx.files.internal("quantum-horizon/raw/globe_3.png"));
    }

    public boolean checkPlayer1Collision(){
        return (playerBall.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    public boolean checkPlayer2collision(){
        return (player2Ball.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());

        //Adding playerBall
        playerBall = new PlayerBall(txtreBall, "playerBall");
        playerBall.sprite.setSize(75,75);
        playerBall.setMovementPattern(new CircularMovement(MyGdxGame.WIDTH/5, MyGdxGame.WIDTH/4, 3*MyGdxGame.HEIGHT/10, 90));
        playerBall.sprite.setColor(0,1,0,1);
        addActor(playerBall);

        //Adding player2Ball
        player2Ball = new PlayerBall(txtreBall, "player2Ball");
        player2Ball.sprite.setColor(0,0,1,1);
        player2Ball.sprite.setSize(75,75);
        player2Ball.setMovementPattern(new CircularMovement(MyGdxGame.WIDTH/5, MyGdxGame.WIDTH/4, 5*MyGdxGame.HEIGHT/10, 180));

        addActor(player2Ball);

        //Adding computerBall
        computerBall = new ComputerBall(txtreBall, "computerBall");
        computerBall.sprite.setSize(75,75);
        computerBall.setMovementPattern(new CircularMovement(MyGdxGame.WIDTH/5, MyGdxGame.WIDTH/4, 4*MyGdxGame.HEIGHT/10, 270));
        computerBall.sprite.setColor(1,0,0,1);

        addActor(computerBall);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()){
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
        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();
        if (checkPlayer1Collision()){
            ScreenManager.getInstance().showScreen(ScreenEnum.MULTI_PLAYER);
        }else if(checkPlayer2collision()){
            ScreenManager.getInstance().showScreen(ScreenEnum.MULTI_PLAYER);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
