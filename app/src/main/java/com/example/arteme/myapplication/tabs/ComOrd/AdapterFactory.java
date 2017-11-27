package com.example.arteme.myapplication.tabs.ComOrd;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.ArrayAdapter;

import com.example.arteme.myapplication.R;

import java.util.HashMap;

public class AdapterFactory {

    private HashMap<String,ArrayAdapter<CharSequence>> mArrayAdapterHashMap;
    private Context mContext;

    public AdapterFactory(Context context, @LayoutRes int textViewResId) {
        mContext = context;

        createArrayAdapterHashMap(textViewResId);
    }

    private void createArrayAdapterHashMap(@LayoutRes int textViewResId) {
        mArrayAdapterHashMap = new HashMap<>();

        String[] names = mContext.getResources().getStringArray(R.array.name_of_arrays);

        for (String name: names) {
            Integer idOfName = mContext.getResources().getIdentifier(name,"array", mContext.getPackageName());

            ArrayAdapter<CharSequence> add = ArrayAdapter.createFromResource(mContext, idOfName, textViewResId);

            mArrayAdapterHashMap.put(name, add);
        }
    }

    public ArrayAdapter<CharSequence> getArrayAdapter(String key) {
        return mArrayAdapterHashMap.get(key);
    }
}
