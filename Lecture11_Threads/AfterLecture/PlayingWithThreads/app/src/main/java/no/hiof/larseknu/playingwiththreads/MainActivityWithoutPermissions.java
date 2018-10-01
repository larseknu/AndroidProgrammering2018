package no.hiof.larseknu.playingwiththreads;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityWithoutPermissions extends AppCompatActivity {
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);

        //StrictMode.enableDefaults();
    }

    public void doWorkThreadless(View view) {
        Worker worker = new Worker(this);
        statusText.setText("Starting");

        //JSONObject jsonObject = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=33f85a4c");
        statusText.setText("Retrieved JSON");

        Location foundLocation = worker.getLocation();
        statusText.setText("Retrieved Location");

        String address = worker.reverseGeocode(foundLocation);
        statusText.setText("Retrieved Address");

        /*try {
            worker.saveToFile(foundLocation, address, jsonObject.getString("Title"), "NonThreadFile.txt");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        worker.saveToFile(foundLocation, address, /*jsonObject.getString("Title")*/ "Movietitle", "NonThreadFile.txt");

        statusText.setText("Done");
    }

    public void doWorkInThread(View view) {
        Thread workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Worker worker = new Worker(MainActivityWithoutPermissions.this);
                updateUI("Starting");

                JSONObject jsonObject = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=33f85a4c");
                updateUI("Retrieved JSON");

                Location foundLocation = worker.getLocation();
                updateUI("Retrieved Location");

                String address = worker.reverseGeocode(foundLocation);
                updateUI("Retrieved Address");

                try {
                    worker.saveToFile(foundLocation, address, jsonObject.getString("Title"), "NonThreadFile.txt");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                updateUI("Done");
            }
        });

        workerThread.start();
    }

    public void doWorkInAsyncTask(View view) {
        AsyncTaskWorker asyncTaskWorker = new AsyncTaskWorker();
        asyncTaskWorker.execute();
    }

    public void updateUI(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusText.setText(message);
            }
        });
    }




    public class AsyncTaskWorker extends AsyncTask<Void, String, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Worker worker = new Worker(MainActivityWithoutPermissions.this);
            publishProgress("Starting");

            JSONObject jsonObject = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=33f85a4c");
            publishProgress("Retrieved JSON");

            Location foundLocation = worker.getLocation();
            publishProgress("Retrieved Location");

            String address = worker.reverseGeocode(foundLocation);
            publishProgress("Retrieved Address");

            try {
                worker.saveToFile(foundLocation, address, jsonObject.getString("Title"), "NonThreadFile.txt");
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            publishProgress("Done");

            return true;
        }

        @Override
        protected void onProgressUpdate(String... messages) {
            statusText.setText(messages[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                statusText.setText("Done");
            else
                statusText.setText("Something failed =(");
        }
    }
}
