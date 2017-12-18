package com.example.arteme.myapplication.tabs.FireTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arteme.myapplication.CalculateFire;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1Meteo;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;

import static java.lang.Math.abs;


public class TabFragmentFireTask1 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab1_firetask;
    private Button btnFTСalculate;
    private View view;

    private SaveDataTab1Meteo mSaveDataTab1Meteo;
    private SaveDataTab2SC mSaveDataTab2SC;
    private SaveDataTab3SC mSaveDataTab3SC;

    private SaveDataTab2CO mSaveDataTab2CO;
    private SaveDataTab1CO mSaveDataTab1CO;

    public static TabFragmentFireTask1 getInstance(){
        Bundle args = new Bundle();
        TabFragmentFireTask1 fragment = new TabFragmentFireTask1();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        view = inflater.inflate(LAYOUT, container, false);
        //readDataFromActivities();
        initButtons();
        return view;
    }

    private void initButtons(){
        btnFTСalculate = (Button) view.findViewById(R.id.btnFTCalculate);
        btnFTСalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                CalculateFire calculateFire = new CalculateFire(getContext());

                double workSystem[][] = new double[][]{};
                workSystem = calculateFire.arrSystemCharge(calculateFire.getSaveDataTab1CO().spinnerSystemPosition,
                        calculateFire.getSaveDataTab1CO().spinnerPacketPosition,
                        calculateFire.getSaveDataTab1CO().spinnerChargePosition);

                double delXcOp = Double.parseDouble(calculateFire.getSaveDataTab3SC().Xc) - Double.parseDouble(calculateFire.getSaveDataTab2CO().Xop);
                double delYcOp = Double.parseDouble(calculateFire.getSaveDataTab3SC().Yc) - Double.parseDouble(calculateFire.getSaveDataTab2CO().Yop);

                double dalTopo = DalInDel(delXcOp, delYcOp);    // Дальность топографическая
                int karetDal = calculateFire.retDalKaret(dalTopo, workSystem);

                double dirOpC = DirRumb(delXcOp, delYcOp);  // Дирикционный угол с ОП на Ц
                double aon = Integer.valueOf(calculateFire.getSaveDataTab2CO().Aon1) + Integer.valueOf(calculateFire.getSaveDataTab2CO().Aon2)*0.01;
                double dovtc = dirOpC - aon;    //Доворот топографический по цели
                double dovmet = calculateFire.retDeldsum(workSystem, karetDal, (int)dalTopo, dirOpC);
                double dovic = dovtc + dovmet;  // Доворот исчисленный

                double delDalSum = calculateFire.retdelDalSum(workSystem, karetDal,
                        (int)dalTopo, Integer.valueOf(calculateFire.getSaveDataTab3SC().Ac1) +
                        Integer.valueOf(calculateFire.getSaveDataTab3SC().Ac2)*0.01);

                int dalic = (int)dalTopo + (int)delDalSum; // Дальность исчисленная по цели

                double Ur = 30; // Уровень

                if(!calculateFire.getSaveDataTab3SC().Hc.isEmpty() || !calculateFire.getSaveDataTab2CO().Hop.isEmpty())
                    Ur = (Double.parseDouble(calculateFire.getSaveDataTab3SC().Hc) -
                        Double.parseDouble(calculateFire.getSaveDataTab2CO().Hop)/0.001*dalTopo)*0.95; // Уровень


                karetDal = calculateFire.retDalKaret(dalic, workSystem);
                double pr = calculateFire.retPr(workSystem, karetDal, dalic);  //Прицел
                double xtis = calculateFire.retDelXtis(workSystem, karetDal, dalic); // Xтыс

                double delXknpOp = Double.parseDouble(calculateFire.getSaveDataTab2CO().Xop) - Double.parseDouble(calculateFire.getSaveDataTab2CO().Xop);
                double delYknpOp = Double.parseDouble(calculateFire.getSaveDataTab2CO().Yop) - Double.parseDouble(calculateFire.getSaveDataTab2CO().Yop);

                double dalKom = DalInDel(delXknpOp, delYknpOp);
                double dirKnpC = DirRumb(delXknpOp, delYknpOp);  // Дирикционный угол с КНП на Ц

                double Ku = dalKom/dalTopo;     //Ку
                double PS = dirKnpC - dirOpC;   //ПС
                double Sugl = abs(PS)/0.01*dalTopo;  //Шу

                String OP = "";

                if(PS < 0)
                    OP = "Слева";
                else
                    OP = "Справа";
            }
        });
    }

/*    private void readDataFromActivities() {
        mSaveDataTab1Meteo = (SaveDataTab1Meteo) ((ActivityFireTask)getActivity()).readDataFromSharedPrefs(ActivityShootCond.SHOOTCOND_TAB1);
        if (mSaveDataTab1Meteo == null) ToastUtil.showErrorToast(getActivity(),"");

        mSaveDataTab2SC = (SaveDataTab2SC) ((ActivityFireTask)getActivity()).readDataFromSharedPrefs(ActivityShootCond.SHOOTCOND_TAB2);
        if (mSaveDataTab2SC == null) ToastUtil.showErrorToast(getActivity(),"");

        mSaveDataTab3SC = (SaveDataTab3SC) ((ActivityFireTask)getActivity()).readDataFromSharedPrefs(ActivityShootCond.SHOOTCOND_TAB3);
        if (mSaveDataTab2SC == null) ToastUtil.showErrorToast(getActivity(),"");

        mSaveDataTab1CO = (SaveDataTab1CO) ((ActivityFireTask)getActivity()).readDataFromSharedPrefs(ActivityComOrd.COMORD_TAB1);
        if (mSaveDataTab2SC == null) ToastUtil.showErrorToast(getActivity(),"");

        mSaveDataTab2CO = (SaveDataTab2CO) ((ActivityFireTask)getActivity()).readDataFromSharedPrefs(ActivityComOrd.COMORD_TAB2);
        if (mSaveDataTab2SC == null) ToastUtil.showErrorToast(getActivity(),"");
    }*/

    @Override
    public void reStoreData(Bundle bundle) {

    }

    @Override
    public void storeDataInBundle() {

    }

    public double DirRumb(double delX, double delY){

        double rumb = Math.atan(abs(delY/delX)*(180/Math.PI));
        double dirCrn = 0;

        if ( delX > 0 && delY > 0 )
            dirCrn = rumb;
        else if( delX < 0 && delY > 0 )
            dirCrn = 180 - rumb;
        else if( delX < 0 && delY < 0 )
            dirCrn = 180 + rumb;
        else if( delX > 0 && delY < 0 )
            dirCrn = 360 - rumb;
        return dirCrn/6;
    }

    public double DalInDel(double delX, double delY){

        double dalnost = 0;

        dalnost = Math.sqrt(Math.pow(delX, 2) + Math.pow(delY, 2));

        return dalnost;
    }

}
