package com.example.arteme.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private final static int INTERNET_PERMISSION_REQUEST = 8435;
    private final static int ACCESS_FINE_LOCATION_PERMISSION_REQUEST = 8434;
    private final static int ACCESS_NETWORK_STATE_PERMISSION_REQUEST = 8436;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionChecker();
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

    private void permissionChecker() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.INTERNET},
                    INTERNET_PERMISSION_REQUEST);
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_PERMISSION_REQUEST);
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    ACCESS_NETWORK_STATE_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}