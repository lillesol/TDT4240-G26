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
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.awt.Rectangle;

import javax.xml.soap.Text;

public class SingleplayerScreen extends AbstractScreen {

    private PlayerBall playerBall;
    private ComputerBall computerBall;
    public Label score;
    private Texture txtreBall;

    public SingleplayerScreen() {
        super();
        txtreBall = new Texture(Gdx.files.internal("quantum-horizon/raw/globe_3.png"));
    }

    public boolean checkCollision(){
        return (playerBall.sprite.getBoundingRectangle().overlaps(computerBall.sprite.getBoundingRectangle()));
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

        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        score = new Label(String.valueOf(playerBall.score),skin);
        score.setPosition(300,700);
        addActor(score);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        score.setText(playerBall.score);
        if (Gdx.input.isTouched()){
            playerBall.setSpeedMultiplier(5);
        }else {
            playerBall.setSpeedMultiplier(1);
        }

        if (checkCollision()){
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);
        }
        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
