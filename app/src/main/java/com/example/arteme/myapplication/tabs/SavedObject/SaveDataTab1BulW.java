package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;
import java.util.ArrayList;

public class SaveDataTab1BulW implements Serializable{
    public String meteoW02;
    public String meteoW04;
    public String meteoW08;
    public String meteoW12;
    public String meteoW16;
    public String meteoW20;
    public String meteoW24;
    public String meteoW30;
    public String meteoW40;

    public SaveDataTab1BulW(String w02, String w04, String w08,
                            String w12, String w16, String w20,
                            String w24, String w30, String w40){
        meteoW02 = w02;
        meteoW04 = w04;
        meteoW08 = w08;
        meteoW12 = w12;
        meteoW16 = w16;
        meteoW20 = w20;
        meteoW24 = w24;
        meteoW30 = w30;
        meteoW40 = w40;
    }

    public SaveDataTab1BulW(ArrayList<String> stringArrayList) {
        meteoW02 = stringArrayList.get(0);
        meteoW04 = stringArrayList.get(1);
        meteoW08 = stringArrayList.get(2);
        meteoW12 = stringArrayList.get(3);
        meteoW16 = stringArrayList.get(4);
        meteoW20 = stringArrayList.get(5);
        meteoW24 = stringArrayList.get(6);
        meteoW30 = stringArrayList.get(7);
        meteoW40 = stringArrayList.get(8);
    }
}
