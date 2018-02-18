package com.example.arteme.myapplication.tabs.SavedObject;

import java.io.Serializable;

public class SaveDataTab1CO implements Serializable {
    public int spinnerSystemPosition, spinnerPacketPosition, spinnerChargePosition, spinnerFusePosition;
    public SaveSpinnerItemSlected systemSpinner;
    public SaveSpinnerItemSlected packetSpinner;
    public SaveSpinnerItemSlected chargeSpinner;
    public SaveSpinnerItemSlected fuseSpinner;

    public SaveDataTab1CO() {
        spinnerSystemPosition = 0;
        spinnerPacketPosition = 0;
        spinnerChargePosition = 0;
        spinnerFusePosition = 0;
    }

}
