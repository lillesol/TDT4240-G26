package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;


public class LoadingScreen extends AbstractScreen {

    public LoadingScreen() {
        super();
    }

    @Override
    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        TextButton btnGoToMainMenu = new TextButton("CLICK ANYWHERE TO CONTINUE", skin);

        table.add(btnGoToMainMenu).fillX().uniformX();
        addActor(table);
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent z, float x, float y){
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
    }
    @Override
    public void dispose() {
        super.dispose();
    }

}
