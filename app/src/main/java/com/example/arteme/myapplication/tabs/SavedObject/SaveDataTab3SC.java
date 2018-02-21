package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab3SC implements Serializable {
    public String Xc;
    public String Yc;
    public String Hc;
    public String Dk;
    public String Ac1;
    public String Ac2;

    public SaveDataTab3SC(String xc, String yc, String hc,
                          String dk, String ac1, String ac2) {

        Xc = xc;
        Yc = yc;
        Hc = hc;
        Dk = dk;
        Ac1 = ac1;
        Ac2 = ac2;
    }
}
