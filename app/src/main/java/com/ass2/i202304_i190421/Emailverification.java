package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Emailverification extends AppCompatActivity {
    private ImageView backArrow;

    @SuppressLint("MissingInflateId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);

         backArrow = findViewById(R.id.backarrow);

         backArrow.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 Intent intent =  new Intent(Emailverification.this, MainActivity.class);
                 startActivity(intent);
             }
         });
    }
}