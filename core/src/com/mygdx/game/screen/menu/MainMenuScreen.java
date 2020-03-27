package com.mygdx.game.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;


public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(){
        super();
    }

    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        TextButton btnNewGame = new TextButton("New Game", skin);
        TextButton btnPreferences = new TextButton("Preferences", skin);
        TextButton btnQuit = new TextButton("Quit", skin);


        table.add(btnNewGame).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(btnPreferences).fillX().uniformX();
        table.row();
        table.add(btnQuit).fillX().uniformX();

        // Refactor this to a Factory class for UI elements and listeners?
        addActor(table);

        btnNewGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.LEVEL_SELECT);
            }
        });
        btnPreferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.PREFERENCES);
            }
        });
        btnQuit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
