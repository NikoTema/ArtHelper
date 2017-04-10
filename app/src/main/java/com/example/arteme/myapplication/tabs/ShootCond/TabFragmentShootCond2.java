package com.example.arteme.myapplication.tabs.ShootCond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.arteme.myapplication.ActivityShootCond;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.MainActivity;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;

public class TabFragmentShootCond2 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab2_shootcond;
    private View view;
    private EditText edtTemperCharge, edtVosum;
    private Bundle mBundle;
    private SaveDataTab2SC mSaveDataTab2SC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);

        initEditText();
        if(getArguments() == null) mBundle = new Bundle();
        else reStoreData(mBundle = getArguments());
        return view;
    }

    private void initEditText() {
        edtTemperCharge = (EditText) view.findViewById(R.id.editTemperCharge);
        edtTemperCharge.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                mSaveDataTab2SC.temperCharge = edtTemperCharge.getText().toString();
                return false;
            }
        });
        edtVosum = (EditText) view.findViewById(R.id.editVoSum);
        edtVosum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                mSaveDataTab2SC.vosum = edtVosum.getText().toString();
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBundle != null) reStoreData(mBundle);
        else mBundle = new Bundle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeDataInBundle();
        ((ActivityShootCond)getActivity()).saveBundle(LAYOUT, mBundle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void reStoreData(Bundle bundle) {
        mSaveDataTab2SC = (SaveDataTab2SC) bundle.getSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY);
        if (mSaveDataTab2SC == null) {
            mSaveDataTab2SC = new SaveDataTab2SC("","");
            return;
        }
        edtTemperCharge.setText(mSaveDataTab2SC.temperCharge);
        edtVosum.setText(mSaveDataTab2SC.vosum);
    }

    @Override
    public void storeDataInBundle() {
        mBundle.putSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY, mSaveDataTab2SC);
    }
}