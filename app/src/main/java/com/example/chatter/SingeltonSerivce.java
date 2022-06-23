package com.example.chatter;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.chatter.API.ContactAPI;
import com.example.chatter.API.LoginAPI;
import com.example.chatter.API.MessageAPI;
import com.example.chatter.API.RegisterAPI;
import com.example.chatter.API.UtilsAPI;
import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.Room.AppDB;

import java.util.List;

public class SingeltonSerivce {


    public static MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
    public static MutableLiveData<List<MessageForRoom>> messages = new MutableLiveData<>();
    private static AppDB appDB;
    private static ContactAPI contactAPI = null;
    private static MessageAPI messageAPI = null;
    private static LoginAPI loginAPI = null;
    private static UtilsAPI utilsAPI = null;
    private static RegisterAPI registerAPI = null;
    private static MutableLiveData<Boolean> hasToken = new MutableLiveData<>();
    private static MutableLiveData<String> loginValue = new MutableLiveData<>();
    private static String contactID = null;

    public static String getContactID() {
        return contactID;
    }
    private SingeltonSerivce() {
        //pass
    }

    public static AppDB getAppDB() {
        if (appDB==null){
            appDB=  Room.databaseBuilder(MyApplication.getContext(), AppDB.class, "AppDB")
                .allowMainThreadQueries()
                .build();
        }
        return appDB;
    }

    public static MutableLiveData<Boolean> getHasToken() {
        return hasToken;
    }

    public static ContactAPI getContactAPI() {
        if (contactAPI == null){
            contactAPI = new ContactAPI(getAppDB().contactDao());
        }
        return contactAPI;
    }

    public static MessageAPI getMessageAPI() {
        if (messageAPI == null){
            messageAPI = new MessageAPI(getAppDB().messageDao());
        }
        return messageAPI;
    }

    public static LoginAPI getLoginAPI() {
        if (loginAPI == null) {
            loginAPI = new LoginAPI();
        }
        return loginAPI;
    }

    public static MutableLiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public static MutableLiveData<List<MessageForRoom>> getMessages() {
        return messages;
    }

    public static UtilsAPI getUtilsAPI() {
        if (utilsAPI == null) {
            utilsAPI = new UtilsAPI();
        }
        return utilsAPI;
    }

    public static RegisterAPI getRegisterAPI() {
        return registerAPI;
    }

    public static void setRegisterAPI(MutableLiveData<String> isRegister) {
        registerAPI = new RegisterAPI(isRegister);
    }

    public static MutableLiveData<String> getLoginValue() {
        return loginValue;
    }

    public static void setContactID(String id) {
        contactID = id;
    }
//
//    public static void setHasToken() {
//        hasToken.postValue();
//    }
}
