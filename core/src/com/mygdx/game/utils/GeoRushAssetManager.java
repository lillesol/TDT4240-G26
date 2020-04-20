package com.mygdx.game.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GeoRushAssetManager {


    public final AssetManager manager = new AssetManager();

    // Skin
    public final String SKIN = "quantum-horizon/skin/quantum-horizon-ui.json";

    public void loadSkins() {
        manager.load(SKIN, Skin.class);
    }

    // Textures
    public final String TEXTURE_BALL = "quantum-horizon/raw/globe_3.png";
    public final String TEXTURE_POWERUP_TIME = "powerUpIcons/time.png";
    public final String TEXTURE_POWERUP_POINTS = "powerUpIcons/point.png";
    public final String TEXTURE_POWERUP_REDUCE = "powerUpIcons/reduce.png";

    public void loadTextures() {
        manager.load(TEXTURE_BALL, Texture.class);
        manager.load(TEXTURE_POWERUP_TIME, Texture.class);
        manager.load(TEXTURE_POWERUP_POINTS, Texture.class);
        manager.load(TEXTURE_POWERUP_REDUCE, Texture.class);
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
