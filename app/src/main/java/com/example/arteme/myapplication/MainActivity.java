package com.example.arteme.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public static final String APP_SHARED_PREFS = "com.example.arteme.shared";
    public static final String BUNDLE_SAVED_DATA_KEY = "savedData";
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(APP_SHARED_PREFS, MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();//apply - background || commit - immediately
        setContentView(R.layout.activity_main);

    }

    public void sendComOrd(View view) {
        Intent intent = new Intent(this, ActivityComOrd.class);
        startActivity(intent);
    }

    public void sendShootCond(View view){
        Intent intent = new Intent(this, ActivityShootCond.class);
        startActivity(intent);
    }

}