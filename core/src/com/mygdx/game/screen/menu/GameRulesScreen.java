package com.mygdx.game.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

public class GameRulesScreen extends AbstractScreen {
    private GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();


    public GameRulesScreen(){
        super();
    }

    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        // table.setDebug(true);

        // Table content
        Skin skin = ScreenManager.getInstance().getAssetManager().getManager().get(ScreenManager.getInstance().getAssetManager().SKIN);
        Label descriptionHeader = new Label("Game Rules",skin);
        descriptionHeader.setFontScale(2,2);
        Label descriptionParagraph = new Label("1. Avoid collision with the red enemy ball " +
                "\n" +
                "\n2. Touch screen to increase the speed off your ball" +
                "\n" +
                "\n3. Get points by increasing the ball speed as much as possible" +
                "\n" +
                "\n4. Collect power-ups to gain advantages" +
                "\n" +
                "\n5. GOOD LUCK!", skin);
        descriptionParagraph.setWrap(true);
        TextButton btnMainMenu = new TextButton("< MAIN MENU", skin);

        table.add(descriptionHeader).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(descriptionParagraph).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(btnMainMenu).fillX().uniformX();

        addActor(table);

        btnMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
