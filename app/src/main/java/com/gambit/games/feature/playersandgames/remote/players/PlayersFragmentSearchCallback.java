package com.gambit.games.feature.playersandgames.remote.players;

import com.gambit.games.feature.playersandgames.model.player.GetAllPlayersResponseModel;

public interface PlayersFragmentSearchCallback {

    void onResponse(GetAllPlayersResponseModel getAllPlayersResponseModel);
    void onError(String errorMessage);
}
