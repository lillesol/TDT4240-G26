package com.mygdx.game.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GeoRushAssetManager {

    private AssetManager manager = new AssetManager();


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

    public final String SOUND_SCORE_POINT = "sound/score_point.mp3";
    // Sounds
    public void loadSounds() {
        manager.load(SOUND_SCORE_POINT, Sound.class);
    }

    // Font
    public void loadFonts() {
    }

    // Preferences
    private Preferences preferences;
    public void loadPreferences(){
        preferences = Gdx.app.getPreferences("GeoRush.settings");
    }
    public Preferences getPreferences() {
        return preferences;
    }

    public AssetManager getManager() {
        return manager;
    }
}
