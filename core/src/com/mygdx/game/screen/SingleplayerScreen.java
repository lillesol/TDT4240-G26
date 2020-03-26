package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.actors.PlayerBall;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public class SingleplayerScreen extends AbstractScreen {
    private PlayerBall ball1;
    private Texture txtrBattery;
    public SingleplayerScreen() {
        super();
        txtrBattery = new Texture(Gdx.files.internal("quantum-horizon/raw/globe_3.png"));
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());

        ball1 = new PlayerBall(txtrBattery, "testTexture");
        ball1.setPos(0,0);
        System.out.println(ball1.getHeight());
        addActor(ball1);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
