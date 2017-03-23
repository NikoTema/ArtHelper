package com.example.arteme.myapplication.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond1;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond2;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond3;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond4;

import java.util.ArrayList;

import static android.R.attr.fragment;

public class TabsPagerFrAdShootCond extends FragmentPagerAdapter {

    private String[] tabs;
    private ArrayList<Bundle> mBundles;

    public TabsPagerFrAdShootCond(FragmentManager fm, ArrayList<Bundle> bundles){
        super(fm);
        tabs = new String[]{"Метео", "Баллистика", "Разведка", "Поправки"};
        mBundles = bundles;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return getTab1Instance();
            case 1:
                return TabFragmentShootCond2.getInstance();
            case 2:
                return TabFragmentShootCond3.getInstance();
            case 3:
                return TabFragmentShootCond4.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    private Fragment getTab1Instance() {
        Bundle args;
        if ((args = mBundles.get(0)) == null) args = new Bundle();
        TabFragmentShootCond1 fragment = new TabFragmentShootCond1();
        fragment.setArguments(args);
        return fragment;
    }
}
