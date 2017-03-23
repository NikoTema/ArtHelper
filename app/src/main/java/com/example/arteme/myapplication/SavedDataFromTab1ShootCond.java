package com.example.arteme.myapplication;

import java.io.Serializable;

public class SavedDataFromTab1ShootCond implements Serializable {
    public String press;
    public String temp;
    public String windD;
    public String windS;
    public String heightMeteo;

    public SavedDataFromTab1ShootCond(String pres, String tmpr, String windd, String winds, String hmeteo) {
        press = pres;
        temp = tmpr;
        windD = windd;
        windS = winds;
        heightMeteo = hmeteo;
    }
}
