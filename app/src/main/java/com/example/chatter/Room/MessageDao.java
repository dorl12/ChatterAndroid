package com.example.chatter.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chatter.Entities.MessageForRoom;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM MessageForRoom")
    List<MessageForRoom> index();

    @Query("SELECT * FROM MessageForRoom WHERE id = :id")
    MessageForRoom get(int id);

    @Insert
    void insert(MessageForRoom... messageForRoom);

    @Update
    void update(MessageForRoom... messageForRoom);

    @Delete
    void delete(MessageForRoom... messageForRoom);

//    @Query("DELETE FROM message")
//    void deleteAll();
}
