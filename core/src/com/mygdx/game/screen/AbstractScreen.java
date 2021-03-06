package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public abstract class AbstractScreen extends Stage implements Screen {
    protected AbstractScreen() {
        super(new ExtendViewport(480,800));
    }
    public abstract void buildStage();


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        super.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }
    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK) {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
        }
        return false;
    }
    @Override public void hide(){}
    @Override public void pause(){}
    @Override public void resume(){
        /*ScreenManager.getInstance().showScreen(ScreenEnum.LOADING, ScreenManager.getInstance().getCurrentScreen());*/
    }
}

