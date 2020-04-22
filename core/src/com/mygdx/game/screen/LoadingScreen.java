package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;


public class LoadingScreen extends AbstractScreen {
    private GeoRushAssetManager assMan;
    private int currentLoading;
    private TextButton textButton;
    private ProgressBar progressBar;
    private ScreenEnum newScreen;
    public LoadingScreen() {
        super();
        assMan  = ScreenManager.getInstance().getAssetManager();
        currentLoading = 0;
    }

    public LoadingScreen(ScreenEnum n) {
        super();
        assMan  = ScreenManager.getInstance().getAssetManager();
        currentLoading = 0;
        newScreen = n;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(assMan.getManager().update()) {
            currentLoading ++;
            switch (currentLoading) {
                case 1:
                    assMan.loadSkins();
                    textButton.setText("LOADING SKINS");

                case 2:
                    textButton.setText("LOADING FONTS");

                case 3:
                    assMan.loadMusic();
                    textButton.setText("LOADING MUSIC");

                case 4:
                    assMan.loadSounds();
                    textButton.setText("LOADING SOUNDS");

                case 5:
                    assMan.loadTextures();
                    textButton.setText("LOADING TEXTURES");

            }
            if (currentLoading > 5) {
                assMan.getManager().finishLoading();
                newScreen = (newScreen == null ? ScreenEnum.MAIN_MENU : newScreen);
                ScreenManager.getInstance().showScreen(newScreen);
            }
        }

        act();
        draw();

    }
    @Override
    public void buildStage() {
        assMan.loadSkins();
        assMan.getManager().finishLoading();
        Skin skin = assMan.getManager().get(assMan.SKIN);

        textButton = new TextButton("LOADING ...", skin);
      //  progressBar = new ProgressBar(0,100, 1, false, skin);
        System.out.println(this.getClass());

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.add(textButton).fillX().uniformX();
        table.row().pad(10,0,10,0);
       // table.add(progressBar);
        addActor(table);
    }
    @Override
    public void dispose() {
        super.dispose();
    }

}
