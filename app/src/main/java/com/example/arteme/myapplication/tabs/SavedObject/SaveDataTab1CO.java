package com.example.arteme.myapplication.tabs.SavedObject;

import java.io.Serializable;

public class SaveDataTab1CO implements Serializable {
    public int spinnerSystemPosition, spinnerPacketPosition, spinnerChargePosition, spinnerFusePosition;
    public SaveDataTab1CO() {}
    public SaveDataTab1CO(int sysPos, int packSys, int charPos, int fusePos) {
        spinnerSystemPosition = sysPos;
        spinnerPacketPosition = packSys;
        spinnerChargePosition = charPos;
        spinnerFusePosition = fusePos;
    }

}