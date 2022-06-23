package com.example.chatter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chatter.Adapters.ChatsListAdapter;
import com.example.chatter.Adapters.MessagesListAdapter;
import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.Room.MessageDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HorizontalActivity extends AppCompatActivity implements ChatsListAdapter.OnChatListener, MessagesListAdapter.OnMessageListener {

    //chats:
    List<Contact> contacts1;
    private ChatsListAdapter chatsAdapter;
    MutableLiveData<List<Contact>> contacts;

    //content:
//    private ActivityHorizontalBinding binding;
    private int count = 0;
    private MessageDB db;
    private MessagesListAdapter messageAdapter;
    private String contactName;
    MutableLiveData<List<MessageForRoom>> messages;

    String contactServer;
    TextView contactNickname;
    MutableLiveData<String> contactRefresh = new MutableLiveData<>();
    List<MessageForRoom> newMsgLst = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        //chats:
        RecyclerView lstChats = findViewById(R.id.lstChats);
        SwipeRefreshLayout refreshChatsLayout = findViewById(R.id.refreshChatsLayout);
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        FloatingActionButton btnExit = findViewById(R.id.btnExit);
        contacts = new MutableLiveData<>();
        chatsAdapter = new ChatsListAdapter(this, this);
        lstChats.setAdapter(chatsAdapter);
        lstChats.setLayoutManager(new LinearLayoutManager(this));
        SingeltonSerivce.getContactAPI().get(); // Get all user's contacts
        SingeltonSerivce.getContacts().observe(this, (contactList) -> {
            chatsAdapter.setContacts(contactList);
            contacts1 = contactList;
        });
        lstChats.setClickable(true);
        //Once the add contact button clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //Validity check
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(v -> {
            finish();
        });

        //content:
//        binding = ActivityHorizontalBinding.inflate(getLayoutInflater());
        TextView txtInputBar = findViewById(R.id.inputBar);
        ImageButton btnSend = findViewById(R.id.sendButton);
        RecyclerView lstMessages = findViewById(R.id.messagesList);
        contactNickname = findViewById(R.id.contactNicknameHorizontal);
        messages = new MutableLiveData<>();

        messageAdapter = new MessagesListAdapter(this, this);
        lstMessages.setAdapter(messageAdapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

        //SingeltonSerivce.getMessageAPI().get();
        SingeltonSerivce.getMessages().observe(this, (messageList) -> {
            messageAdapter.setMessages(messageList);
            newMsgLst = messageList;
        });

        contactRefresh.observe(this, (text) -> {
            contactNickname.setText(text);
        });

        btnSend.setOnClickListener(v -> {
            if(!txtInputBar.getText().toString().equals("")) {
                Calendar cal = Calendar.getInstance();
                String timeOfMessage = timeFormat.format(cal.getTime());
                MessageForRoom m = new MessageForRoom(count++, txtInputBar.getText().toString(), timeOfMessage, true, SingeltonSerivce.getContactID());
                SingeltonSerivce.getMessageAPI().insert(m);
                SingeltonSerivce.getUtilsAPI().transfer(m,contactServer);
                txtInputBar.setText("");
                SingeltonSerivce.getContactAPI().get();
            }
        });

    }

    // When click on contact Activate the function:
    @Override
    public void OnChatClick(int position) {
        Log.d("H","I" + position);
        SingeltonSerivce.setContactID(contacts1.get(position).getId());
        contactRefresh.postValue(contacts1.get(position).getName());
        contactServer = contacts1.get(position).getServer();
        SingeltonSerivce.getMessageAPI().get();
    }

    @Override
    public void OnLongChatClick(int position) {

    }

    @Override
    public void OnLongMessageClick(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        chatsAdapter.setContacts(contacts1);
        messageAdapter.setMessages(newMsgLst);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {return;}
        super.onConfigurationChanged(newConfig);

        Intent intent = new Intent(this, ChatsActivity.class);
        startActivity(intent);
        finish();
    }
}