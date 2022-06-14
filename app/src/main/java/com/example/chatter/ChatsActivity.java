package com.example.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chatter.API.ContactAPI;
import com.example.chatter.Room.ContactDB;
import com.example.chatter.Room.ContactDao;
import com.example.chatter.Adapters.ChatsListAdapter;
import com.example.chatter.Entities.Contact;
import com.example.chatter.adapters.ChatsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ChatsActivity extends AppCompatActivity implements ChatsListAdapter.OnChatListener {

    //List<Contact> contacts;
    List<Contact> contacts1;

    private ContactDB db;
    private ContactDao contactDao;
    private ChatsListAdapter adapter;
    // MutableLiveData<List<Contact>> contacts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        RecyclerView lstChats = findViewById(R.id.lstChats);
        SwipeRefreshLayout refreshChatsLayout = findViewById(R.id.refreshChatsLayout);
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        FloatingActionButton btnExit = findViewById(R.id.btnExit);
        // contacts = new MutableLiveData<>();

        adapter = new ChatsListAdapter(this, this);
        lstChats.setAdapter(adapter);
        lstChats.setLayoutManager(new LinearLayoutManager(this));

        // ContactAPI contactAPI = new ContactAPI(contacts);
        // contactAPI.get();
        db = Room.databaseBuilder(getApplicationContext(), ContactDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();
        contacts1 = contactDao.index();
        adapter.setContacts(contacts1);

        // contacts.observe(this, (contactList) -> {
        //     adapter.setContacts(contactList);
        // });

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

        lstChats.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                int position = 0;
                Contact con = contacts1.remove(position);
                contactDao.delete(con);
                adapter.setContacts(contacts1);
                return true;
            }
        });

    }

    // When click on contact Activate the function:
    @Override
    public void OnChatClick(int position) {
        Log.d("H","I" + position);
        Intent intent = new Intent(this, ChatContent.class);
        intent.putExtra("Nickname", contacts1.get(position).getName());
        startActivity(intent);
    }

    @Override
    public void OnLongChatClick(int position) {
        Contact con = contacts1.remove(position);
        contactDao.delete(con);
        adapter.setContacts(contacts1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts1 = contactDao.index();
        adapter.setContacts(contacts1);
    }
}