package com.gambit.games.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public class PlayersAndGamesComponent implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {}




}
