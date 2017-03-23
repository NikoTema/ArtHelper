package com.example.arteme.myapplication.tabs.ComOrd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.arteme.myapplication.R;

public class TabFragmentComOrd1 extends  Fragment {

    private View view;
    private static final int LAYOUT = R.layout.tab1_comord;

    public static TabFragmentComOrd1 getInstance(){
        Bundle args = new Bundle();
        TabFragmentComOrd1 fragment = new TabFragmentComOrd1();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);
        initSpinner();
        return view;
    }

    private void initSpinner(){
        Spinner spinnerSystem, spinnerPacket, spinnerCharge, spinnerFuse;

        spinnerSystem = (Spinner) view.findViewById(R.id.system_spinner);
        spinnerPacket = (Spinner) view.findViewById(R.id.packet_spinner);
        spinnerCharge = (Spinner) view.findViewById(R.id.charge_spinner);
        spinnerFuse = (Spinner) view.findViewById(R.id.fuse_spinner);

        ArrayAdapter<CharSequence> adapterSystem = ArrayAdapter.createFromResource(getContext(),
                R.array.system_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterPacket = ArrayAdapter.createFromResource(getContext(),
                R.array.packet_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterCharge = ArrayAdapter.createFromResource(getContext(),
                R.array.charge_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterFuse = ArrayAdapter.createFromResource(getContext(),
                R.array.fuse_array, android.R.layout.simple_spinner_item);

        spinnerSystem.setAdapter(adapterSystem);
        spinnerPacket.setAdapter(adapterPacket);
        spinnerCharge.setAdapter(adapterCharge);
        spinnerFuse.setAdapter(adapterFuse);
    }


}