package com.example.arteme.myapplication.tabs.ComOrd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.arteme.myapplication.ActivityComOrd;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.MainActivity;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1CO;

public class TabFragmentComOrd1 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab1_comord;
    private Spinner spinnerSystem, spinnerPacket, spinnerCharge, spinnerFuse;
    private View view;
    private SaveDataTab1CO mSaveDataTab1CO;
    private Bundle mBundle;

    @Override
    public void onResume() {
        super.onResume();
        if(mBundle != null) reStoreData(mBundle);
        else mBundle = new Bundle();

    }

    @Override
    public void reStoreData(Bundle bundle) {
        mSaveDataTab1CO = (SaveDataTab1CO) bundle.getSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY);
        if (mSaveDataTab1CO == null) {
            mSaveDataTab1CO = new SaveDataTab1CO(0,0,0,0);
            return;
        }
        spinnerSystem.setSelection(mSaveDataTab1CO.spinnerSystemPosition);
        spinnerCharge.setSelection(mSaveDataTab1CO.spinnerChargePosition);
        spinnerPacket.setSelection(mSaveDataTab1CO.spinnerPacketPosition);
        spinnerFuse.setSelection(mSaveDataTab1CO.spinnerFusePosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeDataInBundle();
        ((ActivityComOrd) getActivity()).saveBundle(LAYOUT, mBundle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        view = inflater.inflate(LAYOUT, container, false);
        initSpinner();
        if(getArguments() != null) reStoreData(mBundle = getArguments());
        else {
            mSaveDataTab1CO = new SaveDataTab1CO(0,0,0,0);
            mBundle = new Bundle();
        }
        return view;
    }

    private void initSpinner() {

        spinnerSystem = (Spinner) view.findViewById(R.id.system_spinner);
        spinnerPacket = (Spinner) view.findViewById(R.id.packet_spinner);
        spinnerCharge = (Spinner) view.findViewById(R.id.charge_spinner);
        spinnerFuse = (Spinner) view.findViewById(R.id.fuse_spinner);

        AdapterFactory adapterFactory = new AdapterFactory(getContext(), android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterSystem = ArrayAdapter.createFromResource(getContext(),
                R.array.system_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterPacket = ArrayAdapter.createFromResource(getContext(),
                R.array.packet_array_2s3, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterCharge = ArrayAdapter.createFromResource(getContext(),
                R.array.charge_array_2s3of25, android.R.layout.simple_spinner_item);


        ArrayAdapter<CharSequence> adapterFuse = ArrayAdapter.createFromResource(getContext(),
                R.array.fuse_array, android.R.layout.simple_spinner_item);

        spinnerSystem.setAdapter(adapterSystem);
        spinnerSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence s = (CharSequence) parent.getSelectedItem();
                mSaveDataTab1CO.spinnerSystemPosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPacket.setAdapter(adapterPacket);
        spinnerPacket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSaveDataTab1CO.spinnerPacketPosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCharge.setAdapter(adapterCharge);
        spinnerCharge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSaveDataTab1CO.spinnerChargePosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFuse.setAdapter(adapterFuse);
        spinnerFuse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSaveDataTab1CO.spinnerFusePosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void storeDataInBundle() {
        mBundle.putSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY, mSaveDataTab1CO);
    }


}