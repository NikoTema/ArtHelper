package com.example.arteme.myapplication;

import android.app.Application;

import com.example.arteme.myapplication.di.ContextModule;
import com.example.arteme.myapplication.di.InjectionComponent;
import com.example.arteme.myapplication.di.SharedPrefsModule;


public class ArtHelperApplication extends Application {

    public static final String APP_SHARED_PREFS = "com.example.arteme.shared";
    public static final String BUNDLE_SAVED_DATA_KEY = "savedData";

    private static InjectionComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = getComponent();
    }

    protected InjectionComponent getComponent() {
        return DaggerInjectionComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .sharedPrefsModule(new SharedPrefsModule())
                .build();
    }

    public static InjectionComponent getInjectionComponent() {
        return sComponent;
    }
}
