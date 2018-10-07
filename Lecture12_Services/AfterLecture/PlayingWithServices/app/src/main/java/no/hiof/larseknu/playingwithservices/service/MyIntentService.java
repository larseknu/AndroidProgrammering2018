package no.hiof.larseknu.playingwithservices.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import no.hiof.larseknu.playingwithservices.Worker;


public class MyIntentService extends IntentService {
    private static final String LOGTAG = MyIntentService.class.getSimpleName();

    private static final String ACTION_RETREIVE_AND_SAVE_ADDRESS = "no.hiof.larseknu.playingwithservices.service.action.ADDRESS";
    private static final String EXTRA_FILENAME = "no.hiof.larseknu.playingwithservices.service.extra.FILENAME";
    private static final String EXTRA_RESULT_RECEIVER = "no.hiof.larseknu.playingwithservices.service.extra.RESULT_RECEIVER";

    public static final int RESULT_CODE = 1;
    public static final String RESULT_DATA_KEY = "no.hiof.larseknu.playingwithservices.intentservice.RESULT_DATA";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionRetreiveAndSaveAddress(Context context, String fileName, ResultReceiver resultReceiver) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_RETREIVE_AND_SAVE_ADDRESS);
        intent.putExtra(EXTRA_FILENAME, fileName);
        intent.putExtra(EXTRA_RESULT_RECEIVER, resultReceiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(LOGTAG, "Intent received + Thread:" + Thread.currentThread().getName());

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RETREIVE_AND_SAVE_ADDRESS.equals(action)) {
                final String fileName = intent.getStringExtra(EXTRA_FILENAME);
                final ResultReceiver resultReceiver = intent.getParcelableExtra(EXTRA_RESULT_RECEIVER);
                handleActionSaveAddress(fileName, resultReceiver);
            }
        }
    }

    private void handleActionSaveAddress(String fileName, ResultReceiver resultReceiver) {
        try {
            Worker worker = new Worker(getApplicationContext());
            Log.d(LOGTAG, "Worker Started");

            Location location = worker.getLocation();
            Log.d(LOGTAG, "Got location");

            String address = worker.reverseGeocode(location);
            Log.d(LOGTAG, "Got address");

            JSONObject json = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=2f6990a0");
            Log.d(LOGTAG, "Got JSON");

            worker.saveToFile(location, address, json.getString("Title"), fileName);
            Log.d(LOGTAG, "Saved file");

            Log.d(LOGTAG, "MyIntentService Done");

            Bundle bundle = new Bundle();
            bundle.putString(RESULT_DATA_KEY, "IntentService done");
            resultReceiver.send(RESULT_CODE, bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
