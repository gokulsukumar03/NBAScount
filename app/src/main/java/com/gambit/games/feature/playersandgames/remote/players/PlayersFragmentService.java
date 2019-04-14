package com.gambit.games.feature.playersandgames.remote.players;

import com.gambit.games.feature.playersandgames.model.player.GetAllPlayersResponseModel;

public interface PlayersFragmentService {

    void getAllPlayers(
            final int pageNumber,
            final int perPage,
            final PlayersFragmentFactory playersFragmentFactory,
            final PlayersFragmentGetAllPlayersCallback playersFragmentGetAllPlayersCallback);


    void getAllSearchPlayers(
            final String search,
            final PlayersFragmentFactory playersFragmentFactory,
            final PlayersFragmentSearchCallback playersFragmentSearchCallback);

}
