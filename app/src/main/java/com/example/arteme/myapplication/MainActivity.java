package com.example.arteme.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void sendFireTask(View view){
        Intent intent = new Intent(this, ActivityFireTask.class);
        startActivity(intent);
    }

}