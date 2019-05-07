package com.gambit.games.feature.playersandgames.usercase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import com.gambit.games.application.config.RemoteServiceConfig;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesInfo;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesResponseModel;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentFactory;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentGellAllGamesCallback;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentService;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentServiceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

public class GamesFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<GetAllGamesInfo>>  allGamesList;
    private MutableLiveData<String> toastMessage=null;
    private MutableLiveData<String> currentTime=null;
    private File cacheFile = null;
    private GamesFragmentService gamesFragmentService = null;
    private GamesFragmentFactory gamesFragmentFactory = null;
    private Application application;
    private CountDownTimer countDownTimer;

    public GamesFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        if(toastMessage==null){
            toastMessage = new MutableLiveData<>();
        }

        if(currentTime==null){
            currentTime = new MutableLiveData<>();
        }
    }

    public LiveData<String> getCurrentTime(){

        countDownTimer = new CountDownTimer(300000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                currentTime.setValue(new Date().toString());
            }

            @Override
            public void onFinish() {
                currentTime.setValue("Timer completed");
            }
        }.start();

        return currentTime;
    }


    public LiveData<List<GetAllGamesInfo>> getAllGames(){

        if(allGamesList==null){
            allGamesList = new MutableLiveData<>();
        }

        loadGetAllGames();
        return allGamesList;
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    private void loadGetAllGames(){
        toastMessage.setValue("Fetching games...");
        cacheFile = new File(application.getCacheDir(), "responses");
        gamesFragmentFactory = new RemoteServiceConfig<GamesFragmentFactory>().createApiService(GamesFragmentFactory.class, cacheFile);
        gamesFragmentService = new GamesFragmentServiceImpl();

        gamesFragmentService.getAllGames(gamesFragmentFactory,
                new GamesFragmentGellAllGamesCallback() {
                    @Override
                    public void onSuccess(GetAllGamesResponseModel getAllGamesResponseModel) {

                        allGamesList.setValue(getAllGamesResponseModel.getData());

                    }

                    @Override
                    public void onError(String errorMessage) {
                        toastMessage.setValue(errorMessage);
                    }
                });
    }


    public static class GamesFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private Application application;


        public GamesFragmentViewModelFactory(@NonNull Application application){
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new GamesFragmentViewModel(application);
        }
    }



}
