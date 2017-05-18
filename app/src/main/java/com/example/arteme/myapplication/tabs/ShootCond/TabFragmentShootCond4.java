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
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1SmallTable;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.ShohtingTables;

import java.text.DecimalFormat;

public class TabFragmentShootCond4 extends Fragment implements ISavedData{

    public static final int LAYOUT = R.layout.tab4_shootcond;
    private View view;
    private EditText edtModD2,edtModD4, edtModD6, edtModD8, edtModD10, edtModD12, edtModD14;
    private EditText edtModA2,edtModA4, edtModA6, edtModA8, edtModA10, edtModA12, edtModA14;
    private EditText edtApop1, edtApop2;
    EditText[] arrEditModA;
    private SaveDataTab1CO mSaveDataTab1CO;
    private SaveDataTab2SC mSaveDataTab2SC;
    private SaveDataTab1GeneralTable mSaveDataTab1GeneralTable;
    private Bundle mBundle;
    private ShohtingTables mSaveShohtingTables;
    private SaveDataTab1SmallTable mSaveDataTab1SmallTable;
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

        arrEditModA = new EditText[7];
        edtModA2 = (EditText) view.findViewById(R.id.edtModA2);
        arrEditModA[0] = edtModA2;
        edtModA4 = (EditText) view.findViewById(R.id.edtModA4);
        arrEditModA[1] = edtModA4;
        edtModA6 = (EditText) view.findViewById(R.id.edtModA6);
        arrEditModA[2] = edtModA6;
        edtModA8 = (EditText) view.findViewById(R.id.edtModA8);
        arrEditModA[3] = edtModA8;
        edtModA10 = (EditText) view.findViewById(R.id.edtModA10);
        arrEditModA[4] = edtModA10;
        edtModA12 = (EditText) view.findViewById(R.id.edtModA12);
        arrEditModA[5] = edtModA12;
        edtModA14 = (EditText) view.findViewById(R.id.edtModA14);
        arrEditModA[6] = edtModA14;

