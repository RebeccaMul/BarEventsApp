package com.example.administrator.bignightout;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by 40107194 on 24/03/2017.
 */

public class CraftTwoMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //Declaring:
    Button about;
    Button maps;
    Button cmt;
    TextView counter;
    TextView test;

    private static final CharSequence[] MAP_TYPE_ITEMS = {"Road Map", "Satellite"};

    //Values for calculation of number of steps:
    //may need 3 textviews?
    float currentY;
    float previousY;
    int numSteps;
    int threshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft_one_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initialising the login button with existing button id:
        about = (Button) findViewById(R.id.aboutbtn);
        maps = (Button) findViewById(R.id.mapbtn);
        cmt = (Button) findViewById(R.id.commentbtn);
        counter = (TextView) findViewById(R.id.countertext);
        test = (TextView) findViewById(R.id.counterlabel);

        //Setting up threshold (when a step triggers)
        threshold = 10;

        previousY =0;
        numSteps = 0;

        enableAccelerometerListening();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.60184, -5.932103)).title("The Hudson"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
    }

    public void AboutRedirect(View view) {

        Intent menuLink = new Intent(this, CraftTwo.class);
        startActivity(menuLink);

    }

    public void MapsRedirect(View view) {

        Intent menu2Link = new Intent(this, CraftTwoMaps.class);
        startActivity(menu2Link);

    }

    public void CommentsRedirect(View view) {

        Intent menu3Link = new Intent(this, CraftTwoComment.class);
        startActivity(menu3Link);

    }

    private void enableAccelerometerListening() {
        //Initialising Sensor Manager
        SensorManager sensorManager
                = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor stepSensor
                = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (stepSensor == null){
            Toast.makeText(CraftTwoMaps.this,
                    "No Accelerometer Sensor!",
                    Toast.LENGTH_LONG).show();
        }else{

            sensorManager.registerListener(stepSensorEventListener,
                    stepSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }

    }

    SensorEventListener stepSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {

            //Gather values from accelerometer
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            //Fetch current y value
            currentY = y;

            //if statement determines if the y value greater than the sensitivity threshold then add a step.
            if (Math.abs(currentY - previousY) > threshold) {
                numSteps++;
                counter.setText(String.valueOf(numSteps));
            }

            //Display the number of steps
            counter.setText(String.valueOf(numSteps));

            //store previous y value
            previousY = y;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //Required method for class, can remain blank as not needed.
        }
    };

    public void changeMap(View view) {

        chooseMap();

    }


    private void chooseMap() {

        // Prepare the dialog by setting up a Builder.

        final String fDialogTitle = "Select Map Type";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(fDialogTitle);


        // Find the current map type to pre-check the item representing the current state.

        int checkItem = mMap.getMapType() - 1;


        // Add an OnClickListener to the dialog, so that the selection will be handled.

        builder.setSingleChoiceItems(

                MAP_TYPE_ITEMS,

                checkItem,

                new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int item) {

                        // Locally create a finalised object.


                        // Perform an action depending on which item was selected.

                        switch (item) {

                            case 1:

                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                                break;

                            case 2:

                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                                break;

                            default:

                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                        }

                        dialog.dismiss();

                    }

                }

        );


        // Build the dialog and show it.

        AlertDialog fMapTypeDialog = builder.create();

        fMapTypeDialog.setCanceledOnTouchOutside(true);

        fMapTypeDialog.show();

    }


}









