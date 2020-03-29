package com.mygdx.game;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import static android.content.ContentValues.TAG;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.services.GooglePlayGameServices;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.EventsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.games.event.Event;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class AndroidLauncher extends AndroidApplication implements GooglePlayGameServices {

	// Client used to sign in with Google APIs
	private GoogleSignInClient mGoogleSignInClient;

	// Client variables
	private AchievementsClient mAchievementsClient;
	private LeaderboardsClient mLeaderboardsClient;
	private EventsClient mEventsClient;
	private PlayersClient mPlayersClient;

	// request codes used when invoking an external activity
	private static final int RC_UNUSED = 5001;
	private static final int RC_SIGN_IN = 9001;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		// Create the client used to sign in to Google services.
		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());
		initialize(new MyGdxGame(this), config);
	}

	private void onConnected(GoogleSignInAccount googleSignInAccount) {
		Log.d(TAG, "onConnected(): connected to Google APIs");

		mAchievementsClient = Games.getAchievementsClient(this, googleSignInAccount);
		mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
		mEventsClient = Games.getEventsClient(this, googleSignInAccount);
		mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);

		// Set the greeting appropriately on main menu
		mPlayersClient.getCurrentPlayer()
				.addOnCompleteListener(new OnCompleteListener<Player>() {
					@Override
					public void onComplete(@NonNull Task<Player> task) {
						String displayName;
						if (task.isSuccessful()) {
							displayName = task.getResult().getDisplayName();
						} else {
							Exception e = task.getException();
							handleException(e, getString(R.string.players_exception));
							displayName = "???";
						}
					}
				});


		/*
		// if we have accomplishments to push, push them
		if (!mOutbox.isEmpty()) {
			pushAccomplishments();
			Toast.makeText(this, ("Your progress will be updated"),
					Toast.LENGTH_LONG).show();
		}

		loadAndPrintEvents();

		 */
	}

	private void onDisconnected() {
		Log.d(TAG, "onDisconnected()");

		mAchievementsClient = null;
		mLeaderboardsClient = null;
		mPlayersClient = null;
	}

	@Override
	public boolean isSignedIn() {
		return GoogleSignIn.getLastSignedInAccount(this) != null;
	}

	@Override
	public void signInSilently() {
		Log.d(TAG, "signInSilently()");

		mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "signInSilently(): success");
							onConnected(task.getResult());
						} else {
							Log.d(TAG, "signInSilently(): failure", task.getException());
							onDisconnected();
						}
					}
				});
	}

	@Override
	public void startSignInIntent() {
		System.out.println("STARTING SIGN IN!");
		startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");

		// Since the state of the signed in user can change when the activity is not active
		// it is recommended to try and sign in silently from when the app resumes.
		signInSilently();
	}

	@Override
	public void signOut() {
		Log.d(TAG, "signOut()");

		if (!isSignedIn()) {
			Log.w(TAG, "signOut() called, but was not signed in!");
			return;
		}

		mGoogleSignInClient.signOut().addOnCompleteListener(this,
				new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						boolean successful = task.isSuccessful();
						Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));

						onDisconnected();
					}
				});
	}

	@Override
	public void onStartGameRequested(boolean hardMode) {

	}

	@Override
	public void onShowAchievementsRequested() {
		mAchievementsClient.getAchievementsIntent()
				.addOnSuccessListener(new OnSuccessListener<Intent>() {
					@Override
					public void onSuccess(Intent intent) {
						startActivityForResult(intent, RC_UNUSED);
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						handleException(e, getString(R.string.achievements_exception));
					}
				});
	}

	@Override
	public void onShowLeaderboardsRequested() {
		mLeaderboardsClient.getAllLeaderboardsIntent()
				.addOnSuccessListener(new OnSuccessListener<Intent>() {
					@Override
					public void onSuccess(Intent intent) {
						startActivityForResult(intent, RC_UNUSED);
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						handleException(e, getString(R.string.leaderboards_exception));
					}
				});
	}

	@Override
	public void handleException(Exception e, String details) {
		int status = 0;

		if (e instanceof ApiException) {
			ApiException apiException = (ApiException) e;
			status = apiException.getStatusCode();
		}

		String message = getString(R.string.status_exception_error, details, status, e);

		new AlertDialog.Builder(AndroidLauncher.this)
				.setMessage(message)
				.setNeutralButton(android.R.string.ok, null)
				.show();
	}

	@Override
	public void startGame(boolean hardMode) {

	}

	@Override
	public void onEnteredScore(int requestedScore) {
		// Compute final score (in easy mode, it's the requested score; in hard mode, it's half)
		int finalScore = requestedScore;

		// check for achievements
		checkForAchievements(requestedScore, finalScore);

		// update leaderboards
		updateLeaderboards(finalScore);

		// push those accomplishments to the cloud, if signed in
		pushAccomplishments();

		//mEventsClient.increment(getString(R.string.event_number_chosen), 1);
	}

	// Checks if n is prime. We don't consider 0 and 1 to be prime.
	@Override
	public boolean isPrime(int n) {
		int i;
		if (n == 0 || n == 1) {
			return false;
		}
		for (i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check for achievements and unlock the appropriate ones.
	 *
	 * @param requestedScore the score the user requested.
	 * @param finalScore     the score the user got.
	 */
	@Override
	public void checkForAchievements(int requestedScore, int finalScore) {
		// Check if each condition is met; if so, unlock the corresponding
		// achievement.
		if (isPrime(finalScore)) {
			// mOutbox.mPrimeAchievement = true;
			achievementToast(getString(R.string.achievement_prime_toast_text));
		}
		if (requestedScore == 9999) {
			// mOutbox.mArrogantAchievement = true;
			achievementToast(getString(R.string.achievement_arrogant_toast_text));
		}
		if (requestedScore == 0) {
			// mOutbox.mHumbleAchievement = true;
			achievementToast(getString(R.string.achievement_humble_toast_text));
		}
		if (finalScore == 1337) {
			// mOutbox.mLeetAchievement = true;
			achievementToast(getString(R.string.achievement_leet_toast_text));
		}
		// mOutbox.mBoredSteps++;
	}

	@Override
	public void achievementToast(String achievement) {
		// Only show toast if not signed in. If signed in, the standard Google Play
		// toasts will appear, so we don't need to show our own.
		if (!isSignedIn()) {
			Toast.makeText(this, getString(R.string.achievement) + ": " + achievement,
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void pushAccomplishments() {
		if (!isSignedIn()) {
			// can't push to the cloud, try again later
			return;
		}
		/*
		if (mOutbox.mPrimeAchievement) {
			mAchievementsClient.unlock(getString(R.string.achievement_prime));
			mOutbox.mPrimeAchievement = false;
		}
		if (mOutbox.mArrogantAchievement) {
			mAchievementsClient.unlock(getString(R.string.achievement_arrogant));
			mOutbox.mArrogantAchievement = false;
		}
		if (mOutbox.mHumbleAchievement) {
			mAchievementsClient.unlock(getString(R.string.achievement_humble));
			mOutbox.mHumbleAchievement = false;
		}
		if (mOutbox.mLeetAchievement) {
			mAchievementsClient.unlock(getString(R.string.achievement_leet));
			mOutbox.mLeetAchievement = false;
		}
		if (mOutbox.mBoredSteps > 0) {
			mAchievementsClient.increment(getString(R.string.achievement_really_bored),
					mOutbox.mBoredSteps);
			mAchievementsClient.increment(getString(R.string.achievement_bored),
					mOutbox.mBoredSteps);
			mOutbox.mBoredSteps = 0;
		}
		if (mOutbox.mEasyModeScore >= 0) {
			mLeaderboardsClient.submitScore(getString(R.string.leaderboard_easy),
					mOutbox.mEasyModeScore);
			mOutbox.mEasyModeScore = -1;
		}
		if (mOutbox.mHardModeScore >= 0) {
			mLeaderboardsClient.submitScore(getString(R.string.leaderboard_hard),
					mOutbox.mHardModeScore);
			mOutbox.mHardModeScore = -1;
		}
		 */
	}

	@Override
	public void updateLeaderboards(int finalScore) {

	}

}
