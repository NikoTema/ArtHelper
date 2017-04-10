package com.example.arteme.myapplication.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arteme.myapplication.ActivityShootCond;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond1;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond2;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond3;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond4;

import java.util.HashMap;

public class TabsPagerFrAdShootCond extends FragmentPagerAdapter {

    private String[] tabs;
    private HashMap<String, Bundle> mBundles;

    public TabsPagerFrAdShootCond(FragmentManager fm, HashMap<String, Bundle> bundles) {
        super(fm);
        tabs = new String[]{"Метео", "Баллистика", "Разведка", "Поправки"};
        mBundles = bundles;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return getTab1Instance();
            case 1:
                return getTab2Instance();
            case 2:
                return getTab3Instance();
            case 3:
                return getTab4Instance();
        }
        return null;
    }

    private Fragment getTab1Instance() {
        Bundle args;
        if ((args = mBundles.get(ActivityShootCond.SHOOTCOND_TAB1)) == null) args = new Bundle();
        TabFragmentShootCond1 fragment = new TabFragmentShootCond1();
        fragment.setArguments(args);
        return fragment;
    }

    private Fragment getTab2Instance() {
        Bundle args;
        if((args = mBundles.get(ActivityShootCond.SHOOTCOND_TAB2)) == null) args = new Bundle();
        TabFragmentShootCond2 fragment = new TabFragmentShootCond2();
        fragment.setArguments(args);
        return fragment;
    }

    private Fragment getTab3Instance() {
        Bundle args;
        if((args = mBundles.get(ActivityShootCond.SHOOTCOND_TAB3)) == null) args = new Bundle();
        TabFragmentShootCond3 fragment = new TabFragmentShootCond3();
        fragment.setArguments(args);
        return fragment;
    }

    private Fragment getTab4Instance() {
        Bundle args;
        if((args = mBundles.get(ActivityShootCond.SHOOTCOND_TAB4)) == null) args = new Bundle();
        TabFragmentShootCond4 fragment = new TabFragmentShootCond4();
        fragment.setArguments(args);
        return fragment;
    }



}
