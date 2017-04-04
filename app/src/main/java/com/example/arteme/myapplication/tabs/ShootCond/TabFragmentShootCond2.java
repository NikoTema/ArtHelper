package com.example.arteme.myapplication.tabs.ShootCond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.arteme.myapplication.R;

public class TabFragmentShootCond2 extends Fragment {

    public static final int LAYOUT = R.layout.tab2_shootcond;
    private View view;
    EditText edtTemperCharge, edtVosum;


    public static TabFragmentShootCond2 getInstance(){
        Bundle args = new Bundle();
        TabFragmentShootCond2 fragment = new TabFragmentShootCond2();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);

        edtTemperCharge = (EditText) view.findViewById(R.id.editTemperCharge);
        edtVosum = (EditText) view.findViewById(R.id.editVoSum);

        return view;
    }
}
