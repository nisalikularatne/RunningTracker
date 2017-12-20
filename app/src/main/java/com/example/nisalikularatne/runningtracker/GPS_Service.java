package com.example.nisalikularatne.runningtracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by filipp on 6/16/2016.
 */
public class GPS_Service extends Service {

    private LocationListener locationListener;
    private LocationManager locationManager;
    private static String Tag="GPS_Service";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(Tag,"Service Created");
        getCurrentLocation();

    }
    public void getCurrentLocation() {

        locationManager =
                (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener =  new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                i.putExtra("coordinates", location.getLongitude() + " " + location.getLatitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d(Tag, "onProviderEnabled: " + s);
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d(Tag, "onProviderDisabled: " + s);
            }
        };
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5, // minimum time interval between updates
                    5, // minimum distance between updates, in metres
                    locationListener);
            Log.d(Tag,"Successful");
        } catch (SecurityException e) {
            Log.d(Tag, e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(locationListener);
        }
        Log.d(Tag,"Destroyed Service");
    }
}
