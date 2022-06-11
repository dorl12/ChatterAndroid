package com.example.chatter.Room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.chatter.Entities.Message;

@Database(entities = {Message.class}, version = 2)
public abstract class MessageDB extends RoomDatabase{
    public abstract MessageDao messageDao();

}
