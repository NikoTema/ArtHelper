package com.example.arteme.myapplication.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arteme.myapplication.ActivityShootCond;
import com.example.arteme.myapplication.tabs.FireTask.TabFragmentFireTask1;

import java.util.HashMap;

/**
 * Created by arteme on 24.11.17.
 */

public class TabsPagerFrAdFireTask extends FragmentPagerAdapter {

    private String[] tabs;
    private HashMap<String, Bundle> mBundles;

    public TabsPagerFrAdFireTask(FragmentManager fm, HashMap<String, Bundle> bundles) {
        super(fm);
        tabs = new String[]{"Установки"};
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
        }
        return null;
    }

    private Fragment getTab1Instance() {
        Bundle args;
        if ((args = mBundles.get(ActivityShootCond.SHOOTCOND_TAB1)) == null) args = new Bundle();
        TabFragmentFireTask1 fragment = new TabFragmentFireTask1();
        fragment.setArguments(args);
        return fragment;
    }

}
