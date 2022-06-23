package com.example.chatter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatter.Entities.Contact;
import com.example.chatter.Room.AppDB;

public class AddContactActivity extends AppCompatActivity {

    private AppDB db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Button addContactButton = findViewById(R.id.add_contact_button);
        addContactButton.setOnClickListener(view -> {
            EditText addContactName = findViewById(R.id.addContactName);
            EditText addContactUsername = findViewById(R.id.addContactUsername);
            EditText addContactserver = findViewById(R.id.addContactserver);
            Contact newContact = new Contact(addContactUsername.getText().toString(), addContactName.getText().toString(), addContactserver.getText().toString(), "No Messages", "00:00", R.drawable.generic_profile);
            SingeltonSerivce.getContactAPI().insert(newContact);
            SingeltonSerivce.getUtilsAPI().invitations(AppService.getUserID(), addContactserver.getText().toString());
            finish();
        });
    }
}
