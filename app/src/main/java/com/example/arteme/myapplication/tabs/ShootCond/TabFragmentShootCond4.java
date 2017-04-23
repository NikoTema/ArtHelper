package com.example.arteme.myapplication.tabs.ShootCond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.arteme.myapplication.ActivityShootCond;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1GeneralTable;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.ShohtingTables;

public class TabFragmentShootCond4 extends Fragment implements ISavedData{

    public static final int LAYOUT = R.layout.tab4_shootcond;
    private View view;
    private EditText edtModD2,edtModD4, edtModD6, edtModD8, edtModD10, edtModD12, edtModD14;
    private EditText edtModA2,edtModA4, edtModA6, edtModA8, edtModA10, edtModA12, edtModA14;
    private EditText edtApop1, edtApop2;
    private SaveDataTab1CO mSaveDataTab1CO;
    private SaveDataTab2SC mSaveDataTab2SC;
    private SaveDataTab1GeneralTable mSaveDataTab1GeneralTable;
    private Bundle mBundle;
    private ShohtingTables mSaveShohtingTables;
    Button btnSСCalculate;

    public static TabFragmentShootCond4 getInstance(){
        Bundle args = new Bundle();
        TabFragmentShootCond4 fragment = new TabFragmentShootCond4();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);

        initButtons();

        edtModD2 = (EditText) view.findViewById(R.id.edtModD2);
        edtModD4 = (EditText) view.findViewById(R.id.edtModD4);
        edtModD6 = (EditText) view.findViewById(R.id.edtModD6);
        edtModD8 = (EditText) view.findViewById(R.id.edtModD8);
        edtModD10 = (EditText) view.findViewById(R.id.edtModD10);
        edtModD12 = (EditText) view.findViewById(R.id.edtModD12);
        edtModD14 = (EditText) view.findViewById(R.id.edtModD14);

        edtModA2 = (EditText) view.findViewById(R.id.edtModA2);
        edtModA4 = (EditText) view.findViewById(R.id.edtModA4);
        edtModA6 = (EditText) view.findViewById(R.id.edtModA6);
        edtModA8 = (EditText) view.findViewById(R.id.edtModA8);
        edtModA10 = (EditText) view.findViewById(R.id.edtModA10);
        edtModA12 = (EditText) view.findViewById(R.id.edtModA12);
        edtModA14 = (EditText) view.findViewById(R.id.edtModA14);

        edtApop1 = (EditText) view.findViewById(R.id.editAPop1);
        edtApop2 = (EditText) view.findViewById(R.id.editAPop2);
        readTab1ComOrd();
        readTab1ShootCond();
        readTab2ShootCond();
        mSaveShohtingTables = new ShohtingTables();
        return view;
    }

    private void initButtons() {
        btnSСCalculate = (Button) view.findViewById(R.id.btnSСCalculate);
        btnSСCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double workSystem[][] = {};
                workSystem = arrSystemCharge();

            }
        });
    }

    private void readTab1ComOrd() {
        mSaveDataTab1CO = ((ActivityShootCond) getActivity()).readFromSharedSaveDataTab1CO();
        if(mSaveDataTab1CO == null) {
            //TODO - 1 хз что делать смотри сам
        }
    }

    private void readTab1ShootCond() {
        mSaveDataTab1GeneralTable = ((ActivityShootCond) getActivity()).reStoreGeneralTableTab1();
        if(mSaveDataTab1GeneralTable == null) {
            //TODO - 2 хз что делать смотри сам
        }
    }

    private void readTab2ShootCond() {
        mSaveDataTab2SC = ((ActivityShootCond) getActivity()).readFromSharedSaveDataTab2SC();
        if(mSaveDataTab2SC == null) {
            //TODO - 3 хз что делать смотри сам
        }
    }

    @Override
    public void reStoreData(Bundle bundle) {

    }

    @Override
    public void storeDataInBundle() {

    }

    public double[][] arrSystemCharge() {

        double retArr[][] = new double[16][33];
        switch (mSaveDataTab1CO.spinnerSystemPosition) {
            case 0:
                switch (mSaveDataTab1CO.spinnerPacketPosition) {
                    case 0:
                        switch (mSaveDataTab1CO.spinnerChargePosition) {
                            case 0:
                                retArr = mSaveShohtingTables.charge2s3_3_of25;
                                break;
                            case 1:
                                retArr = mSaveShohtingTables.charge2s3_6_of25;
                                break;
                        }
                        break;
                }
                break;
        }

        return retArr;
    }
}