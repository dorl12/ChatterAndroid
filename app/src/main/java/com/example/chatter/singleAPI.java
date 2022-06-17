package com.example.chatter;

import androidx.lifecycle.MutableLiveData;

import com.example.chatter.API.ContactAPI;
import com.example.chatter.API.InvitationAPI;
import com.example.chatter.API.LoginAPI;
import com.example.chatter.API.MessageAPI;
import com.example.chatter.API.RegisterAPI;
import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.MessageForRoom;

import java.util.List;

public class singleAPI {
    private static ContactAPI contactAPI = null;
    private static MessageAPI messageAPI = null;
    private static LoginAPI loginAPI = null;
    private static InvitationAPI invitationAPI = null;
    private static RegisterAPI registerAPI = null;

    private singleAPI() {
        //pass
    }

    public static ContactAPI getContactAPI() {
        return contactAPI;
    }

    public static void setContactAPI(MutableLiveData<List< Contact >> contsList) {
        contactAPI = new ContactAPI(contsList);
    }

    public static MessageAPI getMessageAPI() {
        return messageAPI;
    }

    public static void setMessageAPI(MutableLiveData<List<MessageForRoom>> msgsList, String contID) {
        messageAPI = new MessageAPI(msgsList, contID);
    }

    public static LoginAPI getLoginAPI() {
        return loginAPI;
    }

    public static void setLoginAPI() {
        loginAPI = new LoginAPI();
    }

    public static InvitationAPI getInvitationAPI() {
        return invitationAPI;
    }

    public static void setInvitationAPI(String userID) {
        invitationAPI = new InvitationAPI(userID);
    }

    public static RegisterAPI getRegisterAPI() {
        return registerAPI;
    }

    public static void setRegisterAPI() {
        registerAPI = new RegisterAPI();
    }
}
