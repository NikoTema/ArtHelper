package com.example.arteme.myapplication.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arteme.myapplication.ActivityComOrd;
import com.example.arteme.myapplication.tabs.ComOrd.TabFragmentComOrd1;
import com.example.arteme.myapplication.tabs.ComOrd.TabFragmentComOrd2;

import java.util.HashMap;


public class TabsPagerFrAdComOrd extends FragmentPagerAdapter {

    private String[] tabs;
    private HashMap<String, Bundle> mBundles;

    public TabsPagerFrAdComOrd(FragmentManager fm, HashMap<String, Bundle> bundles){
        super(fm);
        tabs = new String[]{"Средства", "КНП и ОП"};
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
                return getTab2Instance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    private Fragment getTab1Instance() {
        Bundle args;
        if((args = mBundles.get(ActivityComOrd.COMORD_TAB1)) == null) args = new Bundle();
        TabFragmentComOrd1 fragmentComOrd1 = new TabFragmentComOrd1();
        fragmentComOrd1.setArguments(args);
        return fragmentComOrd1;
    }

    private Fragment getTab2Instance() {
        Bundle args;
        if ((args = mBundles.get(ActivityComOrd.COMORD_TAB2)) == null) args = new Bundle();
        TabFragmentComOrd2 fragmentComOrd2 = new TabFragmentComOrd2();
        fragmentComOrd2.setArguments(args);
        return fragmentComOrd2;
    }
}
