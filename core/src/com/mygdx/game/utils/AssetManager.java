package com.mygdx.game.utils;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Logger;

import java.util.concurrent.TimeUnit;

public class AssetManager {

    private static final Logger log = new Logger(AssetManager.class.getName(), Logger.DEBUG);

    public final com.badlogic.gdx.assets.AssetManager manager = new com.badlogic.gdx.assets.AssetManager();

    // Skin
    final String skin = "quantum-horizon/skin/quantum-horizon-ui.json";

    public void loadSkins() {
        manager.load(skin, Skin.class);

        manager.finishLoading();
    }

    // Textures
    public void loadTextures() {

    }

    // Music
    public void loadMusic() {
    }

    // Sounds
    public void loadSounds() {

    }

    // Font
    public void loadFonts() {
    }


}
