package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab1SmallTable implements Serializable {
    public String datA;
    public String meteO;
    public String delH;
    public String delT;

    public SaveDataTab1SmallTable(String data, String meteo, String delh, String delt) {
        datA = data;
        meteO = meteo;
        delH = delh;
        delT = delt;
    }
}
