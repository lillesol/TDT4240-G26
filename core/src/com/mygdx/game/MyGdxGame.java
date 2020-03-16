package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends Game {
	private LoadingScreen loadingScreen;
	private PreferencesMenu preferencesScreen;
	private MainMenu menuScreen;
	private GameSelectMenu gameScreen;
	private GameOverMenu endScreen;

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;



	final static int MENU = 0;
	final static int PREFERENCES = 1;
	final static int GAME = 2;
	final static int ENDGAME = 3;

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();

	}

	void changeScreen(int screen) {
		switch (screen) {
			case MENU :
				if(menuScreen == null) menuScreen = new MainMenu(this);
				this.setScreen(menuScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesMenu(this);
				this.setScreen(preferencesScreen);
				break;
			case GAME:
				if(gameScreen == null) gameScreen = new GameSelectMenu(this);
				this.setScreen(gameScreen);
				break;
			case ENDGAME:
				if(endScreen == null) endScreen = new GameOverMenu(this);
				this.setScreen(endScreen);
				break;
		}
	}
	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}
}
