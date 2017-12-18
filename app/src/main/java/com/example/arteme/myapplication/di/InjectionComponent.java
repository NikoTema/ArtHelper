package com.example.arteme.myapplication.di;

import com.example.arteme.myapplication.ActivityComOrd;
import com.example.arteme.myapplication.ActivityFireTask;
import com.example.arteme.myapplication.ActivityShootCond;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ContextModule.class, SharedPrefsModule.class, StoreModule.class})
@Singleton
public interface InjectionComponent {
    void injectComOrd(ActivityComOrd activityComOrd);
    void injectShootCond(ActivityShootCond activityShootCond);
    void injectFireTask(ActivityFireTask activityFireTask);
    //CalculateFire calculateFire();
}
