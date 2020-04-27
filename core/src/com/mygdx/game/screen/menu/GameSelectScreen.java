package com.mygdx.game.screen.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;


public class GameSelectScreen extends AbstractScreen {
    private GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();
    public GameSelectScreen() {
        super();
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());
        Table table = new Table();
        table.setFillParent(true);
        // table.setDebug(true);

        Skin skin = assMan.getManager().get(assMan.SKIN);
        Label descriptionHeader = new Label("Select Game", skin, "title");
        //descriptionHeader.setFontScale(2,2);
        descriptionHeader.setAlignment(Align.center);
        TextButton btnSingleplayer = new TextButton("SINGLE PLAYER", skin);
        TextButton btnMultiplayer = new TextButton("TWO-PLAYER", skin);
        TextButton btnMainMenu = new TextButton("< MAIN MENU", skin);
        table.add(descriptionHeader).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(btnSingleplayer).fillX().uniformX();
        table.row();
        table.add(btnMultiplayer).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(btnMainMenu).fillX().uniformX();


        btnSingleplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.SINGLE_PLAYER);
            }
        });
        btnMultiplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MULTI_PLAYER);
            }
        });
        btnMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });


        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
