package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.utils.AssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;


public class LoadingScreen extends AbstractScreen {
    private AssetManager assMan;
    private int currentLoading;
    private TextButton textButton;
    public LoadingScreen() {
        super();
        assMan  = ScreenManager.getInstance().getAssetManager();
        currentLoading = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(assMan.manager.update()) {
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

                case 5: assMan.loadTextures();
                    textButton.setText("LOADING TEXTURES");

            }
            if (currentLoading > 5) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        }

        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        draw();

    }
    @Override
    public void buildStage() {
        assMan.loadSkins();
        assMan.manager.finishLoading();
        Skin skin = assMan.manager.get("quantum-horizon/skin/quantum-horizon-ui.json");

        textButton = new TextButton("LOADING ...", skin);

        System.out.println(this.getClass());

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.add(textButton).fillX().uniformX();
        addActor(table);
    }
    @Override
    public void dispose() {
        super.dispose();
    }

}
