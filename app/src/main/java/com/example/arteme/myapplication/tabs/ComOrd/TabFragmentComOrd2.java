package com.example.arteme.myapplication.tabs.ComOrd;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.arteme.myapplication.ActivityComOrd;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.MainActivity;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.ToastUtil;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab2CO;

public class TabFragmentComOrd2 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab2_comord;
    private View view;
    private EditText edtXknp, edtYknp, edtHknp, edtXop, edtYop, edtHop;
    private SaveDataTab2CO mSaveDataTab2CO;
    private Bundle mBundle;
    private Button btnCOSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);
        initButtons();
        if (getArguments() != null) reStoreData(mBundle = getArguments());
        else mBundle = new Bundle();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBundle != null) {
            reStoreData(mBundle);
        }
    }

    @Override
    public void reStoreData(Bundle bundle) {
        mSaveDataTab2CO = (SaveDataTab2CO) mBundle.getSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY);
        if (mSaveDataTab2CO == null) {
            mSaveDataTab2CO = new SaveDataTab2CO("","","","","","");
            return;
        }
        edtXknp.setText(mSaveDataTab2CO.Xknp);
        edtYknp.setText(mSaveDataTab2CO.Yknp);
        edtHknp.setText(mSaveDataTab2CO.Hknp);
        edtXop.setText(mSaveDataTab2CO.Xop);
        edtYop.setText(mSaveDataTab2CO.Yop);
        edtHop.setText(mSaveDataTab2CO.Hop);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeDataInBundle();
    }

    @Override
    public void storeDataInBundle() {
        mBundle.putSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY, mSaveDataTab2CO);
        ((ActivityComOrd)getActivity()).saveBundle(LAYOUT,mBundle);
    }

    private void initButtons() {
        btnCOSave = (Button) view.findViewById(R.id.btnCOSaveXY);
        btnCOSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveDataTab2CO =
                        new SaveDataTab2CO(
                                edtXknp.getText().toString(),
                                edtYknp.getText().toString(),
                                edtHknp.getText().toString(),
                                edtXop.getText().toString(),
                                edtYop.getText().toString(),
                                edtHop.getText().toString());
                storeDataInBundle();
                hideKeyBoard();
                ToastUtil.showSuccessToast(getActivity(), getString(R.string.succ_save_data));
            }
        });

        edtXknp = (EditText) view.findViewById(R.id.edtXknp);
        edtYknp = (EditText) view.findViewById(R.id.edtYknp);
        edtHknp = (EditText) view.findViewById(R.id.edtHknp);
        edtXop = (EditText) view.findViewById(R.id.edtXop);
        edtYop = (EditText) view.findViewById(R.id.edtYop);
        edtHop = (EditText) view.findViewById(R.id.edtHop);
    }

    private void hideKeyBoard() {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    (getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow((getActivity()).getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e ) {
            e.printStackTrace();
        }
    }

}

