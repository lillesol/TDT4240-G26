package com.mygdx.game.screen.menu;


import com.badlogic.gdx.Gdx;
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

public class GameOverScreen extends AbstractScreen {
    GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();

    public GameOverScreen() {
        super();
    }

    @Override
    public void buildStage() {
        System.out.println(this.getClass());
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);

        Skin skin = assMan.getManager().get(assMan.SKIN, Skin.class);
        Label descriptionHeader = new Label("Game Over",skin, "title");
        descriptionHeader.setAlignment(Align.center);
        TextButton btnSingleplayer = new TextButton("Replay", skin);
        TextButton btnMainMenu = new TextButton("< Main Menu", skin);
        table.add(descriptionHeader).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(btnSingleplayer).fillX().uniformX();
        table.row();
        table.add(btnMainMenu).fillX().uniformX();


        btnSingleplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.SINGLE_PLAYER);
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
