package com.example.amanda.helloworld;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    Sensor accelerometer;
    SensorManager sm;
    TextView accelerationX;
    TextView accelerationY;
    TextView accelerationZ;
    protected float[] gravSensorVals;
    static final float ALPHA = 0.10f;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accelerationX = (TextView) findViewById(R.id.accelerationX);
        accelerationY = (TextView) findViewById(R.id.accelerationY);
        accelerationZ = (TextView) findViewById(R.id.accelerationZ);

        accelerationX.setTextSize(40);
        accelerationY.setTextSize(40);
        accelerationZ.setTextSize(40);
    }
    protected float[] lowPass( float[] input, float[] output ) {
        if ( output == null ) return input;
        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravSensorVals = lowPass(event.values.clone(), gravSensorVals);
            accelerationX.setText("X: " + gravSensorVals[0]);
            accelerationY.setText("Y: " + gravSensorVals[1]);
            accelerationZ.setText("Z: " + gravSensorVals[2]);
            //gravSensorVals=null;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

