package no.hiof.larseknu.playingwiththreads;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    private TextView statusText;
    private AsyncTaskWorker asyncTaskWorker;

    private static final int MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE = 1;
    private String[] neededPermissions = { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };


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
                Worker worker = new Worker(MainActivity.this);
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
        asyncTaskWorker = new AsyncTaskWorker();

        // Check if we have the necessary permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Have we requested permission before and been denied (as well as the user NOT selecting "never ask again")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setMessage("You need to enable access to your location and local storage to retrieve an address")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        neededPermissions,
                                        MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
            else {
                // If not, ask for them and handle the reply in the onPermissionResult callback
                ActivityCompat.requestPermissions(this,
                        neededPermissions,
                        MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE);
            }

        }
        else {
            asyncTaskWorker.execute();
        }
    }

    public void updateUI(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusText.setText(message);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    boolean granted = true;

                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED)
                            granted = false;
                    }

                    if (granted)
                        asyncTaskWorker.execute();
                    else {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.container), "You need to enable access to your location and local storage to retreive an address", Snackbar.LENGTH_LONG);
                        snackbar.setAction("Enable", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        });
                        snackbar.show();
                    }
                }
            }
        }
    }

    public class AsyncTaskWorker extends AsyncTask<Void, String, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Worker worker = new Worker(MainActivity.this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.easyPermissionActivity:
                startActivity(new Intent(this, EasyPermissionActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
