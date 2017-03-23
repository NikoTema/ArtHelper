package com.example.arteme.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond1;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond2;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond3;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond4;
import com.example.arteme.myapplication.tabs.TabsPagerFrAdShootCond;

import java.util.ArrayList;

public class ActivityShootCond extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private Bundle mBundleTab1;
    private Bundle mBundleTab2;
    private Bundle mBundleTab3;
    private Bundle mBundleTab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shootcond);

        initToolbar();
        initNavigationView();
        initTabs();

    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbarShootCond);
        toolbar.setTitle("Условия стрельбы");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initNavigationView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_shootcond);
    }

    private void initTabs() {

        viewPager = (ViewPager) findViewById(R.id.viewPagerShootCond);
        TabsPagerFrAdShootCond adapter = new TabsPagerFrAdShootCond(getSupportFragmentManager(), getBundleArrayList());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutShootCond);
        tabLayout.setupWithViewPager(viewPager);
    }

    private ArrayList<Bundle> getBundleArrayList() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        arrayList.add(mBundleTab1);
        arrayList.add(mBundleTab2);
        arrayList.add(mBundleTab3);
        arrayList.add(mBundleTab4);
        return arrayList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveBundle(int tag, Bundle bundle) {
        switch (tag) {
            case TabFragmentShootCond1.LAYOUT:
                mBundleTab1 = bundle;
                break;
            case TabFragmentShootCond2.LAYOUT:
                mBundleTab2 = bundle;
                break;
            case TabFragmentShootCond3.LAYOUT:
                mBundleTab3 = bundle;
                break;
            case TabFragmentShootCond4.LAYOUT:
                mBundleTab4 = bundle;
                break;
            default:
                break;

        }
    }

    public Bundle getBundleTab1() {
        return mBundleTab1;
    }

    public Bundle getBundleTab2() {
        return mBundleTab2;
    }
    public Bundle getBundleTab3() {
        return mBundleTab3;
    }

    public Bundle getBundleTab4() {
        return mBundleTab4;
    }
}
