package com.example.chatter;

import androidx.lifecycle.MutableLiveData;

import com.example.chatter.API.ContactAPI;
import com.example.chatter.API.LoginAPI;
import com.example.chatter.API.MessageAPI;
import com.example.chatter.API.RegisterAPI;
import com.example.chatter.API.UtilsAPI;
import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.MessageForRoom;

import java.util.List;

public class SingeltonSerivce {

    private static ContactAPI contactAPI = null;
    private static MessageAPI messageAPI = null;
    private static LoginAPI loginAPI = null;
    private static UtilsAPI utilsAPI = null;
    private static RegisterAPI registerAPI = null;
    private static MutableLiveData<Boolean> hasToken = new MutableLiveData<>();
    private static MutableLiveData<String> loginValue= new MutableLiveData<>();

    private SingeltonSerivce() {
        //pass
    }

    public static MutableLiveData<Boolean> getHasToken() {
        return hasToken;
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
        if (loginAPI == null) {
            loginAPI = new LoginAPI();
        }
        return loginAPI;
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
//
//    public static void setHasToken() {
//        hasToken.postValue();
//    }
}
