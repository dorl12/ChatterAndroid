package com.example.chatter.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String created;
    private boolean sent;

    public Message(int id, String content, String created, boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
