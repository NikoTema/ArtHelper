package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab2CO implements Serializable{

    public int Xknp;
    public int Yknp;
    public int Hknp;
    public int Xop;
    public int Yop;
    public int Hop;

    public SaveDataTab2CO(int xknp, int yknp, int hknp,
                          int xop, int yop, int hop){

        Xknp = xknp;
        Yknp = yknp;
        Hknp = hknp;
        Xop = xop;
        Yop = yop;
        Hop = hop;
    }

}
