package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab1BulAw implements Serializable {
    public String meteoAw02;
    public String meteoAw04;
    public String meteoAw08;
    public String meteoAw12;
    public String meteoAw16;
    public String meteoAw20;
    public String meteoAw24;
    public String meteoAw30;
    public String meteoAw40;

    public SaveDataTab1BulAw(String aw02, String aw04, String aw08,
                              String aw12, String aw16, String aw20,
                              String aw24, String aw30, String aw40){
        meteoAw02 = aw02;
        meteoAw04 = aw04;
        meteoAw08 = aw08;
        meteoAw12 = aw12;
        meteoAw16 = aw16;
        meteoAw20 = aw20;
        meteoAw24 = aw24;
        meteoAw30 = aw30;
        meteoAw40 = aw40;
    }
}
