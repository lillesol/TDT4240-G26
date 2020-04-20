package com.mygdx.game.utils;

import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.screen.menu.GameOverScreen;
import com.mygdx.game.screen.MultiplayerScreen;
import com.mygdx.game.screen.SingleplayerScreen;
import com.mygdx.game.screen.menu.GameRulesScreen;
import com.mygdx.game.screen.menu.GameSelectScreen;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.menu.MainMenuScreen;
import com.mygdx.game.screen.menu.PreferencesScreen;

public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },
    PREFERENCES {
        public AbstractScreen getScreen(Object... params) {
            return new PreferencesScreen();
        }
    },
    GAME_RULES {
        public AbstractScreen getScreen(Object... params) {
            return new GameRulesScreen();
        }
    },
    LEVEL_SELECT {
        public AbstractScreen getScreen(Object... params) {
            return new GameSelectScreen();
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
    SINGLE_PLAYER {
        public AbstractScreen getScreen(Object... params) {
            return new SingleplayerScreen();
        }
    },
    MULTI_PLAYER {
        public AbstractScreen getScreen(Object... params) {
            return new MultiplayerScreen();
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}