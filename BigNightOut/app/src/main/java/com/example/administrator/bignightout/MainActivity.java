package com.example.administrator.bignightout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaring
    Button menubtn;
    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialising the button & textView with existing elements from the XML:
        menubtn = (Button)findViewById(R.id.viewmenu);
        counter = (TextView) findViewById(R.id.countertext);

        //Rebecca - Declaring & initialising a new sensor manager
        SensorManager sensorManager
                = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor
                = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //Rebecca - Testing for light sensor on phone, if not present alert the user
        if (lightSensor == null){
            Toast.makeText(MainActivity.this,
                    "No Light Sensor!",
                    Toast.LENGTH_SHORT).show();
        }else{
            //Rebecca - Otherwise register the sensorManager with an instance of the SensorEventListener inner class
            sensorManager.registerListener(lightSensorEventListener, lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void MenuRedirect(View view) {
        //Intent which redirects the user to the MenuAcitivity class, onClick for the Enter button.
        Intent menuLink = new Intent(this, MenuActivity.class);
        startActivity(menuLink);
    }

    //Rebecca - Inner class creating SensorEventListener to deal with the light sensor.
    SensorEventListener lightSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {

            //Testing if light sensor is initialised, if so store it's current value
            //Otherwise display an error message for the user.
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                float currentReading = event.values[0];

            } else if (event.sensor.getType() != Sensor.TYPE_LIGHT) {
                counter.setText("No light sensor present.");
                counter.setVisibility(View.VISIBLE);
            }

            //Reading current value for light sensor and adjust the brightness according to levels. Alert the user of the change.
            if (event.values[0] < 100) {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 1F; // set 100% brightness
                getWindow().setAttributes(layoutParams);
                counter.setText("100%");
            } else if (event.values[0] > 200) {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 0.75F; // set 50% brightness
                getWindow().setAttributes(layoutParams);
                counter.setText("75%");
            } else if (event.values[0] > 300) {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 0.5F; // set 25% brightness
                getWindow().setAttributes(layoutParams);
                counter.setText("50%");
            } else if (event.values[0] > 400) {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 0.25F; // set 25% brightness
                getWindow().setAttributes(layoutParams);
                counter.setText("25%");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void OpenLogin(View view) {
        startActivity(new Intent(this,Login.class));
    }

    public void OpenRegFromMain(View view) {
        startActivity(new Intent(this,Register.class));
    }

}
