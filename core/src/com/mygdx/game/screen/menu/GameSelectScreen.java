package com.mygdx.game.screen.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
        table.setDebug(true);

        Skin skin = assMan.manager.get(assMan.SKIN);
        TextButton btnSingleplayer = new TextButton("Single player", skin);
        TextButton btnMultiplayer = new TextButton("Two-player", skin);
        table.add(btnSingleplayer).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(btnMultiplayer).fillX().uniformX();


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
        addActor(table);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK) {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            return true;
        }
        return false;
    }
    @Override
    public void dispose() {
        super.dispose();
    }

}
