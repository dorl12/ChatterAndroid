package com.example.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chatter.API.ContactAPI;
import com.example.chatter.Entities.Contact;
import com.example.chatter.adapters.ChatsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ChatsActivity extends AppCompatActivity implements ChatsListAdapter.OnChatListener {

    MutableLiveData<List<Contact>> contacts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        RecyclerView lstChats = findViewById(R.id.lstChats);
        SwipeRefreshLayout refreshChatsLayout = findViewById(R.id.refreshChatsLayout);
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        FloatingActionButton btnExit = findViewById(R.id.btnExit);
        contacts = new MutableLiveData<>();

        final ChatsListAdapter adapter = new ChatsListAdapter(this, this);
        lstChats.setAdapter(adapter);
        lstChats.setLayoutManager(new LinearLayoutManager(this));

        ContactAPI contactAPI = new ContactAPI(contacts);
        contactAPI.get();
        contacts.observe(this, (contactList) -> {
            adapter.setContacts(contactList);
        });

        lstChats.setClickable(true);

        //Once the submit button clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //Validity check
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });

    }

    // When click on contact Activate the function:
    @Override
    public void OnChatClick(int position) {
        Log.d("H","I" + position);
        Intent intent = new Intent(this, ChatContent.class);
        //intent.putExtra("MORe", (Parcelable) contacts.get(position));
        startActivity(intent);
    }
}