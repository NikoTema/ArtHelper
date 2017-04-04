package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab1BulTem implements Serializable {
    public String meteoT02;
    public String meteoT04;
    public String meteoT08;
    public String meteoT12;
    public String meteoT16;
    public String meteoT20;
    public String meteoT24;
    public String meteoT30;
    public String meteoT40;

    public SaveDataTab1BulTem(String t02, String t04, String t08,
                              String t12, String t16, String t20,
                              String t24, String t30, String t40){
        meteoT02 = t02;
        meteoT04 = t04;
        meteoT08 = t08;
        meteoT12 = t12;
        meteoT16 = t16;
        meteoT20 = t20;
        meteoT24 = t24;
        meteoT30 = t30;
        meteoT40 = t40;
    }
}
