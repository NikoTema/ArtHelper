package com.example.arteme.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.arteme.myapplication.tabs.FireTask.TabFragmentFireTask1;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1Meteo;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;
import com.example.arteme.myapplication.tabs.TabsPagerFrAdFireTask;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

import static com.example.arteme.myapplication.ActivityComOrd.COMORD_TAB1;
import static com.example.arteme.myapplication.ActivityComOrd.COMORD_TAB2;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB1;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB2;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB3;

/**
 * Created by arteme on 24.11.17.
 */

public class ActivityFireTask extends AppCompatActivity {

    public static final String FIRE_TASK_TAB1 = "fireTaskTab1";

    private SharedPreferences mSharedPreferences;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private Bundle mBundleTab1;

    private Bundle mBundleSCTab1;
    private Bundle mBundleSCTab2;
    private Bundle mBundleSCTab3;

    private Bundle mBundleCOTab1;
    private Bundle mBundleCOTab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firetask);
        mSharedPreferences = getSharedPreferences(MainActivity.APP_SHARED_PREFS, MODE_PRIVATE);
        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbarFireTask);
        toolbar.setTitle("Огневая задача");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initNavigationView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_firetask);
    }

    private void initTabs() {

        viewPager = (ViewPager) findViewById(R.id.viewPagerFireTask);
        readTabBundleFromShared();
        TabsPagerFrAdFireTask adapter = new TabsPagerFrAdFireTask(getSupportFragmentManager(), getBundleHashMap());
        viewPager.setAdapter(adapter);

       // TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutFireTask);
        //tabLayout.setupWithViewPager(viewPager);
    }


    private HashMap<String, Bundle> getBundleHashMap() {
        HashMap<String, Bundle> result = new HashMap<>();
        result.put(FIRE_TASK_TAB1, mBundleTab1);
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
        //saveBundleInSharedPrefs();
    }

    public void saveBundle(int tag, Bundle bundle) {
        switch (tag) {
            case TabFragmentFireTask1.LAYOUT:
                mBundleTab1 = bundle;
                break;
            default:
                break;
        }
    }

    private void readTabBundleFromShared() {

    }
    
    public Serializable readDataFromSharedPrefs(String tab) {
        String json = null;
        switch (tab) {
            case SHOOTCOND_TAB1:
                json = mSharedPreferences.getString(SHOOTCOND_TAB1, "");
                return (new Gson()).fromJson(json,SaveDataTab1Meteo.class);

            case SHOOTCOND_TAB2:
                json = mSharedPreferences.getString(SHOOTCOND_TAB2, "");
                return (new Gson()).fromJson(json,SaveDataTab2SC.class);

            case SHOOTCOND_TAB3:
                json = mSharedPreferences.getString(SHOOTCOND_TAB3, "");
                return (new Gson()).fromJson(json,SaveDataTab3SC.class);

            case COMORD_TAB1:
                json = mSharedPreferences.getString(COMORD_TAB1, "");
                return (new Gson()).fromJson(json, SaveDataTab1CO.class);

            case COMORD_TAB2:
                json = mSharedPreferences.getString(COMORD_TAB2, "");
                return (new Gson()).fromJson(json, SaveDataTab2CO.class);

            default:
                break;
        }
        return null;
    }
}
