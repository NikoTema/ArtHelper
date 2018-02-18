package com.example.arteme.myapplication.tabs.ComOrd;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ComOrdSpinnerData {

    private final static String JSON_SPINNER_VALUES = "comord1.json";

    private JsonParserUtil mJsonParserUtil;
    private JSONObject mJSONObjects;

    private String currentAdapterSystemName = null;
    private String currentAdapterPacketName = null;
    private String currentAdapterChargeName = null;
    private String currentAdapterFuseName = "fuse_array";


    public ComOrdSpinnerData(Context context) {
        //mContext= context;
        mJsonParserUtil = new JsonParserUtil(context, JSON_SPINNER_VALUES);
        mJSONObjects = mJsonParserUtil.getJSONObject();
    }

    public void initCurrentSpinnerNames(String systemArrayName) {
        currentAdapterSystemName = systemArrayName;
        currentAdapterPacketName = getFirstValueFromJsonObject(currentAdapterSystemName);
        currentAdapterChargeName = getFirstValueFromJsonObject(currentAdapterPacketName);
    }

    @Nullable
    private String getFirstValueFromJsonObject(String name) {
        String result = null;
        JSONObject json = getJsonObject(name);
        try {
            if (json != null) {
                Iterator<String> keys = json.keys();
                if (keys.hasNext())
                    result = json.getString(keys.next());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Nullable
    private JSONObject getJsonObject(String key) {
        JSONObject obj = null;
        try {
            obj = mJSONObjects.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private String getValueFromJsonObject(String nameOfObject, String key) {
        String result = null;

        try {
            JSONObject obj = mJSONObjects.getJSONObject(nameOfObject);

            result = obj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getAssociativeKey(int spinnerId, String value) {
        String result = null;
        switch (spinnerId) {
            case TabFragmentComOrd1.SYSTEM_SPINNER:
                currentAdapterPacketName = getValueFromJsonObject(currentAdapterSystemName,value);
                result = currentAdapterPacketName;
                break;
            case TabFragmentComOrd1.PACKET_SPINNER:
                currentAdapterChargeName = getValueFromJsonObject(currentAdapterPacketName, value);
                result = currentAdapterChargeName;
                break;
            case TabFragmentComOrd1.CHARGE_SPINNER:
                String associateCharge = getValueFromJsonObject(currentAdapterChargeName, value);
                if (!TextUtils.isEmpty(associateCharge)) {
                    currentAdapterFuseName = associateCharge;
                }
                result = currentAdapterFuseName;
                break;

            default:
                break;
        }
        return result;
    }

    public String getCurrentAdapterSystemName() {
        return currentAdapterSystemName;
    }

    public String getCurrentAdapterPacketName() {
        return currentAdapterPacketName;
    }

    public String getCurrentAdapterChargeName() {
        return currentAdapterChargeName;
    }

    public String getCurrentAdapterFuseName() {
        return currentAdapterFuseName;
    }
}
