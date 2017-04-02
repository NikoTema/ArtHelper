package com.example.arteme.myapplication;

import android.content.SharedPreferences;
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
import com.google.gson.Gson;

import java.util.HashMap;

public class ActivityShootCond extends AppCompatActivity {

    public static final String SHOOTCOND_TAB1 = "shootCondTab1";
    public static final String SHOOTCOND_TAB2 = "shootCondTab2";
    public static final String SHOOTCOND_TAB3 = "shootCondTab3";
    public static final String SHOOTCOND_TAB4 = "shootCondTab4";


    private SharedPreferences mSharedPreferences;
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
        mSharedPreferences = getSharedPreferences(MainActivity.APP_SHARED_PREFS, MODE_PRIVATE);
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
        readTabBundleFromShared();
        TabsPagerFrAdShootCond adapter = new TabsPagerFrAdShootCond(getSupportFragmentManager(), getBundleArrayList());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutShootCond);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void readTabBundleFromShared() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(SHOOTCOND_TAB1, "");
        mBundleTab1 = new Bundle();
        mBundleTab1.putSerializable("savedData", gson.fromJson(json, SavedDataFromTab1ShootCond.class));
        json = mSharedPreferences.getString(SHOOTCOND_TAB2, "");
        mBundleTab2 = gson.fromJson(json,Bundle.class);
        json = mSharedPreferences.getString(SHOOTCOND_TAB3, "");
        mBundleTab3 = gson.fromJson(json,Bundle.class);
        json = mSharedPreferences.getString(SHOOTCOND_TAB4, "");
        mBundleTab4 = gson.fromJson(json,Bundle.class);
    }

    private HashMap<String, Bundle> getBundleArrayList() {
        HashMap<String, Bundle> result = new HashMap<>();
        result.put(SHOOTCOND_TAB1, mBundleTab1);
        result.put(SHOOTCOND_TAB2, mBundleTab2);
        result.put(SHOOTCOND_TAB3, mBundleTab3);
        result.put(SHOOTCOND_TAB4, mBundleTab4);
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveBundleInSharedPrefs();
    }

    private void saveBundleInSharedPrefs() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mBundleTab1.getSerializable("savedData"));
        editor.putString(SHOOTCOND_TAB1,json);
        json = gson.toJson(mBundleTab2);
        editor.putString(SHOOTCOND_TAB2,json);
        json = gson.toJson(mBundleTab3);
        editor.putString(SHOOTCOND_TAB3,json);
        json = gson.toJson(mBundleTab4);
        editor.putString(SHOOTCOND_TAB4,json);
        editor.apply();

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
