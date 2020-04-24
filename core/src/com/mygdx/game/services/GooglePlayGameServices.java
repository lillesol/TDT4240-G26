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
    void onEnteredScore(int requestedScore);
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
