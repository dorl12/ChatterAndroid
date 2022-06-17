package com.example.chatter.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chatter.Entities.MessageForRoom;

@Database(entities = {MessageForRoom.class}, version = 2)
public abstract class MessageDB extends RoomDatabase{
    public abstract MessageDao messageDao();

}
