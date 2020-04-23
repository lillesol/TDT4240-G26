package com.mygdx.game.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.utils.GeoRushAssetManager;
import com.mygdx.game.utils.ScreenEnum;
import com.mygdx.game.utils.ScreenManager;

import java.util.Random;


public class MainMenuScreen extends AbstractScreen {
    GeoRushAssetManager assMan = ScreenManager.getInstance().getAssetManager();
    public MainMenuScreen(){
        super();
    }

    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        // table.setDebug(true);

        Skin skin = assMan.manager.get(assMan.SKIN);

        Label screenHeader = new Label("Main Menu",skin);
        screenHeader.setFontScale(2,2);
        screenHeader.setAlignment(Align.center);
        // For leaderboard testing
        final Random randomLeaderboardScore = new Random();
        TextButton btnNewGame = new TextButton("New Game", skin);
        final TextButton btnPreferences = new TextButton("Preferences", skin);
        final TextButton btnSignIn = new TextButton("Sign in", skin);
        TextButton btnLeaderboard = new TextButton("Leaderboard", skin);
        //TextButton btnLbTest = new TextButton("Leaderboard Test Random Score", skin);
        TextButton btnGameRules = new TextButton("Game Rules", skin);
        TextButton btnQuit = new TextButton("Quit", skin);
        final Label onlineStatusLabael = new Label("Please sign into Google Play Services", skin);
        onlineStatusLabael.setWrap(true);
        onlineStatusLabael.setAlignment(Align.center);

        // If user is already signed in
        if(MyGdxGame.gpgs.isSignedIn()){
            btnSignIn.setText("Sign out");
            onlineStatusLabael.setText("Signed into Google Play Services");
        }

        table.add(screenHeader).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(btnNewGame).fillX().uniformX();
        table.row().pad(20,0,0,0);
        table.add(btnLeaderboard).fillX().uniformX();
        table.row();
        table.add(btnPreferences).fillX().uniformX();
        table.row();
        table.add(btnGameRules).fillX().uniformX();
        //table.row();
        //table.add(btnLbTest).fillX().uniformX();
        table.row();
        table.add(btnSignIn).fillX().uniformX();
        table.row().pad(20,0,0,0);
        table.add(btnQuit).fillX().uniformX();
        table.row().pad(30,0,0,0);
        table.add(onlineStatusLabael).fillX().uniformX();

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
        btnLeaderboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(MyGdxGame.gpgs.isSignedIn()) {
                    MyGdxGame.gpgs.onShowLeaderboardsRequested();
                }else {
                    btnSignIn.setText("Sign in");
                    onlineStatusLabael.setText("Looks like you haven't signed in successfully.. Please try again.");
                    return;
                }
            }
        });
        /*
        btnLbTest.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(MyGdxGame.gpgs.isSignedIn()) {
                    int randomScore = randomLeaderboardScore.nextInt(10);
                    // add this when Game Over
                    MyGdxGame.gpgs.onEnteredScore(randomScore);
                }
            }
        });
         */
        btnSignIn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!MyGdxGame.gpgs.isSignedIn()) {
                    MyGdxGame.gpgs.startSignInIntent();
                    btnSignIn.setText("Sign out");
                    onlineStatusLabael.setText("Signed into Google Play Services");
                }else {
                    MyGdxGame.gpgs.signOut();
                    btnSignIn.setText("Sign in");
                    onlineStatusLabael.setText("Please sign into Google Play Services");
                }
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
