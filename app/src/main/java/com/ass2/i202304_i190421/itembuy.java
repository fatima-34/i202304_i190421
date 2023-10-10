package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class itembuy extends AppCompatActivity {
    private ImageView crossImageView;
    private ImageView uploadImageImageView;
    private ImageView uploadVideoImageView;
    private Spinner citySpinner;
    private Button postItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itembuy);

        crossImageView = findViewById(R.id.cross);
        uploadImageImageView = findViewById(R.id.uploadimage);
        uploadVideoImageView = findViewById(R.id.uploadvideo);
        citySpinner = findViewById(R.id.selectcity);
        postItemButton = findViewById(R.id.postitem);

        String[] cities = {"Islamabad", "Peshawar", "Lahore"};
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        crossImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itembuy.this, itemsmain.class);
                startActivity(intent);
            }
        });

        uploadImageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itembuy.this, CameraPhotos.class);
                startActivity(intent);
            }
        });

        uploadVideoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itembuy.this, CameraVideo.class);
                startActivity(intent);
            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedCity = (String) adapterView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        postItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itembuy.this, itemsmain.class);
                startActivity(intent);
            }
        });
    }
}