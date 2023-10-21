package com.ass2.i202304_i190421;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class peoplechat extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView messagesRecyclerView;
    private String name, email, contact;
    private String lastMessage = "";
    private int unseenMessages = 0;
    private String chatKey = "";
    private boolean dataSet = false;
    private final List<MsgModel> messagesLists = new ArrayList<>();
    private ChatAdapter messagesAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://i202304-i190421-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peoplechat);

        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        contact = getIntent().getStringExtra("contact");

        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messagesAdapter = new ChatAdapter(messagesLists, peoplechat.this);
        messagesRecyclerView.setAdapter(messagesAdapter);

        //get profile picture from firebase database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesLists.clear();
                unseenMessages = 0;
                lastMessage = "";
                chatKey = "";

                for(DataSnapshot dataSnapshot : snapshot.child("users").getChildren()) {

                    final String getMobile = dataSnapshot.child("contact").getValue(String.class);

                    dataSet = false;

                    if(!getMobile.equals(contact)) {
                        final String getName = dataSnapshot.child("name").getValue(String.class);
                        final String getProfilePic = dataSnapshot.child("profilePic").getValue(String.class);
                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int getChatCounts = (int) snapshot.getChildrenCount();
                                if (getChatCounts > 0) {
                                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;

                                        if (dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages")) {
                                            final String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);

                                            if ((getUserOne.equals(getMobile) && getUserTwo.equals(contact)) || getUserOne.equals(contact) && (getUserTwo.equals(getMobile))) {
                                                for (DataSnapshot chatDataSnapshot : dataSnapshot1.child("messages").getChildren()) {
                                                    final long getMessageKey = Long.parseLong(chatDataSnapshot.getKey());
                                                    final long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTs(peoplechat.this, getKey));

                                                    lastMessage = chatDataSnapshot.child("msg").getValue(String.class);
                                                    if (getMessageKey > getLastSeenMessage) {
                                                        unseenMessages++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                if (!dataSet) {
                                    dataSet = true;
                                    MsgModel messagesList = new MsgModel(getName, getMobile, lastMessage, getProfilePic, unseenMessages, chatKey);
                                    messagesLists.add(messagesList);
                                    messagesAdapter.updateData(messagesLists);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.chat);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home) {
                    Intent intent = new Intent(peoplechat.this,itemsmain.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.search) {
                    Intent intent = new Intent(peoplechat.this,search.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id==R.id.plus) {
                    Intent intent = new Intent(peoplechat.this,itembuy.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.chat) {
                    Intent intent = new Intent(peoplechat.this,peoplechat.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (id==R.id.profile) {
                    Intent intent = new Intent(peoplechat.this,profile.class);
                    startActivity(intent);
                    finish();
                    return true;

                }
                return false;
            }
        });
    }
}