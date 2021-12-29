package com.example.anti_drowsiness_hud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        TextView instruction = findViewById(R.id.instruction2);
        ImageView eyes = findViewById(R.id.eyes_main);
        eyes.setVisibility(View.INVISIBLE);


        CountDownTimer timer = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                instruction .setVisibility(View.INVISIBLE);
                eyes.setVisibility(View.VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


//(or GONE)
            }
        }.start();


    }


}