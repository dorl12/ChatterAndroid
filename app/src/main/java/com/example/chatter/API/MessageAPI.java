package com.example.chatter.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chatter.Entities.Message;
import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.example.chatter.Token;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    MutableLiveData<List<MessageForRoom>> msgs;
    String contactID;

    public MessageAPI(MutableLiveData<List<MessageForRoom>> msgsList, String contID) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        msgs = msgsList;
        contactID = contID;
    }

    public void get() {
        Call<List<Message>> call = webServiceAPI.getMessages(contactID, "Bearer " + Token.getToken());
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                new Thread(() -> {
                    if (response.isSuccessful()) {
                        List<MessageForRoom> newMsgList = new ArrayList<>();
                        for (Message message : response.body()) {
                            MessageForRoom newMsg = new MessageForRoom(message.getId(), message.getContent(), message.getCreated(), message.isSent(), contactID);
                            newMsgList.add(newMsg);
                        }
                        msgs.postValue(newMsgList);
                    } else {
                        try {
                            //String token = response.errorBody().string();
                            //Log.i("TRYING THIS: ", token);
                            List<MessageForRoom> newMsgList = new ArrayList<>();
                            for (Message message : response.body()) {
                                MessageForRoom newMsg = new MessageForRoom(message.getId(), message.getContent(), message.getCreated(), message.isSent(), contactID);
                                newMsgList.add(newMsg);
                            }
                            msgs.postValue(newMsgList);
                        } catch (Exception e) {
                            Log.i("Exception: ", e.toString());
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                int i = 1;
            }
        });
    }

    public void insert(MessageForRoom message) {
//        Message newMsg = new Message(message.getId(), message.getContent(), message.getCreated(), message.isSent());
        JsonObject msgsData = new JsonObject();
        msgsData.addProperty("content", message.getContent());
//        msgsData.addProperty("contact", contactID);
        Call<Void> call = webServiceAPI.insertMessage(contactID, "Bearer " + Token.getToken(), msgsData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(() -> {
                    if (response.isSuccessful()) {
                        get();
                    } else {
                        try {
                            get();
                        } catch (Exception e) {
                            Log.i("Exception: ", e.toString());
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                int i = 1;
            }
        });
    }
}
