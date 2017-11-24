package com.example.arteme.myapplication.tabs.FireTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.R;


public class TabFragmentFireTask1 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab1_firetask;
    private View view;

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
        return view;
    }

    @Override
    public void reStoreData(Bundle bundle) {

    }

    @Override
    public void storeDataInBundle() {

    }

}
