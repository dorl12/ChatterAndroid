package com.example.chatter.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chatter.Entities.Chat;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat")
    List<Chat> index();

    @Query("SELECT * FROM chat WHERE id = :id")
    Chat get(int id);

    @Insert
    void insert(Chat...chat);

    @Update
    void update(Chat...chat);

    @Delete
    void delete(Chat...chat);
}
