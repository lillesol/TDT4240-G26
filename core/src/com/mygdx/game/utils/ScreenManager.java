package com.mygdx.game.utils;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screen.AbstractScreen;

public class ScreenManager {
    private static ScreenManager instance;

    private MyGdxGame game;
    public GeoRushAssetManager manager;
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

    public void showScreen(ScreenEnum screenEnum, Object... params) {

        Screen currentScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        if(currentScreen != null) {
            currentScreen.dispose();
        }
    }

    public GeoRushAssetManager getAssetManager() {
        return this.manager;
    }
}
