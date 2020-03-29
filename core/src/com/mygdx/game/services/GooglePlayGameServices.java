package com.mygdx.game.services;

public interface GooglePlayGameServices {
    boolean isSignedIn();
    void signInSilently();
    void startSignInIntent();
    void onResume();
    void signOut();
    void onStartGameRequested(boolean hardMode);
    void onShowAchievementsRequested();
    void onShowLeaderboardsRequested();
    void handleException(Exception e, String details);
    /**
     * Start gameplay. This means updating some status variables and switching
     * to the "gameplay" screen (the screen where the user types the score they want).
     *
     * @param hardMode whether to start gameplay in "hard mode".
     */
    void startGame(boolean hardMode);
    void onEnteredScore(int requestedScore);
    boolean isPrime(int n);
    void checkForAchievements(int requestedScore, int finalScore);
    void achievementToast(String achievement);
    void pushAccomplishments();
    /**
     * Update leaderboards with the user's score.
     *
     * @param finalScore The score the user got.
     */
    void updateLeaderboards(int finalScore);

}
