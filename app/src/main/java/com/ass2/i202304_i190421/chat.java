package com.ass2.i202304_i190421;

import static java.lang.Long.parseLong;
import static java.time.LocalTime.now;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class chat extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://i202304-i190421-default-rtdb.firebaseio.com/");
    private String chatKey;
    String getUserMobile = "";
    private ImageView backBtn;
    TextView sendBtn, nameTV;
    EditText messageEditText;
    private boolean loadingFirstTime = true;
    ImageView voiceCallImageView, videoCallImageView, cameraImageView;
    private final List<ChatList> chatLists = new ArrayList<>();
    private RecyclerView chattingRecyclerView;
    private MsgAdapter chatAdapter;
    private List<MsgModel> messages;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        voiceCallImageView = findViewById(R.id.voicecall);
        videoCallImageView = findViewById(R.id.videocall);
        cameraImageView = findViewById(R.id.camera);
        backBtn = findViewById(R.id.backarrow);
        sendBtn = findViewById(R.id.sendBtn);
        nameTV = findViewById(R.id.name);
        messageEditText = findViewById(R.id.messageEditTxt);

        chattingRecyclerView = findViewById(R.id.chattingRecyclerView);
        chatAdapter = new MsgAdapter(chatLists, chat.this);
        chattingRecyclerView.setAdapter(chatAdapter);

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
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });

        //get data from chat adapter
        final String getName = getIntent().getStringExtra("name");
        final String getProfilePic = getIntent().getStringExtra("profilePic");
        chatKey = getIntent().getStringExtra("chatKey");
        final String getMobile = getIntent().getStringExtra("contact");

        //get user mobile from memory
        getUserMobile = MemoryData.getData(chat.this);
        nameTV.setText(getName);
        //Picasso.get().load(getProfilePic).into(profilePic);
        //chattingRecyclerView.setHasFixedSize(true);
        chattingRecyclerView.setLayoutManager(new LinearLayoutManager(chat.this));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(chatKey.isEmpty()) {
                        chatKey = "1";

                        if (snapshot.hasChild("chat")) {
                            chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);
                        }
                    }

                    if(snapshot.hasChild("chat")) {
                        if(snapshot.child("chat").child(chatKey).hasChild("messages")) {
                            chatLists.clear();

                            for(DataSnapshot messageSnapshot : snapshot.child("chat").child(chatKey).child("messsages").getChildren()) {
                                if(messageSnapshot.hasChild("msg") && messageSnapshot.hasChild("contact")) {
                                    final String messageTimestamps = messageSnapshot.getKey();
                                    final String getMobile = messageSnapshot.child("contact").getValue(String.class);
                                    final String getMsg = messageSnapshot.child("msg").getValue(String.class);

                                    //Timestamp timestamp = new Timestamp(Long.parseLong(messageTimestamps));//Long.parseLong(messageTimestamps));
                                    //Date date = new Date(timestamp.getTime());
                                    long timestamp = Long.parseLong(messageTimestamps);//Long.parseLong(messageTimestamps));
                                    Date date = new Date(timestamp);

                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
                                    ChatList chatList = new ChatList(getMobile, getName, getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
                                    chatLists.add(chatList);

                                    if(loadingFirstTime || Long.parseLong(messageTimestamps) > Long.parseLong(MemoryData.getLastMsgTs(chat.this, chatKey))) {
                                        MemoryData.saveLastMsgTS(messageTimestamps, chatKey, chat.this);
                                        loadingFirstTime = false;
                                        chatAdapter.updateChatList(chatLists);

                                        chattingRecyclerView.scrollToPosition(chatLists.size() - 1);
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getTxtMessage = messageEditText.getText().toString();

                //get current timestamps
                final String currentTimestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);

                databaseReference.child("chat").child(chatKey).child("user_1").setValue(getUserMobile);
                databaseReference.child("chat").child(chatKey).child("user_2").setValue(getMobile);
                databaseReference.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("msg").setValue(getTxtMessage);
                databaseReference.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("contact").setValue(getUserMobile);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(chat.this, peoplechat.class);
//                startActivity(intent);
                finish();
            }
        });
    }
}