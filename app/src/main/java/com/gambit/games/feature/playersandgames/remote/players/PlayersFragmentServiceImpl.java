package com.gambit.games.feature.playersandgames.remote.players;

import com.gambit.games.feature.playersandgames.model.player.GetAllPlayersResponseModel;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PlayersFragmentServiceImpl implements PlayersFragmentService {

    @Override
    public void getAllPlayers(
            int pageNumber,
            int perPage,
            PlayersFragmentFactory playersFragmentFactory,
            PlayersFragmentGetAllPlayersCallback playersFragmentGetAllPlayersCallback) {


        playersFragmentFactory.getAllPlayers(pageNumber, perPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<GetAllPlayersResponseModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<GetAllPlayersResponseModel> getAllPlayersResponseModelResponse) {
                        if (getAllPlayersResponseModelResponse.raw().networkResponse() != null &&
                                getAllPlayersResponseModelResponse.isSuccessful()||
                                getAllPlayersResponseModelResponse.code() == HttpsURLConnection.HTTP_OK ||
                                getAllPlayersResponseModelResponse.code() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                            playersFragmentGetAllPlayersCallback.onResponse(getAllPlayersResponseModelResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        playersFragmentGetAllPlayersCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getAllSearchPlayers(
            String search,
            PlayersFragmentFactory playersFragmentFactory,
            PlayersFragmentSearchCallback playersFragmentSearchCallback) {


        playersFragmentFactory.getAllSearchPlayers(search)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .debounce(5, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .subscribe(new Observer<Response<GetAllPlayersResponseModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<GetAllPlayersResponseModel> getAllPlayersResponseModelResponse) {

                        if (getAllPlayersResponseModelResponse.raw().networkResponse()!=null &&
                            getAllPlayersResponseModelResponse.raw().networkResponse().isSuccessful() ||
                            getAllPlayersResponseModelResponse.code() == HttpURLConnection.HTTP_OK ||
                            getAllPlayersResponseModelResponse.code() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                            playersFragmentSearchCallback.onResponse(getAllPlayersResponseModelResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        playersFragmentSearchCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
