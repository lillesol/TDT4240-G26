package com.mygdx.game.utils;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Logger;

public class AssetLoader {

    private static final Logger log = new Logger(AssetLoader.class.getName(), Logger.DEBUG);

    public final AssetManager manager = new AssetManager();

    public final String skin = "quantum-horizon/skin/quantum-horizon-ui.json";


    public void queueAddSkin() {
        manager.load(skin, Skin.class);
    }
}
