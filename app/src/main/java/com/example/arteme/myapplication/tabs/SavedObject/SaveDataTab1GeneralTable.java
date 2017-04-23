package com.example.arteme.myapplication.tabs.SavedObject;


import java.io.Serializable;

public class SaveDataTab1GeneralTable implements Serializable {
    public SaveDataTab1BulW mSaveDataTab1BulW;
    public SaveDataTab1BulTem mSaveDataTab1BulTem;
    public SaveDataTab1BulAw mSaveDataTab1BulAw;

    public SaveDataTab1GeneralTable() {

    }

    public SaveDataTab1GeneralTable(SaveDataTab1BulAw saveDataTab1BulAw, SaveDataTab1BulTem saveDataTab1BulTem, SaveDataTab1BulW saveDataTab1BulW) {
        mSaveDataTab1BulAw = saveDataTab1BulAw;
        mSaveDataTab1BulTem = saveDataTab1BulTem;
        mSaveDataTab1BulW = saveDataTab1BulW;
    }
}
