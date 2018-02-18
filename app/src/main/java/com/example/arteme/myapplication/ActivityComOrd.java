package com.example.arteme.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.arteme.myapplication.tabs.ComOrd.TabFragmentComOrd1;
import com.example.arteme.myapplication.tabs.ComOrd.TabFragmentComOrd2;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;
import com.example.arteme.myapplication.tabs.TabsPagerFrAdComOrd;

import java.io.Serializable;
import java.util.HashMap;

import javax.inject.Inject;

import static com.example.arteme.myapplication.ArtHelperApplication.BUNDLE_SAVED_DATA_KEY;

public class ActivityComOrd extends AppCompatActivity {

    public static final String COMORD_TAB1 = "comOrdTab1";
    public static final String COMORD_TAB2 = "comOrdTab2";

    @Inject
    DataStore mDataStore;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    private HashMap<String, Bundle> mBundleHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comord);

        ArtHelperApplication.getInjectionComponent().injectComOrd(this);

        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarComOrd);
        toolbar.setTitle("Боевой порядок");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveBundleInSharedPrefs();
    }

    private void saveBundleInSharedPrefs() {
        mDataStore.saveBundleInSharedPrefs(mBundleHashMap);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_comord);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPagerComOrd);
        readTabsBundleFromShared();
        TabsPagerFrAdComOrd adapter = new TabsPagerFrAdComOrd(getSupportFragmentManager(), mBundleHashMap);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutComOrd);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void readTabsBundleFromShared() {
        mBundleHashMap = new HashMap<>();

        readOneTab(COMORD_TAB1, SaveDataTab1CO.class);
        readOneTab(COMORD_TAB2, SaveDataTab2CO.class);
    }

    private void readOneTab(String tabKey, Class<?> cls) {
        Bundle readTab = new Bundle();
        readTab.putSerializable(BUNDLE_SAVED_DATA_KEY, readSerializableTab(tabKey, cls));
        mBundleHashMap.put(tabKey, readTab);
    }

    public Serializable readSerializableTab(String tabKey, Class<?> cls) {
        return (Serializable) mDataStore.readSavedTabInstanceFromShared(tabKey, cls);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveBundle(int tag, Bundle bundle) {
        switch (tag) {
            case TabFragmentComOrd1.LAYOUT:
                mBundleHashMap.put(COMORD_TAB1, bundle);
                break;
            case TabFragmentComOrd2.LAYOUT:
                mBundleHashMap.put(COMORD_TAB2, bundle);
                break;
            default:
                break;
        }
    }
}
