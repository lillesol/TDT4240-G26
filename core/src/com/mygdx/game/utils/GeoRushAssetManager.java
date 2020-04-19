package com.mygdx.game.utils;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Logger;

public class GeoRushAssetManager {

    private static final Logger log = new Logger(GeoRushAssetManager.class.getName(), Logger.DEBUG);

    public final AssetManager manager = new AssetManager();

    // Skin
    public final String SKIN = "quantum-horizon/skin/quantum-horizon-ui.json";

    public void loadSkins() {
        manager.load(SKIN, Skin.class);
    }

    // Textures
    public final String BALL_TEXTURE = "quantum-horizon/raw/globe_3.png";

    public void loadTextures() {
        manager.load(BALL_TEXTURE, Texture.class);
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
