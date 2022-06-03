package com.example.chatter.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatter.R;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private String server;
    private String last;
    private String created;
    private int pic;

    public Contact() {
        this.pic = R.drawable.generic_profile;
    }

    public Contact(String id, String name, String server, String last, String created, int pic) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.created = created;
        this.pic = pic;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return this.server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getPic() {
        return this.pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }


}
