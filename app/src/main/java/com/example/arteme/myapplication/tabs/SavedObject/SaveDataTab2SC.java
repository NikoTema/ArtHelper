package com.example.arteme.myapplication.tabs.SavedObject;

import java.io.Serializable;

public class SaveDataTab2SC implements Serializable {
    String temperCharge;
    String vosum;

    public SaveDataTab2SC(String temCharge, String vo){

        temperCharge = temCharge;
        vosum = vo;
    }
}
