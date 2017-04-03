package com.example.arteme.myapplication;


import java.io.Serializable;

public class SavedDataFromTab2ComOrd implements Serializable{

    public int Xknp;
    public int Yknp;
    public int Hknp;
    public int Xop;
    public int Yop;
    public int Hop;

    public SavedDataFromTab2ComOrd(int xknp, int yknp, int hknp,
                                   int xop, int yop, int hop){

        Xknp = xknp;
        Yknp = yknp;
        Hknp = hknp;
        Xop = xop;
        Yop = yop;
        Hop = hop;
    }

}
