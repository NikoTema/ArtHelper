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

    public static final int SYSTEM_SPINNER = 0;
    public static final int PACKET_SPINNER = 1;
    public static final int CHARGE_SPINNER = 2;
    public static final int FUSE_SPINNER = 3;

    public final static String SYSTEM_ARRAY = "system_array";
    public final static int FIRST_POSITION_ON_ADAPTER = 0;


    private Spinner spinnerSystem, spinnerPacket, spinnerCharge, spinnerFuse;
    private View view;
    private SaveDataTab1CO mSaveDataTab1CO;
    private Bundle mBundle;
    private AdapterFactory mAdapterFactory;
    private ComOrdSpinnerData mComOrdSpinnerData;

    private ArrayAdapter<CharSequence> mAdapterSystem;
    private ArrayAdapter<CharSequence> mAdapterPacket;
    private ArrayAdapter<CharSequence> mAdapterCharge;
    private ArrayAdapter<CharSequence> mAdapterFuse;


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

        mComOrdSpinnerData = new ComOrdSpinnerData(getContext());

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

        mAdapterFactory = new AdapterFactory(getContext(), android.R.layout.simple_spinner_item);

        initAdapters();

        spinnerSystem.setAdapter(mAdapterSystem);
        spinnerSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSpinner(SYSTEM_SPINNER, parent.getSelectedItem().toString());
                mSaveDataTab1CO.spinnerSystemPosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPacket.setAdapter(mAdapterPacket);
        spinnerPacket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSpinner(PACKET_SPINNER, parent.getSelectedItem().toString());

                mSaveDataTab1CO.spinnerPacketPosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCharge.setAdapter(mAdapterCharge);
        spinnerCharge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSpinner(CHARGE_SPINNER, parent.getSelectedItem().toString());

                mSaveDataTab1CO.spinnerChargePosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFuse.setAdapter(mAdapterFuse);
        spinnerFuse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSpinner(FUSE_SPINNER, parent.getSelectedItem().toString());
                mSaveDataTab1CO.spinnerFusePosition = position;
                storeDataInBundle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initAdapters() {
        mComOrdSpinnerData.initCurrentSpinnerNames(SYSTEM_ARRAY);

        mAdapterSystem = mAdapterFactory.getArrayAdapter(mComOrdSpinnerData.getCurrentAdapterSystemName());
        mAdapterPacket = mAdapterFactory.getArrayAdapter(mComOrdSpinnerData.getCurrentAdapterPacketName());
        mAdapterCharge = mAdapterFactory.getArrayAdapter(mComOrdSpinnerData.getCurrentAdapterChargeName());
        mAdapterFuse = mAdapterFactory.getArrayAdapter(mComOrdSpinnerData.getCurrentAdapterFuseName());

    }

    private void updateSpinner(int parentSpinner, String item) {
        String key = null;
        switch (parentSpinner) {
            case SYSTEM_SPINNER:
                key = mComOrdSpinnerData.getAssociativeKey(SYSTEM_SPINNER, item);

                if (key != null) {
                    mAdapterPacket = mAdapterFactory.getArrayAdapter(key);
                    spinnerPacket.setAdapter(mAdapterPacket);
                }
                break;
            case PACKET_SPINNER:
                key = mComOrdSpinnerData.getAssociativeKey(PACKET_SPINNER, item);

                if (key != null) {
                    mAdapterCharge = mAdapterFactory.getArrayAdapter(key);
                    spinnerCharge.setAdapter(mAdapterCharge);
                }
                break;
            case CHARGE_SPINNER:
                key = mComOrdSpinnerData.getAssociativeKey(CHARGE_SPINNER, item);

                if (key != null) {
                    mAdapterFuse = mAdapterFactory.getArrayAdapter(key);
                    spinnerFuse.setAdapter(mAdapterFuse);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void storeDataInBundle() {
        mBundle.putSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY, mSaveDataTab1CO);
    }



}