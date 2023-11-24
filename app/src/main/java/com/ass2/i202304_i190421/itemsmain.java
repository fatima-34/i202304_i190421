package com.ass2.i202304_i190421;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class itemsmain extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button logout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemsmain);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.home);

        logout = findViewById(R.id.Logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home) {
                    Intent intent = new Intent(itemsmain.this,itemsmain.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.search) {
                    Intent intent = new Intent(itemsmain.this,search.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.plus) {
                    Intent intent = new Intent(itemsmain.this,itembuy.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.chat) {
                    Intent intent = new Intent(itemsmain.this,peoplechat.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.profile) {
                    Intent intent = new Intent(itemsmain.this,profile.class);
                    startActivity(intent);
                    finish();
                    return true;

                }
                return false;
            }
        });
    }
}