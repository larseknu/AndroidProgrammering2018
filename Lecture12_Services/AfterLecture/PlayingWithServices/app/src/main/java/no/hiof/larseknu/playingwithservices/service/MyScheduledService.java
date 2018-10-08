package no.hiof.larseknu.playingwithservices.service;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.location.Location;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import no.hiof.larseknu.playingwithservices.Worker;

/**
 * Created by larseknu on 09/10/2017.
 */

public class MyScheduledService extends JobService {
    private static String LOGTAG = "MyScheduledService";

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(LOGTAG, "MyScheduleService Started");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new MyRunnable());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Worker worker = new Worker(getApplicationContext());
                Log.d(LOGTAG, "Worker Started");

                Location location = worker.getLocation();
                Log.d(LOGTAG, "Got location");

                String address = worker.reverseGeocode(location);
                Log.d(LOGTAG, "Got address");

                JSONObject json = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=2f6990a0");
                Log.d(LOGTAG, "Got JSON");

                worker.saveToFile(location, address, json.getString("Title"), "ScheduleServiceJob.txt");
                Log.d(LOGTAG, "Saved file");

                Log.d(LOGTAG, "MyScheduledService Done");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
