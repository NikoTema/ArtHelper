package com.example.arteme.myapplication.tabs.FireTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.arteme.myapplication.CalculateFire;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.ToastUtil;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1GeneralTable;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1Meteo;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;

import java.text.DecimalFormat;

import static java.lang.Math.abs;
import static java.lang.Math.round;


public class TabFragmentFireTask1 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab1_firetask;
    private Button btnFTСalculate;
    private View view;
    private EditText edtPR, edtUr, edtDovi, edtKu, edtShu, edtOP, edtDi, edtPS, edtdelXtis;

    private SaveDataTab1Meteo mSaveDataTab1Meteo;
    private SaveDataTab1GeneralTable mSaveDataTab1GeneralTable;
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

        edtPR = (EditText) view.findViewById(R.id.edtPr);
        edtUr = (EditText) view.findViewById(R.id.edtUr);
        edtDovi = (EditText) view.findViewById(R.id.edtdovi);
        edtKu  = (EditText) view.findViewById(R.id.edtKu);
        edtShu = (EditText) view.findViewById(R.id.edtShu);
        edtOP = (EditText) view.findViewById(R.id.edtOp);
        edtDi = (EditText) view.findViewById(R.id.edtDic);
        edtPS = (EditText) view.findViewById(R.id.edtPs);
        edtdelXtis = (EditText) view.findViewById(R.id.edtdelXt);



        initButtons();
        return view;
    }

    private void initButtons(){
        btnFTСalculate = (Button) view.findViewById(R.id.btnFTCalculate);
        btnFTСalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                CalculateFire calculateFire = new CalculateFire(getContext());

                if(checkFire(calculateFire)) {

                    double workSystem[][] = new double[][]{};
                    mSaveDataTab1CO = calculateFire.getSaveDataTab1CO(getActivity());
                    mSaveDataTab2CO = calculateFire.getSaveDataTab2CO(getActivity());
                    mSaveDataTab2SC = calculateFire.getSaveDataTab2SC(getActivity());
                    mSaveDataTab3SC = calculateFire.getSaveDataTab3SC(getActivity());
                    mSaveDataTab1GeneralTable = calculateFire.getSaveDataTab1SC(getActivity());

                    workSystem = calculateFire.arrSystemCharge(mSaveDataTab1CO.spinnerSystemPosition,
                            mSaveDataTab1CO.spinnerPacketPosition,
                            mSaveDataTab1CO.spinnerChargePosition);

                    double delXcOp = Double.parseDouble(mSaveDataTab3SC.Xc) -
                            Double.parseDouble(mSaveDataTab2CO.Xop);
                    double delYcOp = Double.parseDouble(mSaveDataTab3SC.Yc) -
                            Double.parseDouble(mSaveDataTab2CO.Yop);

                    double dalTopo = DalInDel(delXcOp, delYcOp);    // Дальность топографическая
                    int karetDal = calculateFire.retDalKaret(dalTopo, workSystem);

                    double dirOpC = dirRumb(delXcOp, delYcOp);  // Дирикционный угол с ОП на Ц
                    double aon = Integer.valueOf(mSaveDataTab2CO.Aon1) +
                            Integer.valueOf(mSaveDataTab2CO.Aon2) * 0.01;
                    double dovtc = dirOpC - aon;    //Доворот топографический по цели
                    double dovmet = calculateFire.retDeldsum(workSystem, karetDal, (int) dalTopo, aon,
                            mSaveDataTab1GeneralTable);
                    double dovic = dovtc + dovmet * 0.01;  // Доворот исчисленный
                    edtDovi.setText(roundThous(dovic * 0.01));

                    double delDalSum = calculateFire.retdelDalSum(workSystem, karetDal,
                            (int) dalTopo, Integer.valueOf(mSaveDataTab3SC.Ac1) +
                                    Integer.valueOf(mSaveDataTab3SC.Ac2) * 0.01, mSaveDataTab1GeneralTable, mSaveDataTab2SC, mSaveDataTab2CO);

                    int dalic = (int) dalTopo + (int) delDalSum; // Дальность исчисленная по цели
                    edtDi.setText(String.valueOf(dalic));

                    double Ur = 30; // Уровень

                    if (!mSaveDataTab3SC.Hc.isEmpty() ||
                            !mSaveDataTab2CO.Hop.isEmpty())
                        Ur = Ur + (((Double.parseDouble(mSaveDataTab3SC.Hc) -
                                Double.parseDouble(mSaveDataTab2CO.Hop)) / (0.001 * dalTopo)) * (0.95 * 0.01));
                    // Уровень
                    edtUr.setText(roundThous(Ur));

                    karetDal = calculateFire.retDalKaret(dalic, workSystem);
                    double pr = calculateFire.retPr(workSystem, karetDal, dalic);  //Прицел
                    edtPR.setText(Double.toString(Math.round(pr)));

                    double xtis = calculateFire.retDelXtis(workSystem, karetDal, dalic); // Xтыс
                    edtdelXtis.setText(Double.toString(Math.round(xtis)));

                    double delXknpC = Double.parseDouble(mSaveDataTab3SC.Xc) -
                            Double.parseDouble(mSaveDataTab2CO.Xknp);
                    double delYknpC = Double.parseDouble(mSaveDataTab3SC.Yc) -
                            Double.parseDouble(mSaveDataTab2CO.Yknp);

                    double dalKom = DalInDel(delXknpC, delYknpC);
                    double dirKnpC = dirRumb(delXknpC, delYknpC);  // Дирикционный угол с КНП на Ц

                    double Ku = dalKom / dalTopo;     //Ку
                    edtKu.setText(roundThous(Ku));

                    double PS = dirKnpC - dirOpC;   //ПС
                    edtPS.setText(roundThous(abs(PS)));

                    double Shu = abs(PS * 100) / (0.01 * dalTopo);  //Шу
                    edtShu.setText(roundThous(Shu));

                    String positionOP = retPositionOp(PS);
                    edtOP.setText(positionOP);
                }
            }
        });
    }

 /*   private void readDataFromActivities() {
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

    public double DalInDel(double delX, double delY){

        double dalnost = 0;

        dalnost = Math.sqrt(Math.pow(delX, 2) + Math.pow(delY, 2));

        return dalnost;
    }

    public String retPositionOp(double PS)
    {
        String OP = "";

        if(PS < 0)
            OP = "Слева";
        else
            OP = "Справа";

        return OP;
    }

    public String roundThous(double num)
    {
        String formatted = new DecimalFormat("#0.00").format(num);
        return formatted;
    }

    public double dirRumb(double delX, double delY){

        double rumb = (Math.atan2(abs(delY), abs(delX)))*(180/Math.PI);
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

    public boolean checkFire(CalculateFire calculateFire)
    {
        boolean retVar = true;

        if(calculateFire.getSaveDataTab1CO(getActivity()) == null)
            retVar =  false;
        else if(calculateFire.getSaveDataTab2SC(getActivity()) == null)
            retVar = false;
        else if(calculateFire.getSaveDataTab3SC(getActivity()) == null)
            retVar =  false;
        else if(calculateFire.getSaveDataTab2CO(getActivity()) == null)
            retVar =  false;
        else if(calculateFire.getSaveDataTab1SC(getActivity()) == null)
            retVar = false;

        return retVar;
    }

}
