package com.example.chatter.Room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.Message;

import java.util.List;

public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE id = :id")
    Message get(int id);

    @Insert
    void insert(Message...message);

    @Update
    void update(Message...message);

    @Delete
    void delete(Message...message);
}
