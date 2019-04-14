package com.gambit.games.feature.playersandgames.remote.games;

import com.gambit.games.feature.playersandgames.model.games.GetAllGamesResponseModel;

public interface GamesFragmentGellAllGamesCallback {

    void onSuccess(GetAllGamesResponseModel getAllGamesResponseModel);
    void onError(String errorMessage);
}
