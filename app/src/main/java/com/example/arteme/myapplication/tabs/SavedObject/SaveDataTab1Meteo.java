package com.example.arteme.myapplication.tabs.SavedObject;

import java.io.Serializable;

public class SaveDataTab1Meteo implements Serializable {
    public String press;
    public String temp;
    public String windD;
    public String windS;
    public String heightMeteo;

    public SaveDataTab1Meteo(String pres, String tmpr, String windd, String winds, String hmeteo) {
        press = pres;
        temp = tmpr;
        windD = windd;
        windS = winds;
        heightMeteo = hmeteo;
    }
}
