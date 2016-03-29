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
    TextView acceleration;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        acceleration = (TextView) findViewById(R.id.acceleration);
        acceleration.setTextSize(40);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        acceleration.setText("X: "+event.values[0]+"\nY: "+event.values[1]+"\nZ: "+event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

