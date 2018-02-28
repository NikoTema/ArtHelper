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
import com.example.arteme.myapplication.CalculateFire;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.ToastUtil;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1GeneralTable;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.ShotingTables.Charge2s3OF25;

import java.text.DecimalFormat;

import static com.example.arteme.myapplication.ActivityComOrd.COMORD_TAB1;
import static com.example.arteme.myapplication.ActivityComOrd.COMORD_TAB2;

public class TabFragmentShootCond4 extends Fragment implements ISavedData{

    public static final int LAYOUT = R.layout.tab4_shootcond;
    private View view;
    private EditText edtModD2,edtModD4, edtModD6, edtModD8, edtModD10, edtModD12, edtModD14;
    private EditText edtModA2,edtModA4, edtModA6, edtModA8, edtModA10, edtModA12, edtModA14;
    private EditText edtApop1, edtApop2;
    EditText[] arrEditModA, arrEditModD;
    private SaveDataTab1CO mSaveDataTab1CO;
    private SaveDataTab2CO mSaveDataTab2CO;
    private SaveDataTab2SC mSaveDataTab2SC;
    private SaveDataTab1GeneralTable mSaveDataTab1GeneralTable;
    private Bundle mBundle;
    private Charge2s3OF25 mSaveCharge2s3OF25;
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

        arrEditModD = new EditText[7];
        edtModD2 = (EditText) view.findViewById(R.id.edtModD2);
        arrEditModD[0] = edtModD2;
        edtModD4 = (EditText) view.findViewById(R.id.edtModD4);
        arrEditModD[1] = edtModD4;
        edtModD6 = (EditText) view.findViewById(R.id.edtModD6);
        arrEditModD[2] = edtModD6;
        edtModD8 = (EditText) view.findViewById(R.id.edtModD8);
        arrEditModD[3] = edtModD8;
        edtModD10 = (EditText) view.findViewById(R.id.edtModD10);
        arrEditModD[4] = edtModD10;
        edtModD12 = (EditText) view.findViewById(R.id.edtModD12);
        arrEditModD[5] = edtModD12;
        edtModD14 = (EditText) view.findViewById(R.id.edtModD14);
        arrEditModD[6] = edtModD14;

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
        readTab2ComOrd();
        return view;
    }

    private void initButtons() {
        btnSСCalculate = (Button) view.findViewById(R.id.btnSСCalculate);
        btnSСCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtil.hideKeyboard(getActivity());
                CalculateFire calculateFire = new CalculateFire(getContext());

                if(mSaveDataTab1CO == null)
                    ToastUtil.showErrorToast(getActivity(), getString(R.string.error_destruct));
                else if(mSaveDataTab1GeneralTable == null)
                    ToastUtil.showErrorToast(getActivity(), getString(R.string.error_meteo));
                else {
                    if(mSaveDataTab2SC == null)
                        ToastUtil.showErrorToast(getActivity(), getString(R.string.error_ballistic));
                    else{
                        if(mSaveDataTab2SC.temperCharge == null || mSaveDataTab2SC.temperCharge.isEmpty() )
                            ToastUtil.showErrorToast(getActivity(), getString(R.string.error_Tz));
                        else if(mSaveDataTab2SC.vosum == null || mSaveDataTab2SC.vosum.isEmpty())
                            ToastUtil.showErrorToast(getActivity(), getString(R.string.error_Vosum));
                        else if(edtApop1.length() == 0 || edtApop2.length() == 0)
                            ToastUtil.showErrorToast(getActivity(), getString(R.string.error_A));
                        else{

                           // mSaveDataTab1GeneralTable = calculateFire.getSaveDataTab1SC(getActivity());
                           // mSaveDataTab2SC = calculateFire.getSaveDataTab2SC(getActivity());
                            mSaveDataTab2CO = calculateFire.getSaveDataTab2CO(getActivity());

                            double workSystem[][] = new double[][]{};
                            workSystem = calculateFire.arrSystemCharge(calculateFire.getSaveDataTab1CO(getActivity()).spinnerSystemPosition,
                                    calculateFire.getSaveDataTab1CO(getActivity()).spinnerPacketPosition,
                                    calculateFire.getSaveDataTab1CO(getActivity()).spinnerChargePosition);

                            int karet = 2000;
                            double maxDal = workSystem[0][workSystem[0].length - 1];
                            double aras = Integer.valueOf(edtApop1.getText().toString()) +
                                    Integer.valueOf(edtApop2.getText().toString())*0.01;

                            for (int i = 0; i < 7; i++) {

                                int karetDal = calculateFire.retDalKaret(karet, workSystem);
                                double delDsum = calculateFire.retDeldsum(workSystem, karetDal, karet, aras, mSaveDataTab1GeneralTable);
                                double delDalSum = calculateFire.retdelDalSum(workSystem, karetDal, karet, aras, mSaveDataTab1GeneralTable,
                                        mSaveDataTab2SC, mSaveDataTab2CO);
                                String formattedDouble = new DecimalFormat("#0.00").format(delDsum);
                                String doubleDal = new DecimalFormat("#0").format(delDalSum);

                                if (karet < maxDal) {
                                    arrEditModA[i].setText(formattedDouble);
                                    arrEditModD[i].setText(doubleDal);
                                } else {
                                    arrEditModA[i].setText("not");
                                    arrEditModD[i].setText("not");
                                }
                                karet += 2000;
                            }
                        }
                    }
                }
            }
        });
    }

    private void readTab1ComOrd() {
        mSaveDataTab1CO = (SaveDataTab1CO) ((ActivityShootCond) getActivity()).readSerializableTab(COMORD_TAB1, SaveDataTab1CO.class);
        if(mSaveDataTab1CO == null) {

        }
    }

    private void readTab1ShootCond() {
        mSaveDataTab1GeneralTable = ((ActivityShootCond) getActivity()).reStoreGeneralTableTab1();
        if(mSaveDataTab1GeneralTable == null) {
            //TODO - 2 хз что делать смотри сам
        }
    }

    private void readTab2ShootCond() {
        mSaveDataTab2SC = (SaveDataTab2SC) ((ActivityShootCond) getActivity()).readFromSharedSaveDataTab2SC();
        if(mSaveDataTab2SC == null) {
            //TODO - 3 хз что делать смотри сам
        }
    }

    private void readTab2ComOrd() {
        mSaveDataTab2CO = (SaveDataTab2CO) ((ActivityShootCond) getActivity()).readSerializableTab(COMORD_TAB2, SaveDataTab2CO.class);
        if(mSaveDataTab2CO == null) {
            //TODO хз что делать смотри сам
        }
    }

    @Override
    public void reStoreData(Bundle bundle) {

    }

    @Override
    public void storeDataInBundle() {

    }

}