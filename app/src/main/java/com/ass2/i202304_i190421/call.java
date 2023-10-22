package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class call extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        ImageView galleryIcon = findViewById(R.id.gallery_icon);
        ImageView captureIcon = findViewById(R.id.capture_icon);
        ImageView rotateIcon = findViewById(R.id.rotate_icon);
        ImageView crossIcon = findViewById(R.id.cross);

        galleryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        captureIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rotateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        crossIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(call.this,chat.class);
                startActivity(intent);
            }
        });
    }
}