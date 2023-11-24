package com.ass2.i202304_i190421;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.onesignal.Continue;
import com.onesignal.OneSignal;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class profile extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView updatePP, updateCP;
    Uri PPImage = null, CPImage = null, selectedImage=null;
    private static final String ONESIGNAL_APP_ID = "80ca0b6f-3abd-4c19-afbe-82b1d0afc6783";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);

        // Request notification permissions (if needed)
        OneSignal.getNotifications().requestPermission(true, Continue.with(r -> {
            if (r.isSuccess()) {
                if (r.getData()) {
                    // `requestPermission` completed successfully and the user has accepted permission
                }
                else {
                    // `requestPermission` completed successfully but the user has rejected permission
                }
            }
            else {
                // `requestPermission` completed unsuccessfully, check `r.getThrowable()` for more info on the failure reason
            }
        }));

        ImageView editImageView = findViewById(R.id.edit);
        LinearLayout item1 = findViewById(R.id.item1);
        LinearLayout item2 = findViewById(R.id.item2);
        LinearLayout item3 = findViewById(R.id.item3);
        LinearLayout item5 = findViewById(R.id.item5);
        LinearLayout item6 = findViewById(R.id.item6);
        LinearLayout item7 = findViewById(R.id.item7);
        LinearLayout item8 = findViewById(R.id.item8);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        updateCP = findViewById(R.id.updateCP);
        updatePP = findViewById(R.id.updatePP);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersReference = databaseReference.child("user");

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, itemdetails.class);
                startActivity(intent);
            }
        });
        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null) {
                    Toast.makeText(profile.this, "Image selected.", Toast.LENGTH_LONG).show();
                    // Get a reference to the Firebase Storage
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

                    // Create a unique reference for the user's profile picture
                    String userId = "-Ngo47SckaKysjnDn-iq";
                    StorageReference profilePicRef = storageReference.child("profileImages").child(userId + ".jpg");

                    // Upload the selected image to Firebase Storage
                    profilePicRef.putFile(selectedImage)
                            .addOnSuccessListener(taskSnapshot -> {
                                Toast.makeText(profile.this, "Image succesfully uplaoded", Toast.LENGTH_LONG).show();
                                // Get the download URL of the uploaded image
                                profilePicRef.getDownloadUrl()
                                        .addOnSuccessListener(uri -> {
                                            // URL of the uploaded image
                                            String profilePicUrl = uri.toString();

                                            // Update the profilePic field in the Realtime Database
                                            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("user").child(userId);
                                            userReference.child("profilePic").setValue(profilePicUrl);

                                            // Reset the selected image to null
                                            selectedImage = null;
                                        });
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(profile.this, "Failed to upload the Image.", Toast.LENGTH_LONG).show();
                            });
                } else {
                    Toast.makeText(profile.this, "No image is selected.", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(profile.this, EditProfile.class);
                startActivity(intent);
            }
        });

        Button button=findViewById(R.id.notification);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define notification content
                String id = OneSignal.getUser().getPushSubscription().getId();
                Toast.makeText(profile.this, id, Toast.LENGTH_LONG).show();
            }
        });

        updatePP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if an image is selected
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });

        updateCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home) {
                    Intent intent = new Intent(profile.this,itemsmain.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.search) {
                    Intent intent = new Intent(profile.this,search.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.plus) {
                    Intent intent = new Intent(profile.this,itembuy.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.chat) {
                    Intent intent = new Intent(profile.this,peoplechat.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.profile) {
                    Intent intent = new Intent(profile.this,profile.class);
                    startActivity(intent);
                    finish();
                    return true;

                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200 & resultCode==RESULT_OK) {
            selectedImage = data.getData();
        }
    }
}