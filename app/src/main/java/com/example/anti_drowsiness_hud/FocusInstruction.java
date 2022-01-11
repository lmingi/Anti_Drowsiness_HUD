package com.example.anti_drowsiness_hud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class FocusInstruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_instruction);
    }        CountDownTimer timer = new CountDownTimer(4000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(FocusInstruction.this, FocusActivity.class);
            startActivity(intent);
        }
    }.start();

}