package com.example.arteme.myapplication.tabs.ComOrd;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.ArrayAdapter;

import com.example.arteme.myapplication.R;

import java.util.HashMap;

public class AdapterFactory {

    private HashMap<String,ArrayAdapter<CharSequence>> mArrayAdapterHashMap;

    public AdapterFactory(Context context, @LayoutRes int textViewResId) {
        String[] names = context.getResources().getStringArray(R.array.name_of_arrays);

        for (String name: names) {
            Integer idOfName = context.getResources().getIdentifier(name,"array", context.getPackageName());

            ArrayAdapter<CharSequence> add = ArrayAdapter.createFromResource(context, idOfName, textViewResId);

            mArrayAdapterHashMap.put(name,add);
        }
    }

    public ArrayAdapter<CharSequence> getArrayAdapter(String key) {
        return mArrayAdapterHashMap.get(key);
    }
}
