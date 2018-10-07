package no.hiof.larseknu.playingwithservices.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import no.hiof.larseknu.playingwithservices.R;
import no.hiof.larseknu.playingwithservices.Worker;

public class MyStartedService extends Service {
    private static final String LOGTAG = "MyStartedService";

    private Worker worker;

    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void onCreate() {
        Log.i(LOGTAG, "MyStartedService.onCreate Thread: " + Thread.currentThread().getName());

        worker = new Worker(this);
        worker.monitorGpsInBackground();

        executorService = Executors.newFixedThreadPool(3);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOGTAG, "MyStartedService.onStartCommand Thread: " + Thread.currentThread().getName());

        ServiceRunnable runnable = new ServiceRunnable(startId);

        executorService.execute(runnable);

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(LOGTAG, "MyStartedService.onDestroy Thread: " + Thread.currentThread().getName());
        worker.stopGpsMonitoring();
    }

    private class ServiceRunnable implements Runnable {
        private int startId;

        public ServiceRunnable(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            Log.i(LOGTAG, "MyStartedService.ServiceRunnable Thread: " + Thread.currentThread().getName());
            try {
                Location location = worker.getLocation();

                String address = worker.reverseGeocode(location);

                JSONObject jsonObject = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=2f6990a0");

                worker.saveToFile(location, address, jsonObject.getString("Title"), "MyStartedService.txt");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            DelayedStopRequest delayedStopRequest = new DelayedStopRequest(startId);

            MyStartedService.this.scheduledExecutorService.schedule(delayedStopRequest, 10, TimeUnit.SECONDS);
        }
    }

    private class DelayedStopRequest implements Runnable {
        private int startId;

        public DelayedStopRequest(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            Log.i(LOGTAG, "MyStartedService.DelayedStopRequest Thread: " + Thread.currentThread().getName());

            boolean stopping = MyStartedService.this.stopSelfResult(startId);

            Log.i(LOGTAG, "Service with startid: " + startId + " stopping: " + stopping);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
