package com.example.arteme.myapplication.di;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.arteme.myapplication.DataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StoreModule {

    @Provides
    @NonNull
    @Singleton
    public DataStore provideDataStore(SharedPreferences sharedPreferences) {
        return new DataStore(sharedPreferences);
    }
}
