package com.gambit.games.feature.playersandgames.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gambit.games.R;
import com.gambit.games.base.PlayersAndGamesComponent;
import com.gambit.games.databinding.ActivityPlayersGamesBinding;
import com.gambit.games.feature.playersandgames.usercase.TabUpdateListener;

import java.util.Objects;

public class PlayersAndGamesActivity extends AppCompatActivity implements TabUpdateListener {
    private ActivityPlayersGamesBinding activityPlayersGamesBinding;
    private PlayersAndGamesPagerAdapter playersAndGamesPagerAdapter;
    private View playerView, gamesView;
    private TextView totalCountPlayersBadgeView, totalCountGamesBadgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityPlayersGamesBinding = DataBindingUtil.setContentView(this, R.layout.activity_players_games);
        activityPlayersGamesBinding.getRoot();
        renderView();
        getLifecycle().addObserver(new PlayersAndGamesComponent());
    }

    public void toastMessage() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            Log.d("!!!!!!", "toast message");
            Toast.makeText(PlayersAndGamesActivity.this, "toast Message", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        toastMessage();
    }

    private void renderView() {
        activityPlayersGamesBinding.styleTab.addTab(activityPlayersGamesBinding.styleTab.newTab().setText("Players"));
        activityPlayersGamesBinding.styleTab.addTab(activityPlayersGamesBinding.styleTab.newTab().setText("Games"));
        playersAndGamesPagerAdapter = new PlayersAndGamesPagerAdapter(getSupportFragmentManager());
        activityPlayersGamesBinding.viewpager.setAdapter(playersAndGamesPagerAdapter);
        activityPlayersGamesBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(activityPlayersGamesBinding.styleTab));
        playerView = LayoutInflater.from(this).inflate(R.layout.tab_layout_palyer_badge, null);
        totalCountPlayersBadgeView = playerView.findViewById(R.id.total_count_player_badge_tv);
        gamesView = LayoutInflater.from(this).inflate(R.layout.tab_layout_games_badge,null);
        totalCountGamesBadgeView = gamesView.findViewById(R.id.total_count_games_badge_tv);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void updateTabText(int position, String text) {
        if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (position == 0) {
                totalCountPlayersBadgeView.setText(text);
                Objects.requireNonNull(Objects.requireNonNull(activityPlayersGamesBinding.styleTab.getTabAt(position)).setText("Players")).setCustomView(playerView);
            } else if (position == 1) {
                totalCountGamesBadgeView.setText(text);
                Objects.requireNonNull(Objects.requireNonNull(activityPlayersGamesBinding.styleTab.getTabAt(position)).setText("Games")).setCustomView(gamesView);
            }
        }
    }
}
