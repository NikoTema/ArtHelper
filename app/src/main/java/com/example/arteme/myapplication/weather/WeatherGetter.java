package com.example.arteme.myapplication.weather;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.arteme.myapplication.weather.Interfaces.IWeatherReceiver;
import com.example.arteme.myapplication.weather.Interfaces.IYahooRespondReceiver;

public class WeatherGetter implements LocationListener, IYahooRespondReceiver {

    private Context mContext;
    private IWeatherReceiver mIWeatherReceiver;
    public static int GET_WEATHER_FROM_CURRENT_LOCATION = 0x00001;

    public WeatherGetter(Context context, IWeatherReceiver iWeatherReceiver) {
        mContext = context;
        mIWeatherReceiver = iWeatherReceiver;
    }

    public void uploadWeather() {
        // system's LocationManager
        final LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean error = true;
        Criteria locationCriteria = new Criteria();
        if (isNetworkEnabled) {
            locationCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
        } else if (isGPSEnabled) {
            locationCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        } else {
            error = false;
        }
        if (error) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            /*ActivityCompat.requestPermissions(mContext., new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
            }, GET_WEATHER_FROM_CURRENT_LOCATION);*/
                return;
            }
            locationManager.requestSingleUpdate(locationCriteria, this, null);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        new YahooWeatherAsyncTask(this).execute(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onReceiveSuccessRespond(Channel channel) {
        mIWeatherReceiver.onReceiveWeather(channel);
    }

    @Override
    public void onReceiveFailureRespond(Exception e) {
        //TODO handle exception
    }
}
