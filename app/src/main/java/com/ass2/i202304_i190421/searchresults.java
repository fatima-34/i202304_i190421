package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class searchresults extends AppCompatActivity {
    private Spinner bestMatchSpinner;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);

        LinearLayout item1 = findViewById(R.id.item1);
        LinearLayout item2 = findViewById(R.id.item2);
        LinearLayout item3 = findViewById(R.id.item3);
        LinearLayout item4 = findViewById(R.id.item4);
        LinearLayout item5 = findViewById(R.id.item5);
        LinearLayout item6 = findViewById(R.id.item6);
        LinearLayout item7 = findViewById(R.id.item7);
        LinearLayout item8 = findViewById(R.id.item8);

        bestMatchSpinner = findViewById(R.id.bestmatch);
        backArrow = findViewById(R.id.backarrow);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchresults.this, itemdetails.class);
                startActivity(intent);
            }
        });

        String[] bestMatchOptions = {"Best Match", "<=$500", ">$500"};

        ArrayAdapter<String> bestMatchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bestMatchOptions);
        bestMatchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bestMatchSpinner.setAdapter(bestMatchAdapter);

        bestMatchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = (String) adapterView.getItemAtPosition(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(searchresults.this, itemsmain.class);
                startActivity(intent);
            }
        });
    }
}