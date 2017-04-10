package com.example.arteme.myapplication.tabs.SavedObject;

import java.io.Serializable;

public class SaveDataTab2SC implements Serializable {
    public String temperCharge;
    public String vosum;

    public SaveDataTab2SC(String temCharge, String vo){

        temperCharge = temCharge;
        vosum = vo;
    }
}
