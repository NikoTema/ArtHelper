package com.example.arteme.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.arteme.myapplication.tabs.FireTask.TabFragmentFireTask1;
import com.example.arteme.myapplication.tabs.TabsPagerFrAdFireTask;

import java.util.HashMap;

import javax.inject.Inject;


public class ActivityFireTask extends AppCompatActivity {

    public static final String FIRE_TASK_TAB1 = "fireTaskTab1";

    @Inject
    SharedPreferences mSharedPreferences;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private Bundle mBundleTab1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firetask);
        //mSharedPreferences = getSharedPreferences(APP_SHARED_PREFS, MODE_PRIVATE);
        ArtHelperApplication.getInjectionComponent().injectFireTask(this);
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

}
