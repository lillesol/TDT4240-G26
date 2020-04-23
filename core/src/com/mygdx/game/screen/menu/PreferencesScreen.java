package com.mygdx.game.screen.menu;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.GeoRushAssetManager;

import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public class PreferencesScreen extends AbstractScreen implements Screen {
    private GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();

    private float masterVolume;
    private float sfxVolume;
    private float musicVolume;

    private Slider sliderMasterVolume;
    private Slider sliderSfxVolume;
    private Slider sliderMusicVolume;

    private TextField txtMasterVolume;
    private TextField txtSfxVolume;
    private TextField txtMusicVolume;
    private Preferences preferences;
    public PreferencesScreen() {
        super();
        preferences = assMan.getPreferences();
        masterVolume = Float.parseFloat(preferences.getString("masterVolume", "0.5"));
        sfxVolume = Float.parseFloat(preferences.getString("sfxVolume", "0.5"));
        musicVolume = Float.parseFloat(preferences.getString("musicVolume", "0.5"));
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.top().left();
        table.pad(50,50,50,50);
        Skin skin = assMan.getManager().get(assMan.SKIN, Skin.class);

        Label title = new Label("Preferences", skin, "title");

        Label labelMasterVolume = new Label("Master volume ", skin, "default");
        Label labelSfxVolume = new Label("SFX volume ", skin, "default");
        Label labelMusicVolume = new Label("Music volume ", skin, "default");

        sliderMasterVolume = new Slider(0,1, 0.1f, false, skin);
        sliderSfxVolume = new Slider(0,1, 0.1f, false, skin);
        sliderMusicVolume = new Slider(0,1, 0.1f, false, skin);

        txtMasterVolume = new TextField(String.valueOf(1.0), skin);
        txtSfxVolume = new TextField(String.valueOf(1.0), skin);
        txtMusicVolume = new TextField(String.valueOf(1.0), skin);
        txtMasterVolume.setDisabled(true);
        txtSfxVolume.setDisabled(true);
        txtMusicVolume.setDisabled(true);
        this.setVolumeText();
        this.setSliderValues();

        TextButton btnMainMenu = new TextButton("< MAIN MENU", skin);

        table.add(title).center().expandX();
        table.row().pad(30,0,10,0);

        // Master volume
        table.add(labelMasterVolume).left();
        table.row().pad(0,0,10,0);
        table.add(sliderMasterVolume).fillX().pad(0, 50,10, 0);
        table.add(txtMasterVolume).width(50);
        table.row();

        // SFX volume
        table.add(labelSfxVolume).left();
        table.row().pad(0,0,10,0);
        table.add(sliderSfxVolume).fillX().pad(0, 50,10, 0);
        table.add(txtSfxVolume).width(50);
        table.row();

        // Music volume
        table.add(labelMusicVolume).left();
        table.row().pad(0,0,10,0);
        table.add(sliderMusicVolume).fillX().pad(0, 50,10, 0);
        table.add(txtMusicVolume).width(50);
        table.row();

        table.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                masterVolume = sliderMasterVolume.getValue();
                sfxVolume = sliderSfxVolume.getValue();
                musicVolume = sliderMusicVolume.getValue();

                setVolumeText();

                preferences.putString("masterVolume", String.valueOf(masterVolume));
                preferences.putString("sfxVolume", String.valueOf(sfxVolume));
                preferences.putString("musicVolume", String.valueOf(musicVolume));

                // TODO: flush on back button?
                preferences.flush();
            }
        });

        table.row().pad(30,0,0,0);
        table.add(btnMainMenu).fillX().uniformX();

        addActor(table);

        btnMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
    }

    private void setVolumeText(){
        txtMasterVolume.setText(String.valueOf(masterVolume));
        txtSfxVolume.setText(String.valueOf(sfxVolume));
        txtMusicVolume.setText(String.valueOf(musicVolume));
    }

    private void setSliderValues(){
        sliderMasterVolume.setValue(masterVolume);
        sliderSfxVolume.setValue(sfxVolume);
        sliderMusicVolume.setValue(musicVolume);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
