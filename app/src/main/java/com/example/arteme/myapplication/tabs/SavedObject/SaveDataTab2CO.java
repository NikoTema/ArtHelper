package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab2CO implements Serializable{

    public String  Xknp;
    public String Yknp;
    public String Hknp;
    public String Xop;
    public String Yop;
    public String Hop;

    public SaveDataTab2CO(String xknp, String yknp, String hknp, String xop, String yop, String hop) {
        Xknp = xknp;
        Yknp = yknp;
        Hknp = hknp;
        Xop = xop;
        Yop = yop;
        Hop = hop;
    }

}
