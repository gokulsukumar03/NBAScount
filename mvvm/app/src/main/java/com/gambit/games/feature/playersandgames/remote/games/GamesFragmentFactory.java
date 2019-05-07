package com.gambit.games.feature.playersandgames.remote.games;

import com.gambit.games.feature.playersandgames.constant.PlayersAndGamesConstant;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesResponseModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface GamesFragmentFactory {

    @GET(PlayersAndGamesConstant.GET_ALL_GAMES)
    Observable<Response<GetAllGamesResponseModel>> getAllGames();

}
