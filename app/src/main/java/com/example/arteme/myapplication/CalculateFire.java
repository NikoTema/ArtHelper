package com.example.arteme.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1GeneralTable;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1Meteo;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;
import com.example.arteme.myapplication.tabs.ShotingTables.Charge2s19OF25;
import com.example.arteme.myapplication.tabs.ShotingTables.Charge2s19OF45;
import com.example.arteme.myapplication.tabs.ShotingTables.Charge2s3OF25;
import com.google.gson.Gson;

import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;
import static com.example.arteme.myapplication.ActivityComOrd.COMORD_TAB1;
import static com.example.arteme.myapplication.ActivityComOrd.COMORD_TAB2;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB1;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB2;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB3;

public class CalculateFire {

    private SaveDataTab1CO mSaveDataTab1CO;
    private SaveDataTab2CO mSaveDataTab2CO;
    private SaveDataTab2SC mSaveDataTab2SC;
    private SaveDataTab3SC mSaveDataTab3SC;
    private SaveDataTab1GeneralTable mSaveDataTab1GeneralTable;

    private Charge2s3OF25 mSaveCharge2s3OF25;
    private Charge2s19OF25 mSaveCharge2s19OF25;
    private Charge2s19OF45 mSaveCharge2s19OF45;

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public CalculateFire(Context context)
    {
        mSaveCharge2s3OF25 = new Charge2s3OF25();
        mSaveCharge2s19OF25 = new Charge2s19OF25();
        mSaveCharge2s19OF45 = new Charge2s19OF45();
        mSaveDataTab1GeneralTable = new SaveDataTab1GeneralTable();

        mContext = context;
        getSharedPrefs();
    }

    private void getSharedPrefs() {
        mSharedPreferences = mContext.getSharedPreferences(MainActivity.APP_SHARED_PREFS, MODE_PRIVATE);
    }


    public double[][] arrSystemCharge(int system, int packet, int charge)
    {

        double retArr[][] = new double[16][];
        switch (system) {
            case 0:
                switch (packet) {
                    case 0:
                        switch (charge) {
                            case 0:
                                retArr = mSaveCharge2s3OF25.charge2s3_3_of25;
                                break;
                            case 1:
                                retArr = mSaveCharge2s3OF25.charge2s3_6_of25;
                                break;
                        }
                        break;
                }
                break;
            case 1:
                switch (packet){
                    case 0:
                        switch (charge){
                            case 0:
                                retArr = mSaveCharge2s19OF25.charge2s19_P_of25;
                                break;
                            case 1:
                                retArr = mSaveCharge2s19OF25.charge2s19_2_of25;
                                break;
                            case 2:
                                retArr = mSaveCharge2s19OF25.charge2s19_3_of25;
                                break;
                            case 3:
                                retArr = mSaveCharge2s19OF25.charge2s19_4_of25;
                                break;
                        }
                        break;
                    case 1:
                        switch (charge){
                            case 0:
                                retArr = mSaveCharge2s19OF45.charge2s19_D_of45;
                                break;
                            case 1:
                                retArr = mSaveCharge2s19OF45.charge2s19_P_of45;
                                break;
                            case 2:
                                retArr = mSaveCharge2s19OF45.charge2s19_2_of45;
                                break;
                            case 3:
                                retArr = mSaveCharge2s19OF45.charge2s19_3_of45;
                                break;
                            case 4:
                                retArr = mSaveCharge2s19OF45.charge2s19_4_of45;
                                break;
                        }
                        break;
                }
                break;
        }
        return retArr;
    }

    public double retYbull(double arrCharge[][], int retKaret)
    {
        double yBull = arrCharge[15][retKaret];

        return yBull;
    }

