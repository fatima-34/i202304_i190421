package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    private ImageView backArrow;
    private Spinner countrySpinner;
    private Spinner citySpinner;
    private TextView saveChanges;
    private EditText name, email, contact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        countrySpinner = findViewById(R.id.country);
        citySpinner = findViewById(R.id.city);
        saveChanges = findViewById(R.id.savechanges);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);

        String[] countries = {"Pakistan", "US", "UK"};
        String[] cities = {"Islamabad", "Peshawar", "Lahore"};

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersReference = databaseReference.child("user");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(countryAdapter);
        citySpinner.setAdapter(cityAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedCountry = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle nothing selected (if needed)
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

        backArrow = findViewById(R.id.backarrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, profile.class);
                startActivity(intent);
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user inputs
                String newName = name.getText().toString();
                String newEmail = email.getText().toString();
                String newContact = contact.getText().toString();

                String userId = "-Ngo47SckaKysjnDn-iq";
                databaseReference.child("user").child(userId).child("name").setValue(newName);
                databaseReference.child("user").child(userId).child("email").setValue(newEmail);
                databaseReference.child("user").child(userId).child("contact").setValue(newContact);

                Intent intent = new Intent(EditProfile.this, profile.class);
                startActivity(intent);
            }
        });
    }
}