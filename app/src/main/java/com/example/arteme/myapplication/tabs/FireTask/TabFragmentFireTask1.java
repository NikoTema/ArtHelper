package com.example.arteme.myapplication.tabs.FireTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arteme.myapplication.ActivityFireTask;
import com.example.arteme.myapplication.ActivityShootCond;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;


public class TabFragmentFireTask1 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab1_firetask;
    private Button btnFTСalculate;
    private View view;
    private SaveDataTab3SC mSaveDataTab3SC;
    private SaveDataTab2CO mSaveDataTab2CO;

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
        initButtons();
        return view;
    }

    private void initButtons(){
        btnFTСalculate = (Button) view.findViewById(R.id.btnFTCalculate);
        btnFTСalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                double delXcOp = Double.parseDouble(mSaveDataTab3SC.Xc) - Double.parseDouble(mSaveDataTab2CO.Xop);
                double delYcOp = Double.parseDouble(mSaveDataTab3SC.Yc) - Double.parseDouble(mSaveDataTab2CO.Yop);
                double dirOpC = DirRumb(delXcOp, delYcOp);
                double aon = Integer.valueOf(mSaveDataTab2CO.Aon1) + Integer.valueOf(mSaveDataTab2CO.Aon2)*0.01;
                double dovtc = dirOpC - aon;
                //dovic = dovtc + dovmet;

                double dalTopo = DalInDel(delXcOp, delYcOp);
                //dalic = dalTopo + dalmet;

            }
        });
    }

    @Override
    public void reStoreData(Bundle bundle) {

    }

    @Override
    public void storeDataInBundle() {

    }

    public double DirRumb(double delX, double delY){

        double rumb = Math.atan(Math.abs(delY/delX)*(180/Math.PI));
        double dirCrn = 0;

        if ( delX > 0 && delY > 0 )
            dirCrn = rumb;
        else if( delX < 0 && delY > 0 )
            dirCrn = 180 - rumb;
        else if( delX < 0 && delY < 0 )
            dirCrn = 180 + rumb;
        else if( delX > 0 && delY < 0 )
            dirCrn = 360 - rumb;
        return dirCrn;
    }

    public double DalInDel(double delX, double delY){

        double dalnost = 0;

        dalnost = Math.sqrt(Math.pow(delX, 2) + Math.pow(delY, 2));

        return dalnost;
    }

}
