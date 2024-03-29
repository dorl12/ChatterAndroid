package com.example.chatter.Room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.chatter.Entities.Chat;


@Database(entities = {Chat.class}, version = 1)
public abstract class ChatDB extends RoomDatabase{
    public abstract ChatDao chatDao();
}
