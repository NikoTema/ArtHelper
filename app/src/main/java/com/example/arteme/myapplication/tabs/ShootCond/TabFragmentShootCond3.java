package com.example.arteme.myapplication.tabs.ShootCond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arteme.myapplication.R;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TabFragmentShootCond3 extends Fragment {

    public static final int LAYOUT = R.layout.tab3_shootcond;
    private View view;
    private Button btnSСPGZ;

    public static TabFragmentShootCond3 getInstance(){
        Bundle args = new Bundle();
        TabFragmentShootCond3 fragment = new TabFragmentShootCond3();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);
        initButtons();
        return view;
    }

    private void initButtons() {
        btnSСPGZ = (Button) view.findViewById(R.id.btnSСPGZ);
        btnSСPGZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private int[] Pgz(double Dk, double Ac, int Xknp, int Yknp)
    {
        int[] arrXY = {0, 0};
        double delX, delY;

        delX = Dk*cos(Ac*6);
        delY = Dk*sin(Ac*6);

        arrXY[0] = (int)delX + Xknp;
        arrXY[1] = (int)delY + Yknp;

        return arrXY;
    }
}
