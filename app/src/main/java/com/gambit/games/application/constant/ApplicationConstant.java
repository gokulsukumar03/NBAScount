package com.gambit.games.application.constant;

import android.content.Context;

import com.gambit.games.BuildConfig;


public interface ApplicationConstant {

    /*Shared preference*/
    String SHARED_PREFERENCE_NAME = "gambit_shared_preference";
    int SHARED_PREFERENCE_MODE = Context.MODE_PRIVATE;

    /*Retrofit*/
    int CONNECT_TIMEOUT = 20;
    int READ_TIMEOUT = 20;
    int WRITE_TIMEOUT = 20;
    String GSON_SET_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    String BASE_URL = "https://www.balldontlie.io/api/";
    boolean BUILD_CONFIG = BuildConfig.DEBUG;

}
