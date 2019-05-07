package com.gambit.games.application.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.gambit.games.application.constant.ApplicationConstant;


public class SharedPreferenceConfig {

    private  SharedPreferences sharedPreferences;
    private  Context context;

    public  SharedPreferences getSharedPreferences(@NonNull final Context context){
            this.context  =context;
            sharedPreferences = this.context.getSharedPreferences(ApplicationConstant.SHARED_PREFERENCE_NAME,
                    ApplicationConstant.SHARED_PREFERENCE_MODE);
        return sharedPreferences;
    }
}
