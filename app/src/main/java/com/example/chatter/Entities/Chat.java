package com.example.chatter.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Chat {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String contact;
    private String messages;

    public Chat(String contact) {
        this.contact = contact;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int intId) {
        this.id = intId;
    }

    public String getContact() {
        return contact;
    }

    public String getMessages() {
        return messages;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
