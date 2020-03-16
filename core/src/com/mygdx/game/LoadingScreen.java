package com.mygdx.game;

import com.badlogic.gdx.Screen;

public class LoadingScreen extends MenuScreen implements Screen {

    LoadingScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(MyGdxGame.MENU);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
