package com.example.chatter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chatter.Adapters.ChatsListAdapter;
import com.example.chatter.Entities.Contact;
import com.example.chatter.Room.AppDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ChatsActivity extends AppCompatActivity implements ChatsListAdapter.OnChatListener {

//    List<Contact> contacts;
    List<Contact> contacts1;

    private AppDB db;
//    private ContactDao contactDao;
    private ChatsListAdapter adapter;
     MutableLiveData<List<Contact>> contacts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        RecyclerView lstChats = findViewById(R.id.lstChats);
        SwipeRefreshLayout refreshChatsLayout = findViewById(R.id.refreshChatsLayout);
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        FloatingActionButton btnExit = findViewById(R.id.btnExit);
        contacts = new MutableLiveData<>();

        adapter = new ChatsListAdapter(this, this);
        lstChats.setAdapter(adapter);
        lstChats.setLayoutManager(new LinearLayoutManager(this));

//        SingeltonSerivce.setContactAPI(contacts);
        SingeltonSerivce.getContactAPI().get(); // Get all user's contacts

         SingeltonSerivce.getContacts().observe(this, (contactList) -> {
             adapter.setContacts(contactList);
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

//        lstChats.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v){
//                int position = 0;
//                Contact con = contacts1.remove(position);
//                contactDao.delete(con);
//                adapter.setContacts(contacts1);
//                return true;
//            }
//        });

    }



    // When click on contact Activate the function:
    @Override
    public void OnChatClick(int position) {
        Log.d("H","I" + position);
        Intent intent = new Intent(this, ChatContent.class);
        intent.putExtra("Nickname", contacts1.get(position).getName());
        intent.putExtra("ID", contacts1.get(position).getId());
        intent.putExtra("contactServer", contacts1.get(position).getServer());
        SingeltonSerivce.setContactID(contacts1.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void OnLongChatClick(int position) {
//        Contact con = contacts1.remove(position);
//        contactDao.delete(con);
//        adapter.setContacts(contacts1);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        SingeltonSerivce.getContactAPI().get(); // Get all user's contacts

//        contacts1 = contactDao.index();
        adapter.setContacts(contacts1);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {return;}
        super.onConfigurationChanged(newConfig);

        Intent intent = new Intent(this, HorizontalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}