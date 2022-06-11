package com.example.chatter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.chatter.Entities.Contact;
import com.example.chatter.Room.ContactDB;
import com.example.chatter.Room.ContactDao;

public class AddContactActivity extends AppCompatActivity {

    private ContactDB db;
    private ContactDao contactDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), ContactDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();

        Button addContactButton = findViewById(R.id.add_contact_button);
        addContactButton.setOnClickListener(view -> {
            EditText addContactName = findViewById(R.id.addContactName);
            EditText addContactUsername = findViewById(R.id.addContactUsername);
            EditText addContactserver = findViewById(R.id.addContactserver);
            Contact newContact = new Contact(addContactUsername.toString(), addContactName.getText().toString(), addContactserver.toString(), "No Messages", "00:00", R.drawable.generic_profile);
            contactDao.insert(newContact);

            finish();
        });
    }
}
