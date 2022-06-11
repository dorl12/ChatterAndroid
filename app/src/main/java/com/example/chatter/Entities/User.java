package com.example.chatter.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatter.R;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private String password;
    private int pic;

    public User() {
        this.pic = R.drawable.generic_profile;
    }

    public User(String id, String name, String password, int pic) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPic() {
        return this.pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
