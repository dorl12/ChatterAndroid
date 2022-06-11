package com.example.chatter.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int intId;
    private int id;
    private String content;
    private String created;
    private boolean sent;
    private String to;

    public Message(int id, String content, String created, boolean sent, String to) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.to = to;
    }

    public int getIntId() {
        return this.intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
