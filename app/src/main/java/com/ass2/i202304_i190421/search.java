package com.ass2.i202304_i190421;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class search extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private searchAdapter searchAdapter;
    private ArrayList<Items> itemList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();
        searchAdapter = new searchAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FeaturedItems");

        // Retrieve data from Firebase and populate the itemList
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Items item = snapshot.getValue(Items.class);
                itemList.add(item);

                searchAdapter = new searchAdapter(itemList);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("FirebaseData", "Data retrieved: " + itemList.size() + " items.");
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home) {
                    Intent intent = new Intent(search.this,itemsmain.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.search) {
                    Intent intent = new Intent(search.this,search.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.plus) {
                    Intent intent = new Intent(search.this,itembuy.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.chat) {
                    Intent intent = new Intent(search.this,peoplechat.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.profile) {
                    Intent intent = new Intent(search.this,profile.class);
                    startActivity(intent);
                    finish();
                    return true;

                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

            MenuItem searchItem = menu.findItem(R.id.action_search); // Use the correct item ID
            SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                    public boolean onQueryTextSubmit(String query) {
                        // Handle search submit if needed
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchAdapter.getFilter().filter(newText);
                        return true;
                    }
                });

                return true;
            }
    }