    public double retDelXw(double arrCharge[][], int retKaret, int dal)
    {
        double delXw = arrCharge[7][retKaret] - (((arrCharge[7][retKaret] - arrCharge[7][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXw * (-1);
    }

    public double retDelXh(double arrCharge[][], int retKaret, int dal)
    {
        double delXh = arrCharge[8][retKaret] - (((arrCharge[8][retKaret] - arrCharge[8][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXh;
    }

    public double retDelXtv(double arrCharge[][], int retKaret, int dal)
    {
        double delXtv = arrCharge[10][retKaret] - (((arrCharge[10][retKaret] - arrCharge[10][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXtv * (-1);
    }

    public double retDelXvo(double arrCharge[][], int retKaret, int dal)
    {
        double delXvo = arrCharge[12][retKaret] - (((arrCharge[12][retKaret] - arrCharge[12][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXvo*(-1);
    }

    public double retDelXtz(double arrCharge[][], int retKaret, int dal)
    {
        double delXtz = arrCharge[11][retKaret] - (((arrCharge[11][retKaret] - arrCharge[11][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delXtz * (-1);
    }

    public  double retDelZw(double arrCharge[][], int retKaret, int dal)
    {
        double delZw = arrCharge[6][retKaret] - (((arrCharge[6][retKaret] - arrCharge[6][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return delZw * (-1);
    }

    public double retZ(double arrCharge[][], int retKaret, int dal)
    {
        double z = arrCharge[5][retKaret] - (((arrCharge[5][retKaret] - arrCharge[5][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return z * (-1);
    }

    public double retPr(double arrCharge[][], int retKaret, int dal)
    {
        double pr = arrCharge[1][retKaret] - (((arrCharge[1][retKaret] - arrCharge[1][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return pr;
    }

    public double retDelXtis(double arrCharge[][], int retKaret, int dal)
    {
        double xtis = arrCharge[2][retKaret] - (((arrCharge[2][retKaret] - arrCharge[2][retKaret - 1])/200 * (arrCharge[0][retKaret] - dal)));

        return xtis;
    }

    public int retDalKaret(double dalTopo, double arrCharge[][])
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

    public double retDelH()
    {
        int retH = Integer.parseInt(mSaveDataTab1GeneralTable.mSaveDataTab1SmallTable.delH);
        double hop;
        if(mSaveDataTab2CO == null)
            hop = 0;
        else{
            if(mSaveDataTab2CO.Hop.isEmpty())
                hop = 0;
            else
                hop = Double.parseDouble(mSaveDataTab2CO.Hop);
        }
        double hMeteo = (Double.parseDouble(mSaveDataTab1GeneralTable.mSaveDataTab1SmallTable.meteO) - hop)/10;

        double retVar = 0;

        retVar = retH + hMeteo;

        return retVar;
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

    public String retTbull(int retKaretMeteo)
    {
        String retT = "";
        switch (retKaretMeteo){
            case 0:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT02;
                break;
            case 1:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT04;
                break;
            case 2:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT08;
                break;
            case 3:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT12;
                break;
            case 4:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT16;
                break;
            case 5:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT20;
                break;
            case 6:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT24;
                break;
            case 7:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT30;
                break;
            case 8:
                retT = mSaveDataTab1GeneralTable.mSaveDataTab1BulTem.meteoT40;
                break;
        }

        int intVar = Integer.parseInt(retT);
        if(intVar > 50)
            intVar = (intVar - 50)*(-1);
        if(intVar == 50)
            intVar = 0;

        return String.valueOf(intVar);
    }

    public double retAw(double awBull, double A)
    {
        double aw = A - (awBull);
        if (aw < 0)
            aw = aw + 60;

        return aw;
    }

    public int retWz(double arrCharge[][], int retKaret, double A)
    {

        double yBull = retYbull(arrCharge, retKaret);
        int karetMeteo = retKaretMeteo(yBull);
        double awBull = Double.parseDouble(retAwBull(karetMeteo));
        double wBull = Double.parseDouble(retWbull(karetMeteo));

        double aw = retAw(awBull, A);

        int awCell = (int)Math.ceil(aw);
        int vwCell = (int)Math.ceil(wBull);
        int retWz = mSaveCharge2s3OF25.arWz[awCell][vwCell];

        return retWz;
    }

    public int retWx(double arrCharge[][], int retKaret, double A)
    {
        double yBull = retYbull(arrCharge, retKaret);
        int karetMeteo = retKaretMeteo(yBull);
        double awBull = Double.parseDouble(retAwBull(karetMeteo));
        double wBull = Double.parseDouble(retWbull(karetMeteo));

        double aw = retAw(awBull, A);

        int awCell = (int)Math.ceil(aw);
        int vwCell = (int)Math.ceil(wBull);
        int retWx = mSaveCharge2s3OF25.arWx[awCell][vwCell - 1];

        return retWx;
    }

    public  double retDeldsum(double arrCharge[][], int retKaret, int dal, double A)
    {
        double delZw = retDelZw(arrCharge, retKaret, dal);
        double Z = retZ(arrCharge, retKaret, dal);

        int wz = retWz(arrCharge, retKaret, A);
        double delDwz = (0.1 * delZw * wz);
        double delDsum = Z + delDwz;

        return delDsum;
    }



    public double retdelDalSum(double arrCharge[][], int retKaret, int dal, double A)
    {
        double delDwx = 0.1 * retDelXw(arrCharge, retKaret, dal) * retWx(arrCharge, retKaret, A);

        double delDh = 0.1 * retDelXh(arrCharge, retKaret, dal) * retDelH();

        double test = retYbull(arrCharge, retKaret);
        double delDTv = 0.1 * retDelXtv(arrCharge, retKaret, dal) * Double.parseDouble(retTbull(retKaretMeteo(test)));

        double delDVo = retDelXvo(arrCharge, retKaret, dal) * Double.parseDouble(mSaveDataTab2SC.vosum);

        double delDTz = 0.1 * retDelXtz(arrCharge, retKaret, dal) * (Double.parseDouble(mSaveDataTab2SC.temperCharge) - 15);

        double delDalSum = delDwx + delDh + delDTv + delDVo + delDTz;

        return delDalSum;

    }

    @Nullable
    private  Serializable readDataFromSharedPrefs(String tab) {
        String json = null;
        switch (tab) {
            case SHOOTCOND_TAB1:
                json = mSharedPreferences.getString(SHOOTCOND_TAB1, "");
                return (new Gson()).fromJson(json,SaveDataTab1Meteo.class);

            case SHOOTCOND_TAB2:
                json = mSharedPreferences.getString(SHOOTCOND_TAB2, "");
                return (new Gson()).fromJson(json,SaveDataTab2SC.class);

            case SHOOTCOND_TAB3:
                json = mSharedPreferences.getString(SHOOTCOND_TAB3, "");
                return (new Gson()).fromJson(json,SaveDataTab3SC.class);

            case COMORD_TAB1:
                json = mSharedPreferences.getString(COMORD_TAB1, "");
                return (new Gson()).fromJson(json, SaveDataTab1CO.class);

            case COMORD_TAB2:
                json = mSharedPreferences.getString(COMORD_TAB2, "");
                return (new Gson()).fromJson(json, SaveDataTab2CO.class);

            default:
                break;
        }
        return null;
    }

    private void readTabsFromCO() {
        mSaveDataTab1CO = (SaveDataTab1CO) readDataFromSharedPrefs(COMORD_TAB1);
        mSaveDataTab2CO = (SaveDataTab2CO) readDataFromSharedPrefs(COMORD_TAB2);
    }

    private void readTabsFromSC() {
        mSaveDataTab1GeneralTable = (SaveDataTab1GeneralTable) readDataFromSharedPrefs(SHOOTCOND_TAB1);
        mSaveDataTab2SC = (SaveDataTab2SC) readDataFromSharedPrefs(SHOOTCOND_TAB2);
        mSaveDataTab3SC = (SaveDataTab3SC) readDataFromSharedPrefs(SHOOTCOND_TAB3);
    }

    public SaveDataTab2SC getSaveDataTab2SC()
    {
        readTabsFromSC();
        return mSaveDataTab2SC;
    }

    public SaveDataTab1CO getSaveDataTab1CO()
    {
        readTabsFromCO();
        return mSaveDataTab1CO;
    }

    public SaveDataTab2CO getSaveDataTab2CO()
    {
        readTabsFromCO();
        return mSaveDataTab2CO;
    }

    public SaveDataTab3SC getSaveDataTab3SC()
    {
        readTabsFromSC();
        return mSaveDataTab3SC;
    }

}
