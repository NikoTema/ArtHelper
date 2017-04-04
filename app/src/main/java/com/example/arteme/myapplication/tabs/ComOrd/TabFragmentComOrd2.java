package com.example.arteme.myapplication.tabs.ComOrd;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.arteme.myapplication.R;

public class TabFragmentComOrd2 extends  Fragment {

    private static final int LAYOUT = R.layout.tab2_comord;
    private View view;
    EditText edtXknp, edtYknp, edtHknp, edtXop, edtYop, edtHop;

    private Button btnCOSave;

    public static TabFragmentComOrd2 getInstance(){
        Bundle args = new Bundle();
        TabFragmentComOrd2 fragment = new TabFragmentComOrd2();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);
        initButtons();
        return view;
    }

    private void initButtons() {
        btnCOSave = (Button) view.findViewById(R.id.btnCOSaveXY);
        btnCOSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtXknp = (EditText) view.findViewById(R.id.edtXknp);
                edtYknp = (EditText) view.findViewById(R.id.edtYknp);
                edtHknp = (EditText) view.findViewById(R.id.edtHknp);
                edtXop = (EditText) view.findViewById(R.id.edtXop);
                edtYop = (EditText) view.findViewById(R.id.edtYop);
                edtHop = (EditText) view.findViewById(R.id.edtHop);
            }
        });

    }

}
