package com.example.amanda.helloworld;

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
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CompassActivity extends AppCompatActivity implements SensorEventListener{
    private ImageView image;
    private float currentDegree = 0f;
    private SensorManager sm;
    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        image = (ImageView) findViewById(R.id.imageview_compass_312235);
        sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        text= (TextView) findViewById(R.id.text_compass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        text.setText("Direction: " + Float.toString(degree) + " degrees");
        RotateAnimation ra = new RotateAnimation(currentDegree,-degree, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(210);
        ra.setFillAfter(true);
        image.startAnimation(ra);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
