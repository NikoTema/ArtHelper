package com.example.arteme.myapplication;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.HashMap;

import javax.annotation.Nullable;

import static com.example.arteme.myapplication.ActivityShootCond.BUNDLE_SAVED_DATA_KEY_GEN_TABLE;
import static com.example.arteme.myapplication.ActivityShootCond.SHOOTCOND_TAB1;
import static com.example.arteme.myapplication.ArtHelperApplication.BUNDLE_SAVED_DATA_KEY;

public class DataStore {

    private SharedPreferences mSharedPreferences;

    public DataStore(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Nullable
    public <T> T readSavedTabInstanceFromShared(String TabKey, Class<T> classOfT) {
        String json = mSharedPreferences.getString(TabKey, "");
        return (new Gson()).fromJson(json, classOfT);
    }

    public void saveBundleInSharedPrefs(HashMap<String, Bundle> data) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = "";

        for (String key: data.keySet()) {
            if(TextUtils.equals(key, SHOOTCOND_TAB1)) {
                json = gson.toJson(data.get(key).getSerializable(BUNDLE_SAVED_DATA_KEY_GEN_TABLE));
                editor.putString(BUNDLE_SAVED_DATA_KEY_GEN_TABLE, json);
            }
            json = gson.toJson(data.get(key).getSerializable(BUNDLE_SAVED_DATA_KEY));
            editor.putString(key, json);
        }

        editor.apply();
    }

}
