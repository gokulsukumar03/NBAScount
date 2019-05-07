package com.gambit.games.feature.playersandgames.remote.games;

import android.util.Log;

import com.gambit.games.feature.playersandgames.model.games.GetAllGamesResponseModel;

import java.net.HttpURLConnection;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class GamesFragmentServiceImpl implements GamesFragmentService {


    @Override
    public void getAllGames(
            GamesFragmentFactory gamesFragmentFactory,
            GamesFragmentGellAllGamesCallback gamesFragmentGellAllGamesCallback) {

        gamesFragmentFactory.getAllGames()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<GetAllGamesResponseModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<GetAllGamesResponseModel> getAllGamesResponseModelResponse) {

                        if(getAllGamesResponseModelResponse.raw().networkResponse()!=null &&
                            getAllGamesResponseModelResponse.raw().networkResponse().isSuccessful() ||
                            getAllGamesResponseModelResponse.code() == HttpURLConnection.HTTP_OK ||
                            getAllGamesResponseModelResponse.code() == HttpURLConnection.HTTP_NOT_MODIFIED){

                            gamesFragmentGellAllGamesCallback.onSuccess(getAllGamesResponseModelResponse.body());
                        }
                        else if(getAllGamesResponseModelResponse.raw().cacheResponse()!=null){
                            Log.d("#### " , "response from cache");
                            gamesFragmentGellAllGamesCallback.onError("#### response from cache");
                            gamesFragmentGellAllGamesCallback.onSuccess(getAllGamesResponseModelResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        gamesFragmentGellAllGamesCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