        edtApop1 = (EditText) view.findViewById(R.id.editAPop1);
        edtApop2 = (EditText) view.findViewById(R.id.editAPop2);
        readTab1ComOrd();
        readTab1ShootCond();
        readTab2ShootCond();
        return view;
    }

    private void initButtons() {
        btnSСCalculate = (Button) view.findViewById(R.id.btnSСCalculate);
        btnSСCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSaveShohtingTables = new ShohtingTables();
                double workSystem[][] = new double[][] {};
                workSystem = arrSystemCharge();
                int karet = 2000;
                double maxDal = workSystem[0][workSystem[0].length - 1];

                for(int i = 0; i < 7; i++)
                {
                    int karetDal = retDalKaret(karet, workSystem);
                    double delDsum = retDelDsum(workSystem, karetDal, karet);
                    String formattedDouble = new DecimalFormat("#0.00").format(delDsum);
                    if (karet < maxDal)
                        arrEditModA[i].setText(formattedDouble);
                    else
                        arrEditModA[i].setText("not");
                    karet += 2000;
                }

                int karetDal = retDalKaret(2000, workSystem);
                double delDwx = 0.1 * retDelXw(workSystem, karetDal, 2000) * retWx(workSystem, karetDal);

                double delDh =  0.1 * retDelXh(workSystem, karetDal, 2000);

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

    public double[][] arrSystemCharge()
    {

        double retArr[][] = new double[16][];
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


    public  double retDelDsum(double arrCharge[][], int retKaret, int dal)
    {
        double delZw = retDelZw(arrCharge, retKaret, dal) * 0.01;
        double Z = retZ(arrCharge, retKaret, dal) * 0.01;

        int wz = retWz(arrCharge, retKaret);
        double delDwz = (0.1 * delZw * wz);
        double delDsum = Z + delDwz;

        return delDsum;
    }

    public double retAw(double awBull)
    {
        double aon = Integer.valueOf(edtApop1.getText().toString()) + Integer.valueOf(edtApop2.getText().toString())*0.01;
        double aw = aon - (awBull);
        if (aw < 0)
            aw = aw + 60;

        return aw;
    }

    public int retDalKaret(int dalTopo, double arrCharge[][])
    {
        int retKaret = 0;

        for(int i = 0; i < arrCharge[0].length - 1; i++)
        {
            if(dalTopo > arrCharge[0][i])
                retKaret ++;
            else
                break;
        }

        return retKaret;
    }

    public double retZ(double arrCharge[][], int retKaret, int dal)
    {
        double z = arrCharge[5][retKaret] - (((arrCharge[5][retKaret] - arrCharge[5][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return z;
    }

    public  double retDelZw(double arrCharge[][], int retKaret, int dal)
    {
        double delZw = arrCharge[6][retKaret] - (((arrCharge[6][retKaret] - arrCharge[6][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delZw;
    }

    public double retDelXw(double arrCharge[][], int retKaret, int dal)
    {
        double delXw = arrCharge[7][retKaret] - (((arrCharge[7][retKaret] - arrCharge[7][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXw;
    }

    public double retDelXh(double arrCharge[][], int retKaret, int dal)
    {
        double delXh = arrCharge[8][retKaret] - (((arrCharge[8][retKaret] - arrCharge[8][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXh;
    }

    public double retDelXtv(double arrCharge[][], int retKaret, int dal)
    {
        double delXtv = arrCharge[10][retKaret] - (((arrCharge[10][retKaret] - arrCharge[10][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXtv;
    }

    public double retDelXvo(double arrCharge[][], int retKaret, int dal)
    {
        double delXvo = arrCharge[12][retKaret] - (((arrCharge[12][retKaret] - arrCharge[12][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXvo;
    }

    public double retDelXtz(double arrCharge[][], int retKaret, int dal)
    {
        double delXtz = arrCharge[11][retKaret] - (((arrCharge[11][retKaret] - arrCharge[11][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXtz;
    }

    public double retDelH()
    {
        double retH = Double.parseDouble(mSaveDataTab1SmallTable.delH);
        double hMeteo = (Double.parseDouble(mSaveDataTab1SmallTable.meteO) - 100)/10;

        return retH;
    }
    

    public double retYbull(double arrCharge[][], int retKaret)
    {
        double yBull = arrCharge[15][retKaret];

        return yBull;
    }

    public int retKaretMeteo(double yBull)
    {
        int retKaretMeteo = 0;

        if(yBull <= 200)
            retKaretMeteo = 0;
        else if(yBull > 200 & yBull <= 400)
            retKaretMeteo = 1;
        else if(yBull > 400 & yBull <= 800)
            retKaretMeteo = 2;
        else if(yBull > 800 & yBull <= 1200)
            retKaretMeteo = 3;
        else if(yBull > 1200 & yBull <= 1600)
            retKaretMeteo = 4;
        else if(yBull > 1600 & yBull <= 2000)
            retKaretMeteo = 5;
        else if(yBull > 2000 & yBull <= 2400)
            retKaretMeteo = 6;
        else if(yBull > 2400 & yBull <= 3000)
            retKaretMeteo = 7;
        else if(yBull > 3000 & yBull <= 4000)
            retKaretMeteo = 8;
        else if(yBull > 4000)
            retKaretMeteo = 9;

        return retKaretMeteo;
    }

    public String retAwBull(int retKaretMeteo)
    {
        String retAw = "";
        switch (retKaretMeteo){
            case 0:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw02;
                break;
            case 1:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw04;
                break;
            case 2:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw08;
                break;
            case 3:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw12;
                break;
            case 4:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw16;
                break;
            case 5:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw20;
                break;
            case 6:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw24;
                break;
            case 7:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw30;
                break;
            case 8:
                retAw = mSaveDataTab1GeneralTable.mSaveDataTab1BulAw.meteoAw40;
                break;
        }

        return retAw;
    }

    public String retWbull(int retKaretMeteo)
    {
        String retW = "";
        switch (retKaretMeteo){
            case 0:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW02;
                break;
            case 1:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW04;
                break;
            case 2:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW08;
                break;
            case 3:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW12;
                break;
            case 4:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW16;
                break;
            case 5:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW20;
                break;
            case 6:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW24;
                break;
            case 7:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW30;
                break;
            case 8:
                retW = mSaveDataTab1GeneralTable.mSaveDataTab1BulW.meteoW40;
                break;
        }

        return retW;
    }

    public int retWz(double arrCharge[][], int retKaret)
    {

        double yBull = retYbull(arrCharge, retKaret);
        int karetMeteo = retKaretMeteo(yBull);
        double awBull = Double.parseDouble(retAwBull(karetMeteo));
        double wBull = Double.parseDouble(retWbull(karetMeteo));

        double aw = retAw(awBull);

        int awCell = (int)Math.ceil(aw);
        int vwCell = (int)Math.ceil(wBull);
        int retWz = mSaveShohtingTables.arWz[awCell][vwCell];

        return retWz;
    }

    public int retWx(double arrCharge[][], int retKaret)
    {
        double yBull = retYbull(arrCharge, retKaret);
        int karetMeteo = retKaretMeteo(yBull);
        double awBull = Double.parseDouble(retAwBull(karetMeteo));
        double wBull = Double.parseDouble(retWbull(karetMeteo));

        double aw = retAw(awBull);

        int awCell = (int)Math.ceil(aw);
        int vwCell = (int)Math.ceil(wBull);
        int retWx = mSaveShohtingTables.arWx[awCell][vwCell];

        return retWx;
    }
}