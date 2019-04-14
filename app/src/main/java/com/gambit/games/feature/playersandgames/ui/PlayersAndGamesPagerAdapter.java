package com.gambit.games.feature.playersandgames.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PlayersAndGamesPagerAdapter extends FragmentPagerAdapter {


    public PlayersAndGamesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PlayersFragment playersFragment = new PlayersFragment();
                return playersFragment;
            case 1:
                GamesFragment gamesFragment = new GamesFragment();
                return gamesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
