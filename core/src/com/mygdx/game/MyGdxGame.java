package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.services.GooglePlayGameServices;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public class MyGdxGame extends Game {

	public static final int WIDTH = 768;
	public static final int HEIGHT = 1024;

	public static GooglePlayGameServices gpgs;

	public MyGdxGame(GooglePlayGameServices gpgs){
		this.gpgs = gpgs;
	}

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.LOADING);
	}
	@Override
	public void render() {
		super.render();
		Gdx.graphics.setTitle(Integer.toString(Gdx.graphics.getFramesPerSecond()));
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
