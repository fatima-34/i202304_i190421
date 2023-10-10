package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class last extends AppCompatActivity {
    private static final long DELAY_MILLIS = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(last.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_MILLIS);
    }
}