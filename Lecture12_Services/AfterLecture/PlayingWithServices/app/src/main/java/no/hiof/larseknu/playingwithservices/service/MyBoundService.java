package no.hiof.larseknu.playingwithservices.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;

import no.hiof.larseknu.playingwithservices.Worker;

public class MyBoundService extends Service {
    private MyLocalBinder myLocalBinder = new MyLocalBinder();

    private Location currentLocation;
    private Worker worker;

    @Override
    public void onCreate() {
        super.onCreate();
        worker = new Worker(this);
        worker.monitorGpsInBackground(new MyLocationListener());
        currentLocation = createLocationManually();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        worker.stopGpsMonitoring();
    }

    public class MyLocalBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myLocalBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            currentLocation = location;
        }

        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        public void onProviderEnabled(String s) {
        }

        public void onProviderDisabled(String s) {
        }
    }

    private Location createLocationManually() {
        Location lastLocation = new Location("Hiof");
        Date now = new Date();
        lastLocation.setTime(now.getTime());
        lastLocation.setLatitude(59.128229);
        lastLocation.setLongitude(11.352860);

        return lastLocation;
    }
}
