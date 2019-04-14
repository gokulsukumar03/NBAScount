package com.gambit.games.feature.playersandgames.remote.players;

import com.gambit.games.feature.playersandgames.constant.PlayersAndGamesConstant;
import com.gambit.games.feature.playersandgames.model.player.GetAllPlayersResponseModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PlayersFragmentFactory {


    @GET(PlayersAndGamesConstant.GET_ALL_PLAYERS)
    Observable<Response<GetAllPlayersResponseModel>> getAllPlayers(@Query("page") int page, @Query("per_page") int perPage);



    @GET(PlayersAndGamesConstant.GET_ALL_SEARCH_PLAYERS)
    Observable<Response<GetAllPlayersResponseModel>> getAllSearchPlayers(@Query("search") String search);
}
