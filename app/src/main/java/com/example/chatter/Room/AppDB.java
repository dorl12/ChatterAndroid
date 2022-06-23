package com.example.chatter.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.MessageForRoom;

@Database(entities = {Contact.class, MessageForRoom.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
}
