package com.example.arteme.myapplication.tabs.ShootCond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.weather.Channel;
import com.example.arteme.myapplication.weather.Interfaces.IWeatherReceiver;
import com.example.arteme.myapplication.weather.WeatherGetter;
import com.example.arteme.myapplication.weather.data.Item;

public class TabFragmentShootCond1 extends Fragment {

    private static final int LAYOUT = R.layout.tab1_shootcond;
    private View view;
    private Button btnSССompose, btnSСDownload, btnSСFill, btnSCBack;
    private LinearLayout eathContitionsLayout, meteoSrLayout;
    private EditText editPress, editTemper, editDirection, editWindSpeed, editHeightMeteo;
    private EditText edtMeteoT02, edtMeteoT04, edtMeteoT08, edtMeteoT12, edtMeteoT16, edtMeteoT20, edtMeteoT24, edtMeteoT30, edtMeteoT40;
    private EditText edtMeteoAw02, edtMeteoAw04, edtMeteoAw08, edtMeteoAw12, edtMeteoAw16, edtMeteoAw20, edtMeteoAw24, edtMeteoAw30, edtMeteoAw40;

    private IWeatherReceiver mIWeatherReceiver = new IWeatherReceiver() {
        @Override
        public void onReceiveWeather(Channel channel) {
            Item item = channel.getItem();
            editPress.setText(Integer.toString(item.getAtmosphere()));
            editDirection.setText(Integer.toString(item.getWindDirection()));
            editHeightMeteo.setText("0");
            editTemper.setText(Integer.toString(item.getTemperature()));
            editWindSpeed.setText(Double.toString(item.getWindSpeed()));
        }
    };

    public static TabFragmentShootCond1 getInstance(){
        Bundle args = new Bundle();
        TabFragmentShootCond1 fragment = new TabFragmentShootCond1();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState){
        view = inflater.inflate(LAYOUT, container, false);
        eathContitionsLayout = (LinearLayout)view.findViewById(R.id.linearEathConditions);
        meteoSrLayout = (LinearLayout)view.findViewById(R.id.linearMeteoSr);
        initSpinner();
        initButtons();
        initEditors();
        eathContitionsLayout.setVisibility(LinearLayout.VISIBLE);
        meteoSrLayout.setVisibility(LinearLayout.GONE);

        return view;
    }

    private void initSpinner(){

        Spinner spinnerWindSpeed;
        spinnerWindSpeed = (Spinner) view.findViewById(R.id.windSpeedSpinner);

        ArrayAdapter<CharSequence> adapterWindSpeed = ArrayAdapter.createFromResource(getContext(),
                R.array.wind_speed_array, android.R.layout.simple_spinner_item);

        spinnerWindSpeed.setAdapter(adapterWindSpeed);

    }

