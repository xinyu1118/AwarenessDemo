package com.example.headphones;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.awareness.snapshot.HeadphoneStateResult;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.awareness.state.HeadphoneState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity {
    Location lastLatLng = null;

    private static final int MY_PERMISSION_LOCATION = 1;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Awareness.API)
                .build();
        mGoogleApiClient.connect();

        // detect Google service connection status
        // If d=false and e=true, it means that the device is trying to connect Google service all the time...
//        boolean d = mGoogleApiClient.isConnected();
//        boolean e = mGoogleApiClient.isConnecting();

        // Another way to check Google api connection status
//        GoogleApiAvailability b = GoogleApiAvailability.getInstance();
//        int c = b.isGooglePlayServicesAvailable(this);

        /**
         * Detect user activity
         */
//        Awareness.SnapshotApi.getDetectedActivity(mGoogleApiClient)
//                .setResultCallback(new ResultCallback<DetectedActivityResult>() {
//                    @Override
//                    public void onResult(@NonNull DetectedActivityResult detectedActivityResult) {
//                        if (!detectedActivityResult.getStatus().isSuccess()) {
//                            Log.d("Log", "Could not get the current activity.");
//                            return;
//                        }
//                        ActivityRecognitionResult ar = detectedActivityResult.getActivityRecognitionResult();
//                        DetectedActivity probableActivity = ar.getMostProbableActivity();
//                        Log.d("Log", probableActivity.toString());
//                    }
//                });


        /**
         * Location updates
         */
//        if (ContextCompat.checkSelfPermission(
//                MainActivity.this,
//                Manifest.permission.ACCESS_FINE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                    MainActivity.this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    MY_PERMISSION_LOCATION
//            );
//        }
//        Awareness.SnapshotApi.getLocation(mGoogleApiClient)
//                .setResultCallback(new ResultCallback<LocationResult>() {
//                    @Override
//                    public void onResult(@NonNull LocationResult locationResult) {
//                        if (!locationResult.getStatus().isSuccess()) {
//                            Log.d("Log", "Could not get location.");
//                            return;
//                        }
//                        Location location = locationResult.getLocation();
//                        Log.d("Log", "Lat: " + location.getLatitude() + ", Lng: " + location.getLongitude());
//                        if (!location.equals(lastLatLng)) {
//                            Log.d("Log", "Location updates");
//                        }
//                        lastLatLng = location;
//                    }
//                });


        /**
         * Headphones plugged in
         */
        Awareness.SnapshotApi.getHeadphoneState(mGoogleApiClient)
                .setResultCallback(new ResultCallback<HeadphoneStateResult>() {
                    @Override
                    public void onResult(@NonNull HeadphoneStateResult headphoneStateResult) {
                        if (!headphoneStateResult.getStatus().isSuccess()) {
                            Log.d("Log", "Could not get headphone state.");
                            return;
                        }
                        HeadphoneState headphoneState = headphoneStateResult.getHeadphoneState();
                        if (headphoneState.getState() == HeadphoneState.PLUGGED_IN) {
                            Log.d("Log", "Headphones are plugged in.");
                        } else {
                            Log.d("Log", "Headphones are NOT plugged in.");
                        }
                    }
                });


    }
}
