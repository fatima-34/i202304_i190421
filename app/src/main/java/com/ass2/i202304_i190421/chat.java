package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class chat extends AppCompatActivity {
    private ImageView backArrow;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ImageView voiceCallImageView = findViewById(R.id.voicecall);
        ImageView videoCallImageView = findViewById(R.id.videocall);
        ImageView cameraImageView = findViewById(R.id.camera);

        voiceCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chat.this, call.class);
                startActivity(intent);
            }
        });

        videoCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chat.this, VideoCall.class);
                startActivity(intent);
            }
        });

        cameraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chat.this, CameraPhotos.class);
                startActivity(intent);
            }
        });

        backArrow = findViewById(R.id.backarrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chat.this, peoplechat.class);
                startActivity(intent);
            }
        });
    }
}