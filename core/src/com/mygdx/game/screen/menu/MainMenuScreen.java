package com.mygdx.game.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.util.Random;


public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(){
        super();
    }

    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        // For leaderboard testing
        final Random randomLeaderboardScore = new Random();

        Skin skin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));
        TextButton btnNewGame = new TextButton("New Game", skin);
        final TextButton btnPreferences = new TextButton("Preferences", skin);
        final TextButton btnSignin = new TextButton("Sign in", skin);
        TextButton btnLeaderboard = new TextButton("Leaderboard", skin);
        TextButton btnLbTest = new TextButton("Leaderboard Test Random Score", skin);
        TextButton btnQuit = new TextButton("Quit", skin);

        table.add(btnNewGame).fillX().uniformX();
        table.row();
        table.add(btnPreferences).fillX().uniformX();
        table.row();
        table.add(btnSignin).fillX().uniformX();
        table.row();
        table.add(btnLeaderboard).fillX().uniformX();
        table.row();
        table.add(btnLbTest).fillX().uniformX();
        table.row();
        table.row().pad(10,0,10,0);
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
        btnSignin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.gpgs.startSignInIntent();
                btnSignin.setDisabled(true);
            }
        });
        btnLeaderboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.gpgs.onShowLeaderboardsRequested();
            }
        });
        btnLbTest.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int randomScore = randomLeaderboardScore.nextInt(100);
                System.out.println("Random score ("+randomScore+") added to the Leaderboard");
                MyGdxGame.gpgs.updateLeaderboards(randomScore);
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
