package com.mygdx.game.utils;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screen.AbstractScreen;

public class ScreenManager {
    private static ScreenManager instance;

    private MyGdxGame game;
    private GeoRushAssetManager manager;
    private ScreenEnum currentScreen;

    private ScreenManager() {
        super();
        manager = new GeoRushAssetManager();
    }

    public static ScreenManager getInstance() {
        if(instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(MyGdxGame game) {
        this.game = game;
    }

    public ScreenEnum getCurrentScreen() {
        return currentScreen;
    }
    public void showScreen(ScreenEnum screenEnum, Object... params) {

        Screen oldScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        this.currentScreen = screenEnum;

        if(oldScreen != null) {
            oldScreen.dispose();
        }
    }

    public GeoRushAssetManager getAssetManager() {
        return this.manager;
    }
}
