package com.example.anti_drowsiness_hud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tomer.fadingtextview.FadingTextView;

import java.util.Timer;
import java.util.TimerTask;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DriveActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor accelerometer;
    private AdView mAdView;


    ImageView eyes;
    public static int x = 0;
    public static int y = 0;
    public static int height;
    public static int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        eyes = findViewById(R.id.eyes_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        class RotateEyes extends TimerTask {
            int degree = 1;

            public void run() {
                eyes.setScaleX(degree);
                degree *= -1;}
        }
        TimerTask rotateEyes = new RotateEyes();
        new Timer().scheduleAtFixedRate(rotateEyes, 0, 6000);

    }




    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            x = (int) (event.values[0]);
            y = (int) (event.values[1]);
            int locX = (int)eyes.getX();
            int locY = (int)eyes.getY();

            if(locY > 0 && locY < height-eyes.getMeasuredHeight()&& locX+eyes.getMeasuredWidth()*0.2 > 0 && locX+eyes.getMeasuredWidth()*0.8 < width){
                eyes.setX(locX + y);
                eyes.setY(locY + x);
            } else{
                eyes.setX((width-eyes.getMeasuredWidth())/2);
                eyes.setY((height -eyes.getMeasuredHeight())/2);

            }

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(DriveActivity.this, ActivityHome.class));
        finish();

    }

}
