package com.example.arteme.myapplication.tabs.ShootCond;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.arteme.myapplication.ActivityShootCond;
import com.example.arteme.myapplication.ISavedData;
import com.example.arteme.myapplication.MainActivity;
import com.example.arteme.myapplication.R;
import com.example.arteme.myapplication.ToastUtil;
import com.example.arteme.myapplication.tabs.SavedObject.SaveDataTab1Meteo;
import com.example.arteme.myapplication.weather.Channel;
import com.example.arteme.myapplication.weather.Interfaces.IWeatherReceiver;
import com.example.arteme.myapplication.weather.WeatherGetter;
import com.example.arteme.myapplication.weather.data.Item;

public class TabFragmentShootCond1 extends Fragment implements ISavedData {

    public static final int LAYOUT = R.layout.tab1_shootcond;
    private View view;
    ArrayAdapter<CharSequence> adapterWindSpeed;
    Spinner spinnerWindSpeed;
    int posWindSpeed;
    private Button btnSССompose, btnSСDownload, btnSСFill, btnSCBack;
    private LinearLayout eathContitionsLayout, meteoSrLayout;
    private EditText editPress, editTemper, editDirection, editWindSpeed, editHeightMeteo;
    private EditText edtMeteoT02, edtMeteoT04, edtMeteoT08, edtMeteoT12, edtMeteoT16, edtMeteoT20, edtMeteoT24, edtMeteoT30, edtMeteoT40;
    private EditText edtMeteoAw02, edtMeteoAw04, edtMeteoAw08, edtMeteoAw12, edtMeteoAw16, edtMeteoAw20, edtMeteoAw24, edtMeteoAw30, edtMeteoAw40;
    private EditText edtMeteoW02, edtMeteoW04, edtMeteoW08, edtMeteoW12, edtMeteoW16, edtMeteoW20, edtMeteoW24, edtMeteoW30, edtMeteoW40;
    private Bundle mBundle;

    @Override
    public void onResume() {
        super.onResume();
        if(mBundle != null) {
            reStoreData(mBundle);
        }
    }

    @Override
    public void reStoreData(Bundle bundle) {
        SaveDataTab1Meteo saveDataTab1Meteo = (SaveDataTab1Meteo) bundle.getSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY);
        if (saveDataTab1Meteo == null) return;
        editPress.setText(saveDataTab1Meteo.press);
        editDirection.setText(saveDataTab1Meteo.windD);
        editHeightMeteo.setText(saveDataTab1Meteo.heightMeteo);
        editWindSpeed.setText(saveDataTab1Meteo.windS);
        editTemper.setText(saveDataTab1Meteo.temp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        storeDataInBundle();
        ((ActivityShootCond) getActivity()).saveBundle(LAYOUT, mBundle);
    }

    @Override
    public void storeDataInBundle() {
        SaveDataTab1Meteo saveDataTab1Meteo =
                new SaveDataTab1Meteo(
                        editPress.getText().toString(),
                        editTemper.getText().toString(),
                        editDirection.getText().toString(),
                        editWindSpeed.getText().toString(),
                        editHeightMeteo.getText().toString());
        mBundle = new Bundle();
        mBundle.putSerializable(MainActivity.BUNDLE_SAVED_DATA_KEY, saveDataTab1Meteo);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

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
        if (getArguments() != null) {
            reStoreData(getArguments());
        }
        return view;
    }

