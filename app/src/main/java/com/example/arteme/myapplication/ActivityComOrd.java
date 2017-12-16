package com.example.arteme.myapplication;

import android.content.SharedPreferences;
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
import com.google.gson.Gson;

import java.util.HashMap;

import javax.inject.Inject;

import static com.example.arteme.myapplication.ArtHelperApplication.BUNDLE_SAVED_DATA_KEY;

public class ActivityComOrd extends AppCompatActivity {

    public static final String COMORD_TAB1 = "comOrdTab1";
    public static final String COMORD_TAB2 = "comOrdTab2";

    @Inject
    SharedPreferences mSharedPreferences;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private Bundle mBundleTab1;
    private Bundle mBundleTab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comord);
        //mSharedPreferences = getSharedPreferences(APP_SHARED_PREFS, MODE_PRIVATE);
        ArtHelperApplication.getInjectionComponent().injectComOrd(this);
        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initToolbar(){
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
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mBundleTab1.getSerializable(BUNDLE_SAVED_DATA_KEY));
        //TODO save..
        editor.putString(COMORD_TAB1, json);
        json = gson.toJson(mBundleTab2.getSerializable(BUNDLE_SAVED_DATA_KEY));
        editor.putString(COMORD_TAB2, json);
        editor.apply();
    }

    private void initNavigationView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_comord);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPagerComOrd);
        readTabBundleFromShared();
        TabsPagerFrAdComOrd adapter = new TabsPagerFrAdComOrd(getSupportFragmentManager(), getBundleHashMap());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutComOrd);
        tabLayout.setupWithViewPager(viewPager);
    }

    private HashMap<String,Bundle> getBundleHashMap() {
        HashMap<String, Bundle> result = new HashMap<>();
        result.put(COMORD_TAB1, mBundleTab1);
        result.put(COMORD_TAB2, mBundleTab2);
        return result;
    }

    private void readTabBundleFromShared() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(COMORD_TAB1,"");
        mBundleTab1 = new Bundle();
        mBundleTab1.putSerializable(BUNDLE_SAVED_DATA_KEY, gson.fromJson(json, SaveDataTab1CO.class));
        //TODO for tab2
        json = mSharedPreferences.getString(COMORD_TAB2, "");
        mBundleTab2 = new Bundle();
        mBundleTab2.putSerializable(BUNDLE_SAVED_DATA_KEY, gson.fromJson(json, SaveDataTab2CO.class));
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
            case TabFragmentComOrd1.LAYOUT:
                mBundleTab1 = bundle;
                break;
            case TabFragmentComOrd2.LAYOUT:
                mBundleTab2 = bundle;
                break;
            default:
                break;
        }
    }

}
