package com.example.arteme.myapplication.di;


import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;
import static com.example.arteme.myapplication.ArtHelperApplication.APP_SHARED_PREFS;

@Module
public class SharedPrefsModule {

    private SharedPreferences mSharedPreferences;

    public SharedPrefsModule() {

    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPrefs(Context context) {
        mSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, MODE_PRIVATE);
        mSharedPreferences.edit().clear().commit();//apply - background || commit - immediately
        return mSharedPreferences;
    }
}
