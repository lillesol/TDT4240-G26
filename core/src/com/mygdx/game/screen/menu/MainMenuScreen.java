package com.mygdx.game.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;


public class MainMenuScreen extends AbstractScreen {
    GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();
    public MainMenuScreen(){
        super();
    }

    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);


        Skin skin = assMan.getManager().get(assMan.SKIN, Skin.class);
        Label screenHeader = new Label("Main Menu",skin, title);
        screenHeader.setFontScale(2,2);
      
        TextButton btnNewGame = new TextButton("New Game", skin);
        TextButton btnPreferences = new TextButton("Preferences", skin);
        TextButton btnGameRules = new TextButton("Game Rules", skin);
        TextButton btnQuit = new TextButton("Quit", skin);

        table.add(screenHeader).fillX().uniformX();
        table.row().pad(20,0,0,0);
        table.add(btnNewGame).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(btnPreferences).fillX().uniformX();
        table.row();
        table.add(btnGameRules).fillX().uniformX();
        table.row().pad(20,0,0,0);
        table.add(btnQuit).fillX().uniformX();

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
        btnGameRules.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.GAME_RULES);
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