    private void initSpinner(){

        spinnerWindSpeed = (Spinner) view.findViewById(R.id.windSpeedSpinner);

        adapterWindSpeed = ArrayAdapter.createFromResource(getContext(),
                R.array.wind_speed_array, android.R.layout.simple_spinner_item);

        spinnerWindSpeed.setAdapter(adapterWindSpeed);

        spinnerWindSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                posWindSpeed = selectedItemPosition;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                    arrEditMeteoAw[i].setText(strDelTv(delAw(Double.parseDouble(editDirection.getText().toString()), i)));

                //<------------------------------------------------------------>

                //<------------------------W----------------------------------->

                EditText[] arrEditMeteoW = new EditText[9];
                edtMeteoW02 = (EditText) view.findViewById(R.id.edtMeteoW02);
                arrEditMeteoW[0] = edtMeteoW02;
                edtMeteoW04 = (EditText) view.findViewById(R.id.edtMeteoW04);
                arrEditMeteoW[1] = edtMeteoW04;
                edtMeteoW08 = (EditText) view.findViewById(R.id.edtMeteoW08);
                arrEditMeteoW[2] = edtMeteoW08;
                edtMeteoW12 = (EditText) view.findViewById(R.id.edtMeteoW12);
                arrEditMeteoW[3] = edtMeteoW12;
                edtMeteoW16 = (EditText) view.findViewById(R.id.edtMeteoW16);
                arrEditMeteoW[4] = edtMeteoW16;
                edtMeteoW20 = (EditText) view.findViewById(R.id.edtMeteoW20);
                arrEditMeteoW[5] = edtMeteoW20;
                edtMeteoW24 = (EditText) view.findViewById(R.id.edtMeteoW24);
                arrEditMeteoW[6] = edtMeteoW24;
                edtMeteoW30 = (EditText) view.findViewById(R.id.edtMeteoW30);
                arrEditMeteoW[7] = edtMeteoW30;
                edtMeteoW40 = (EditText) view.findViewById(R.id.edtMeteoW40);
                arrEditMeteoW[8] = edtMeteoW40;

                for(int i = 0; i < arrEditMeteoW.length; i++)
                    arrEditMeteoW[i].setText(strDelTv(delW(Double.parseDouble(editWindSpeed.getText().toString()), i)));

                //<------------------------------------------------------------>
            }
        });

        btnSСDownload = (Button) view.findViewById(R.id.btnSСDownload);
        btnSСDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mToastUtil.showErrorToast(getString(R.string.meteo_connection_error));
                if(!isConnectivityEnabled()) {
                    ToastUtil.showErrorToast(getActivity(), getString(R.string.meteo_connection_error));
                    return;
                }
                else if (!isGPSEnabled()) {
                    ToastUtil.showErrorToast(getActivity(), getString(R.string.meteo_gps_error));
                    return;
                }
                String s = editDirection.getText().toString();
                if(!s.isEmpty()) {
                    System.out.println(s);
                }
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

    private double delAw(double realAw, int hYbull) {

        if(posWindSpeed == 1) {
            int arrDelAwv[] = {0, 1, 2, 2, 3, 3, 3, 4, 4};
            return (double)(int)realAw + arrDelAwv[hYbull];
        }

        int arrDelAw[] = {1, 2, 3, 3, 4, 4, 4, 5, 5};

        return (double)((int)realAw + arrDelAw[hYbull]);
    }

    private double delW(double realW, int hYbull){

        double retDelW = 0;

        if(posWindSpeed == 0)
            retDelW = dalW(realW, hYbull);
        else
            retDelW = dalDg(realW, hYbull);

        return retDelW;
    }

    private String strDelTv(double delTv){
        String retStrDelTv = "";

        if(delTv < 10 & delTv >= 0)
            retStrDelTv ="0" + String.valueOf((int)delTv);
        else if (delTv < 0)
            retStrDelTv = String.valueOf((int)delTv*(-1) + 50);
        else
            retStrDelTv = String.valueOf((int)delTv);

        return retStrDelTv;
    }

    private double minusTv(int minusTem[], double delTv ) {
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

    private int dalDg(double realDg, int hYbull){
        //40,50,60,70,80,90,100,110,120,130,140,150
        int arrDg[][] = {{3, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 12},
                {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
                {4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16},
                {4, 5, 7, 8, 8, 9, 11, 12, 13, 15, 15, 16},
                {4, 6, 7, 8, 9, 10, 11, 3, 14, 15, 17, 17},
                {4, 6, 7, 8, 9, 10, 11, 13, 14, 16, 17, 18},
                {4, 6, 8, 9, 9, 10, 12, 14, 15, 16, 18, 19},
                {5, 6, 8, 9, 10, 11, 12, 14, 15, 17, 18, 19},
                {5, 6, 8, 9, 10, 11, 12, 14, 16, 18, 19, 20}};

        int retDg = 0;

        if(realDg <= 40)
            retDg = arrDg[hYbull][0];
        else if(realDg > 40 & realDg <= 50)
            retDg = arrDg[hYbull][1];
        else if(realDg > 50 & realDg <= 60)
            retDg = arrDg[hYbull][2];
        else if(realDg > 60 & realDg <= 70)
            retDg = arrDg[hYbull][3];
        else if(realDg > 70 & realDg <= 80)
            retDg = arrDg[hYbull][4];
        else if(realDg > 80 & realDg <= 90)
            retDg = arrDg[hYbull][5];
        else if(realDg > 90 & realDg <= 100)
            retDg = arrDg[hYbull][6];
        else if(realDg > 100 & realDg <= 110)
            retDg =  arrDg[hYbull][7];
        else if(realDg > 110 & realDg <= 120)
            retDg = arrDg[hYbull][8];
        else if(realDg > 120 & realDg <= 130)
            retDg = arrDg[hYbull][9];
        else if(realDg > 130 & realDg <= 140)
            retDg = arrDg[hYbull][10];
        else if(realDg > 140)
            retDg =  arrDg[hYbull][11];

        return retDg;
    }

    private double dalW(double realW, int hYbull){
        //3,4, 5, 6, 7,  8,  9,  10, 11, 12,  13, 14, 15
        int arrW[][] = {{4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22},
                {5, 7, 10, 11, 12, 14, 17, 18, 20, 22, 23, 25, 27},
                {5, 8, 10, 11, 13, 15, 18, 19, 21, 23, 25, 27, 28},
                {5, 8, 11, 12, 13, 16, 19, 20, 22, 24, 26, 28, 30},
                {6, 8, 11, 13, 14, 17, 20, 21, 23, 25, 27, 29, 32},
                {6, 9, 11, 13, 14, 17, 20, 21, 24, 26, 28, 30, 30},
                {6, 9, 12, 14, 15, 18, 21, 22, 25, 27, 29, 32, 34},
                {6, 9, 12, 14, 15, 18, 21, 23, 25, 28, 30, 32, 36},
                {6, 10, 12, 14, 16, 19, 22, 24, 26, 29, 32, 34, 36}};

        double retW = 0;

        if(realW <= 3)
            retW = arrW[hYbull][0];
        else if(realW > 15)
            retW = arrW[hYbull][12];
        else
            retW = arrW[hYbull][(int)realW - 3];

        return retW;
    }

    private boolean isConnectivityEnabled() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    private boolean isGPSEnabled() {
        final LocationManager manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled( LocationManager.GPS_PROVIDER);
    }

}
