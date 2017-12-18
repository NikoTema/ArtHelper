package com.example.arteme.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1GeneralTable;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1Meteo;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2SC;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab3SC;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond1;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond2;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond3;
import com.example.arteme.myapplication.tabs.ShootCond.TabFragmentShootCond4;
import com.example.arteme.myapplication.tabs.TabsPagerFrAdShootCond;

import java.io.Serializable;
import java.util.HashMap;

import javax.inject.Inject;

import static com.example.arteme.myapplication.ArtHelperApplication.BUNDLE_SAVED_DATA_KEY;

public class ActivityShootCond extends AppCompatActivity {

    public static final String SHOOTCOND_TAB1 = "shootCondTab1";
    public static final String BUNDLE_SAVED_DATA_KEY_GEN_TABLE = "generalTable";
    public static final String SHOOTCOND_TAB2 = "shootCondTab2";
    public static final String SHOOTCOND_TAB3 = "shootCondTab3";
    public static final String SHOOTCOND_TAB4 = "shootCondTab4";


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    private HashMap<String, Bundle> mBundleHashMap;

    @Inject
    DataStore mDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shootcond);

        ArtHelperApplication.getInjectionComponent().injectShootCond(this);

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
        readTabsBundleFromShared();

        TabsPagerFrAdShootCond adapter = new TabsPagerFrAdShootCond(getSupportFragmentManager(), mBundleHashMap);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutShootCond);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void readTabsBundleFromShared() {
        //getBundleHashMap();
        mBundleHashMap = new HashMap<>();

        readOneTab(SHOOTCOND_TAB1, SaveDataTab1Meteo.class);
        readOneTab(SHOOTCOND_TAB2, SaveDataTab2SC.class);
        readOneTab(SHOOTCOND_TAB3, SaveDataTab3SC.class);

        Bundle readTab = new Bundle();
        mBundleHashMap.put(SHOOTCOND_TAB4, readTab);
    }

    private void readOneTab(String tabKey, Class<?> cls) {
        Bundle readTab = new Bundle();
        readTab.putSerializable(BUNDLE_SAVED_DATA_KEY, readSerializableTab(tabKey, cls));
        mBundleHashMap.put(tabKey, readTab);
    }

    public Serializable readSerializableTab(String tabKey, Class<?> cls) {
       return (Serializable) mDataStore.readSavedTabInstanceFromShared(tabKey, cls);
    }

    public SaveDataTab2SC readFromSharedSaveDataTab2SC() {
        SaveDataTab2SC result = (SaveDataTab2SC) readSerializableTab(SHOOTCOND_TAB2, SaveDataTab2SC.class);
        if (result == null) {
            result = (SaveDataTab2SC) mBundleHashMap.get(SHOOTCOND_TAB2).getSerializable(BUNDLE_SAVED_DATA_KEY);
        }
        return result;
    }

    public SaveDataTab1GeneralTable reStoreGeneralTableTab1() {
        Bundle bundle = mBundleHashMap.get(SHOOTCOND_TAB1);
        return (SaveDataTab1GeneralTable) bundle.getSerializable(BUNDLE_SAVED_DATA_KEY_GEN_TABLE);
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
        mDataStore.saveBundleInSharedPrefs(mBundleHashMap);
    }

    public void saveBundle(int tag, Bundle bundle) {
        switch (tag) {
            case TabFragmentShootCond1.LAYOUT:
                mBundleHashMap.put(SHOOTCOND_TAB1, bundle);
                break;
            case TabFragmentShootCond2.LAYOUT:
                mBundleHashMap.put(SHOOTCOND_TAB2, bundle);
                break;
            case TabFragmentShootCond3.LAYOUT:
                mBundleHashMap.put(SHOOTCOND_TAB3, bundle);
                break;
            case TabFragmentShootCond4.LAYOUT:
                mBundleHashMap.put(SHOOTCOND_TAB4, bundle);
                break;
            default:
                break;
        }
    }

}
