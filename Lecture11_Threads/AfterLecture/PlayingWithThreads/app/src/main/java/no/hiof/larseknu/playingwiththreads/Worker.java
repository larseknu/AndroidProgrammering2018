package no.hiof.larseknu.playingwiththreads;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Worker {
    private Context context;
    private static boolean useGpsToGetLocation = true;

    public Worker(Context context) {
        this.context = context;
    }

    /**
     * Uses the LocationManager to get the last known location
     * Since we use the LocationManager, we need permission to access the users location
     */
    public Location getLocation() {
        Location lastLocation = null;

        if (useGpsToGetLocation) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        if(lastLocation == null)
            lastLocation = createLocationManually();

        addDelay();
        return lastLocation;
    }

    /**
     * Gets one address from a specific location
     * If no address is found, HIØ location is returned
     */
    public String reverseGeocode(Location location) {
        String addressDescription = null;

        try  {
            Geocoder geocoder = new Geocoder(context);
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
            if(!addressList.isEmpty())
            {
                Address firstAddress = addressList.get(0);

                StringBuilder addressBuilder = new StringBuilder();
                for (int i = 0; i <= firstAddress.getMaxAddressLineIndex(); i++)
                {
                    if(i != 0)
                        addressBuilder.append(", ");
                    addressBuilder.append(firstAddress.getAddressLine(i));
                }
                addressDescription = addressBuilder.toString();
            }
            else {
                return "B.R.A. veien 4, 1757 Halden";
            }
        }
        catch (IOException ex) {
            Log.e("Worker.reverseGeocode", "IOException Error");
        }
        catch (Exception ex) {
            Log.e("Worker.reverseGeocode", "Error");
        }

        addDelay();
        return addressDescription;
    }

    /**
     * Appends the information given by the parameters to a file in the downloads directory
     *
     * @param location The location to save to the file
     * @param address The address to save to the file
     * @param movietitle The movietitle to save to the file
     * @param fileName The filename we're going to use to save to
     */
    public void saveToFile(Location location, String address, String movietitle, String fileName) {
        try {
            File targetDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if(!targetDir.exists())
                targetDir.mkdirs();

            File outFile = new File(targetDir, fileName);
            FileWriter fileWriter = new FileWriter(outFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String outLine = String.format(Locale.getDefault(), "%s - %f/%f\n",
                    new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(location.getTime()),
                    location.getLatitude(),
                    location.getLongitude());
            writer.write(outLine);
            writer.write(address + "\n");
            writer.write(movietitle + "\n\n");

            writer.flush();
            writer.close();
            fileWriter.close();
        }
        catch (Exception ex){
            Log.e("Worker.saveToFile", ex.getMessage());
        }

        addDelay();
    }

    /**
     * Gets JSON from a specific URL
     * @param urlString The URL that points to a JSON-object
     * @return The JSON object retreived from the URL
     */
    public JSONObject getJSONObjectFromURL(String urlString)  {

        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            urlConnection.disconnect();

            String jsonString = sb.toString();

            addDelay();

            return new JSONObject(jsonString);
        }
        catch (JSONException jsone) {
            Log.e("Worker.getJSON", jsone.getMessage());
        }
        catch (IOException ioe) {
            Log.e("Worker.getJSON", ioe.getMessage());
        }

        addDelay();
        return new JSONObject();

    }

    /**
     * Creates a location manually for HIØ to use for testing
     *
     * @return The location of HIØ
     */
    private Location createLocationManually() {
        Location lastLocation = new Location("Hiof");
        Date now = new Date();
        lastLocation.setTime(now.getTime());
        lastLocation.setLatitude(59.128229);
        lastLocation.setLongitude(11.352860);

        return lastLocation;
    }

    /**
     * Makes the current Thread sleep for 2 seconds to simulate a delay in the methods in this worker class
     */
    private void addDelay() {
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
