package com.ass2.i202304_i190421;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class itembuy extends AppCompatActivity {
    private ImageView crossImageView;
    private ImageView uploadImageImageView;
    private ImageView uploadVideoImageView;
    private Spinner citySpinner;
    private Button postItemButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    EditText name, rate, description;
    Uri selectedImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itembuy);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("item");

        crossImageView = findViewById(R.id.cross);
        uploadImageImageView = findViewById(R.id.uploadimage);
        uploadVideoImageView = findViewById(R.id.uploadvideo);
        citySpinner = findViewById(R.id.selectcity);
        postItemButton = findViewById(R.id.postitem);
        name = findViewById(R.id.name);
        rate = findViewById(R.id.rate);
        description = findViewById(R.id.description);

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
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
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
                if(selectedImage != null) {
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                    storageReference = storageReference.child("abc/dp.jpg");
                    storageReference.putFile(selectedImage)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                                    task
                                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String dp = uri.toString();
                                            reference.push().setValue(new Item(name.getText().toString(), rate.getText().toString(), description.getText().toString(), dp, "Islamabad"));
                                            Toast.makeText(itembuy.this, "Data uploaded successfully.", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(itembuy.this, "Failed to upload the Image.", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(itembuy.this, "Failed to upload the Image.", Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else {
                    Toast.makeText(itembuy.this, "Select an Image", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(itembuy.this, profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200 & resultCode==RESULT_OK) {
            selectedImage = data.getData();
            uploadImageImageView.setImageURI(selectedImage);
        }
    }
}