    private void initButtons() {
        btnSССompose = (Button) view.findViewById(R.id.btnSССompose);
        btnSССompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eathContitionsLayout.setVisibility(LinearLayout.GONE);
                meteoSrLayout.setVisibility(LinearLayout.VISIBLE);

                //<----------------------Tv--------------------------->

                EditText[] arrEditMeteoT = new EditText[9];
                edtMeteoT02 = (EditText) view.findViewById(R.id.edtMeteoT02);
                arrEditMeteoT[0] = edtMeteoT02;
                edtMeteoT04 = (EditText) view.findViewById(R.id.edtMeteoT04);
                arrEditMeteoT[1] = edtMeteoT04;
                edtMeteoT08 = (EditText) view.findViewById(R.id.edtMeteoT08);
                arrEditMeteoT[2] = edtMeteoT08;
                edtMeteoT12 = (EditText) view.findViewById(R.id.edtMeteoT12);
                arrEditMeteoT[3] = edtMeteoT12;
                edtMeteoT16 = (EditText) view.findViewById(R.id.edtMeteoT16);
                arrEditMeteoT[4] = edtMeteoT16;
                edtMeteoT20 = (EditText) view.findViewById(R.id.edtMeteoT20);
                arrEditMeteoT[5] = edtMeteoT20;
                edtMeteoT24 = (EditText) view.findViewById(R.id.edtMeteoT24);
                arrEditMeteoT[6] = edtMeteoT24;
                edtMeteoT30 = (EditText) view.findViewById(R.id.edtMeteoT30);
                arrEditMeteoT[7] = edtMeteoT30;
                edtMeteoT40 = (EditText) view.findViewById(R.id.edtMeteoT40);
                arrEditMeteoT[8] = edtMeteoT40;

                for(int i = 0; i < arrEditMeteoT.length; i++)
                    arrEditMeteoT[i].setText(strDelTv(delTv(Integer.valueOf(editTemper.getText().toString()), i)));

                //<------------------------------------------------------------>

                //<------------------------Aw----------------------------------->

                EditText[] arrEditMeteoAw = new EditText[9];
                edtMeteoAw02 = (EditText) view.findViewById(R.id.edtMeteoAw02);
                arrEditMeteoAw[0] = edtMeteoAw02;
                edtMeteoAw04 = (EditText) view.findViewById(R.id.edtMeteoAw04);
                arrEditMeteoAw[1] = edtMeteoAw04;
                edtMeteoAw08 = (EditText) view.findViewById(R.id.edtMeteoAw08);
                arrEditMeteoAw[2] = edtMeteoAw08;
                edtMeteoAw12 = (EditText) view.findViewById(R.id.edtMeteoAw12);
                arrEditMeteoAw[3] = edtMeteoAw12;
                edtMeteoAw16 = (EditText) view.findViewById(R.id.edtMeteoAw16);
                arrEditMeteoAw[4] = edtMeteoAw16;
                edtMeteoAw20 = (EditText) view.findViewById(R.id.edtMeteoAw20);
                arrEditMeteoAw[5] = edtMeteoAw20;
                edtMeteoAw24 = (EditText) view.findViewById(R.id.edtMeteoAw24);
                arrEditMeteoAw[6] = edtMeteoAw24;
                edtMeteoAw30 = (EditText) view.findViewById(R.id.edtMeteoAw30);
                arrEditMeteoAw[7] = edtMeteoAw30;
                edtMeteoAw40 = (EditText) view.findViewById(R.id.edtMeteoAw40);
                arrEditMeteoAw[8] = edtMeteoAw40;

                for(int i = 0; i < arrEditMeteoAw.length; i++)
                    arrEditMeteoAw[i].setText(strDelTv(delAw(Double.parseDouble(editWindSpeed.getText().toString()), i)));

                //<------------------------------------------------------------>
            }
        });

        btnSСDownload = (Button) view.findViewById(R.id.btnSСDownload);
        btnSСDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WeatherGetter(getContext(), mIWeatherReceiver).uploadWeather();
            }
        });

        btnSCBack = (Button) view.findViewById(R.id.btnBackMeteo);
        btnSCBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                eathContitionsLayout.setVisibility(LinearLayout.VISIBLE);
                meteoSrLayout.setVisibility(LinearLayout.GONE);
            }
        });
        //btnSСFill = (Button) view.findViewById(R.id.btnSСFill);
    }

    private void initEditors() {
        editPress = (EditText) view.findViewById(R.id.editPress);
        editTemper = (EditText) view.findViewById(R.id.editTemper);
        editDirection = (EditText) view.findViewById(R.id.editDirection);
        editWindSpeed = (EditText) view.findViewById(R.id.editWindSpeed);
        editHeightMeteo = (EditText) view.findViewById(R.id.editHeightMeteo);
    }

    private double delTv(double realT, int hYbull){

        double arrDelTv[] = {0.5, 1, 1.5, 2, 3.5, 4.5};
        double retDelTv = 0;

        if(realT < 0)
            retDelTv = realT - 15.9;
        else if(realT >= 0 & realT <= 5)
            retDelTv = (realT + arrDelTv[0]) - 15.9;
        else if(realT > 5 & realT < 10)
            retDelTv = (realT + arrDelTv[0] + 0.1*(realT - 5)) - 15.9;
        else if(realT >= 10 & realT <= 15)
            retDelTv = (realT + arrDelTv[1]) - 15.9;
        else if(realT > 15 & realT < 20)
            retDelTv = (realT + arrDelTv[1] + 0.1*(realT - 15)) - 15.9;
        else if(realT >= 20 & realT <= 25)
            retDelTv = (realT + arrDelTv[2] + 0.1*(realT - 20)) - 15.9;
        else if(realT > 25 & realT <= 30)
            retDelTv = (realT + arrDelTv[3] + 0.3*(realT - 25)) - 15.9;
        else if(realT > 30 & realT <= 40)
            retDelTv = (realT + arrDelTv[4] + 0.1*(realT - 30)) - 15.9;
        else
            retDelTv = (realT + arrDelTv[5]) - 15.9;

         retDelTv = Math.round(retDelTv);

         if(retDelTv < 0) {
             switch (hYbull) {
                 case 0:
                     int temH02[] = {-1, -2, -3, -4, -5, -6, -7, -8, -8, -9, -20, -29, -39, -49};// 10(-20) 11(-30) 12(-40) 13(-50)
                     retDelTv = minusTv(temH02, retDelTv);
                     break;
                 case 1:
                     int temH04[] = {-1, -2, -3, -4, -5, -6, -7, -7, -8, -9, -19, -29, -38, -48};
                     retDelTv = minusTv(temH04, retDelTv);
                     break;
                 case 2:
                     int temH08[] = {-1, -2, -3, -4, -5, -6, -6, -7, -7, -8, -18, -28, -37, -46};
                     retDelTv = minusTv(temH08, retDelTv);
                     break;
                 case 3:
                     int temH12[] = {-1, -2, -3, -4, -4, -5, -5, -6, -7, -8, -17, -26, -35, -44};
                     retDelTv = minusTv(temH12, retDelTv);
                     break;
                 case 4:
                     int temH16[] = {-1, -2, -3, -3, -4, -4, -5, -6, -7, -7, -17, -25, -34, -42};
                     retDelTv = minusTv(temH16, retDelTv);
                     break;
                 case 5:
                     int temH20[] = {-1, -2, -3, -3, -4, -4, -5, -6, -6, -7, -16, -24, -32, -40};
                     retDelTv = minusTv(temH20, retDelTv);
                     break;
                 case 6:
                     int temH24[] = {-1, -2, -3, -3, -4, -4, -5, -5, -6, -7, -15, -23, -31, -38};
                     retDelTv = minusTv(temH24, retDelTv);
                     break;
                 case 7:
                     int temH30[] = {-1, -2, -3, -3, -4, -4, -4, -5, -5, -6, -15, -22, -30, -37};
                     retDelTv = minusTv(temH30, retDelTv);
                     break;
                 case 8:
                     int temH40[] = {-1, -2, -3, -3, -4, -4, -4, -4, -5, -6, -14, -20, -27, -34};
                     retDelTv = minusTv(temH40, retDelTv);
                     break;
             }
         }

         return retDelTv;
     }

    private double delAw(double realAw, int hYbull)
    {
        int arrDelAw[] = {1, 2, 3, 3, 4, 4, 4, 5, 5};

        return (double)((int)realAw + arrDelAw[hYbull]);
    }

    private String strDelTv(double delTv){
        String retStrDelTv = "";

        if(delTv < 10 & delTv >= 0)
            retStrDelTv ="0" + String.valueOf((int)delTv);
        else
            retStrDelTv = String.valueOf((int)delTv*(-1) + 50);

        return retStrDelTv;
    }

    private double minusTv(int minusTem[], double delTv )
    {
        if((int)delTv < -10)
        {
            int ostDel = (int)delTv%10;
            int celDel = ((int)delTv - ostDel)/10;
            if(ostDel == 0)
                delTv = minusTem[9 + celDel*(-1) - 1];
            else
                delTv = minusTem[9 + celDel*(-1) - 1] + minusTem[ostDel*(-1) - 1];
        }
        else
            delTv = (double)minusTem[(int)delTv*(-1) - 1];

        return delTv;
    }
}
