package com.example.fuxinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor accel;
    private ConstraintLayout cly;
    private static final String Tag = "Fuxinas:\t";

    private TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView)findViewById(R.id.textView);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
        cly = (ConstraintLayout)findViewById(R.id.main_layout);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accel,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("Hello");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        double x,y,z;
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        t1.setText("X : "+Math.round(x*100)/100.0);
        t2.setText("Y : "+Math.round(y*100)/100.0);
        t3.setText("Z : "+Math.round(z*100)/100.0);

        int X,Y,Z;
        X = Math.abs((int)(x*25.0));
        if( X > 255)
                X = 255;
        Y = Math.abs((int)(y*25.0));
        if ( Y > 255)
                Y = 255;
        Z = Math.abs((int)(z*25.0));
        if (Z > 255)
                Y = 255;

        cly.setBackgroundColor(Color.rgb(X,Y,Z));
    }
}
