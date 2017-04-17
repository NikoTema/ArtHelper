package com.example.arteme.myapplication.tabs.ShootCond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;

public class TabFragmentShootCond4 extends Fragment implements ISavedData{

    public static final int LAYOUT = R.layout.tab4_shootcond;
    private View view;
    private EditText edtModD2,edtModD4, edtModD6, edtModD8, edtModD10, edtModD12, edtModD14;
    private EditText edtModA2,edtModA4, edtModA6, edtModA8, edtModA10, edtModA12, edtModA14;
    private EditText edtApop1, edtApop2;

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

        return view;
    }

    @Override
    public void reStoreData(Bundle bundle) {

    }

    @Override
    public void storeDataInBundle() {

    }
}