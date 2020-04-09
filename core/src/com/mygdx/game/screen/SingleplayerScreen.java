package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.ComputerBall;
import com.mygdx.game.actors.PlayerBall;
import com.mygdx.game.actors.movement_patterns.CircularMovement;
import com.mygdx.game.actors.movement_patterns.SquareMovement;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.awt.Rectangle;

import javax.xml.soap.Text;

public class SingleplayerScreen extends AbstractScreen {

    private PlayerBall playerBall;
    private ComputerBall computerBall;
    private Texture txtreBall;
    private Label score;
    public SingleplayerScreen() {
        super();
        txtreBall = new Texture(Gdx.files.internal("quantum-horizon/raw/globe_3.png"));
    }

    public boolean checkCollision() {
        return (playerBall.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
    }

    @Override
    public void buildStage() {
        // Adding playerBall
        playerBall = new PlayerBall(txtreBall, "playerBall");
        playerBall.sprite.setSize(75, 75);
        playerBall.setMovementPattern(new CircularMovement(MyGdxGame.WIDTH / 5, MyGdxGame.WIDTH / 4, MyGdxGame.HEIGHT / 3, 90));
        // playerBall.setMovementPattern( new SquareMovement(MyGdxGame.WIDTH/5,MyGdxGame.HEIGHT/4));
        playerBall.sprite.setColor(0, 1, 0, 1);
        playerBall.getMovementPattern().getVisualMovementPattern().setColor(playerBall.sprite.getColor());
        addActor(playerBall);

        // Adding computerBall
        computerBall = new ComputerBall(txtreBall, "computerBall");
        computerBall.sprite.setSize(75, 75);
        computerBall.setMovementPattern(new CircularMovement(MyGdxGame.WIDTH / 5, MyGdxGame.WIDTH / 4, MyGdxGame.HEIGHT / 2, 0));
        // computerBall.setMovementPattern(new SquareMovement(MyGdxGame.WIDTH/5, 2*MyGdxGame.HEIGHT/4));
        computerBall.getMovementPattern().getVisualMovementPattern().setColor(computerBall.getSprite().getColor());
        addActor(computerBall);

        //Adding ScoreBoard
        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        score = new Label(String.valueOf(playerBall.score), skin);
        score.setPosition(300, 700);
        addActor(score);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        score.setText(playerBall.score);
        if (Gdx.input.isTouched()) {
            playerBall.setSpeedMultiplier(5);
        } else {
            playerBall.setSpeedMultiplier(1);
        }

        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();

        // Must be here or it will collide sprites at default location
        if (checkCollision()) {
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
