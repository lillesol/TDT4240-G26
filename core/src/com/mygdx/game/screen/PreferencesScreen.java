package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public class PreferencesScreen extends AbstractScreen implements Screen {

    public PreferencesScreen() {
        super();
    }

    @Override
    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));

        TextButton btnPreferences = new TextButton("Back", skin);
        final CheckBox soundBtn = new CheckBox("Sound off ", skin);
        Label label = new Label("Preferences", skin);
        label.setBounds(150,700,10,0);
        label.setFontScale(1f,1f);

        soundBtn.isChecked();
        soundBtn.setSize(500,500);
        table.add(soundBtn).fillX().uniformX();
        table.row();
        table.add(btnPreferences).fillX().uniformX();

        table.row().pad(10,0,10,0);

        addActor(table);
        addActor(label);

        soundBtn.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (soundBtn.isChecked()){
                    soundBtn.setText("Sound on ");
                    System.out.println("Sound on ");
                } else {
                    soundBtn.setText("Sound off ");
                }
            }
        });

        btnPreferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });

    }
}
