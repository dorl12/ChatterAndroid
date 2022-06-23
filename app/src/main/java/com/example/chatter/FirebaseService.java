package com.example.chatter;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseService extends FirebaseMessagingService {
    public FirebaseService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        if (message.getData().get("type").equals("contact")) {
            SingeltonSerivce.getContactAPI().get();
        } else {
            SingeltonSerivce.getContactAPI().get();
            SingeltonSerivce.getMessageAPI().get();
        }
    }
}