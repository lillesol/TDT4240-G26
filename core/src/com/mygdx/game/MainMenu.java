package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class MainMenu extends MenuScreen implements Screen  {

    MainMenu(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
        TextButton btnNewGame = new TextButton("New Game", skin);
        TextButton btnPreferences = new TextButton("Preferences", skin);
        TextButton btnQuit = new TextButton("Quit", skin);


        table.add(btnNewGame).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(btnPreferences).fillX().uniformX();
        table.row();
        table.add(btnQuit).fillX().uniformX();

        stage.addActor(table);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        stage.draw();

        btnNewGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.GAME);
            }
        });
        btnPreferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.PREFERENCES);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
