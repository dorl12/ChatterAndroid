package com.example.chatter.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chatter.Entities.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDB extends RoomDatabase{
    public abstract ContactDao contactDao();
}
