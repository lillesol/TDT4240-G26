package com.mygdx.game.utils;

import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.screen.GameOverScreen;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.GameSelectScreen;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MainMenuScreen;
import com.mygdx.game.screen.PreferencesScreen;

public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },
    LEVEL_SELECT {
        public AbstractScreen getScreen(Object... params) {
            return new GameSelectScreen();
        }
    },
    PREFERENCES {
        public AbstractScreen getScreen(Object... params) {
            return new PreferencesScreen();
        }
    },
    LOADING {
        public AbstractScreen getScreen(Object... params) {
            return new LoadingScreen();
        }
    },
    GAME_OVER {
        public AbstractScreen getScreen(Object... params) {
            return new GameOverScreen();
        }
    },
    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen((Integer) params[0]);
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}