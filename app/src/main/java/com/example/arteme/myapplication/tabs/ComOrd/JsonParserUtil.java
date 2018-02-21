package com.example.arteme.myapplication.tabs.ComOrd;


import android.content.Context;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonParserUtil {


    @Nullable
    public static JSONObject loadJSONFromAsset(Context context, String jsonFile) {
        JSONObject json = null;
        try {
            InputStream is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, "UTF-8");
            json = new JSONObject(jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

}
