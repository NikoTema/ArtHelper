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
import com.example.arteme.myapplication.MainActivity;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.ToastUtil;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TabFragmentShootCond3 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab3_shootcond;
    private View view;
    private Button btnSСPGZ;
    private EditText edtXc, edtYc, edtHc, edtDk, edtAc1, edtAc2;
    private Bundle mBundle;
    private SaveDataTab3SC mSaveDataTab3SC;
    private SaveDataTab2CO mSaveDataTab2CO;

    @Override
    public void onResume() {
        super.onResume();
        if(mBundle != null) reStoreData(mBundle);
        else mBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);
        initButtons();
        if((mBundle = getArguments()) != null) reStoreData(mBundle);
        else mBundle = new Bundle();
        readTab2ComOrd();
        return view;
    }

    private void readTab2ComOrd() {
        mSaveDataTab2CO = ((ActivityShootCond) getActivity()).readFromSharedSaveDataTab2CO();
        if(mSaveDataTab2CO == null) {
            //TODO хз что делать смотри сам
        }
    }

    private void initButtons() {
        btnSСPGZ = (Button) view.findViewById(R.id.btnSСPGZ);
        initEdits();
        btnSСPGZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtil.hideKeyboard(getActivity());

                if(edtDk.getText().length() == 0)
                    ToastUtil.showErrorToast(getActivity(), getString(R.string.error_Dk));
                else if(edtAc1.getText().length() == 0 || edtAc2.getText().length() == 0)
                    ToastUtil.showErrorToast(getActivity(), getString(R.string.error_Ac));
                else {
                    double ac = (Double.parseDouble(edtAc1.getText().toString()) + Double.parseDouble(edtAc2.getText().toString()) * 0.01) * 6;
                    int[] arrXYc = {0, 0};

                    if(mSaveDataTab2CO == null )
                      ToastUtil.showErrorToast(getActivity(), getString(R.string.error_XYknp));
                    else {
                        if(mSaveDataTab2CO.Xknp.isEmpty() || mSaveDataTab2CO.Yknp.isEmpty())
                            ToastUtil.showErrorToast(getActivity(), getString(R.string.error_XYknp));
                        else {
                            arrXYc = Pgz(Double.parseDouble(edtDk.getText().toString()), ac, Integer.parseInt(mSaveDataTab2CO.Xknp), Integer.parseInt(mSaveDataTab2CO.Yknp));
                            edtXc.setText(String.valueOf(arrXYc[0]));
                            edtYc.setText(String.valueOf(arrXYc[1]));
                        }
                    }
                }
            }
        });
    }

    private void initEdits() {
        edtXc = (EditText) view.findViewById(R.id.edtXc);
        edtYc = (EditText) view.findViewById(R.id.edtYc);
        edtHc = (EditText) view.findViewById(R.id.edtHc);
        edtDk = (EditText) view.findViewById(R.id.editDk);
        edtAc1 = (EditText) view.findViewById(R.id.editAc1);
        edtAc2 = (EditText) view.findViewById(R.id.editAc2);
    }

    private int[] Pgz(double Dk, double Ac, int Xknp, int Yknp) {
        int[] arrXY = {0, 0};
        double delX, delY;

        delX = Dk*cos(Ac*6);
        delY = Dk*sin(Ac*6);

        arrXY[0] = (int)delX + Xknp;
        arrXY[1] = (int)delY + Yknp;

        return arrXY;
    }

    @Override
    public void reStoreData(Bundle bundle) {
        mSaveDataTab3SC = (SaveDataTab3SC) bundle.getSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY);
        if (mSaveDataTab3SC == null) {
            mSaveDataTab3SC = new SaveDataTab3SC("","","","","","");
            return;
        }
        edtAc1.setText(mSaveDataTab3SC.Ac1);
        edtAc2.setText(mSaveDataTab3SC.Ac2);
        edtDk.setText(mSaveDataTab3SC.Dk);
        edtHc.setText(mSaveDataTab3SC.Hc);
        edtXc.setText(mSaveDataTab3SC.Xc);
        edtYc.setText(mSaveDataTab3SC.Yc);
    }

    @Override
    public void storeDataInBundle() {
        getTextFromEdits();
        mBundle.putSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY,mSaveDataTab3SC);
        ((ActivityShootCond) getActivity()).saveBundle(LAYOUT, mBundle);
    }

    private void getTextFromEdits() {
        mSaveDataTab3SC.Ac1 = edtAc1.getText().toString();
        mSaveDataTab3SC.Ac2 = edtAc2.getText().toString();
        mSaveDataTab3SC.Xc = edtXc.getText().toString();
        mSaveDataTab3SC.Hc = edtHc.getText().toString();
        mSaveDataTab3SC.Yc = edtYc.getText().toString();
        mSaveDataTab3SC.Dk = edtDk.getText().toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeDataInBundle();
    }
}
