package com.example.chatter.API;

import com.example.chatter.AppService;
import com.example.chatter.Entities.Message;
import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.Room.MessageDao;
import com.example.chatter.SingeltonSerivce;
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
    private final MessageDao messageDao;

    public MessageAPI(MessageDao messageDao) {
        this.messageDao = messageDao;
        retrofit = new Retrofit.Builder()
                .baseUrl(AppService.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void setUrl(String url){
        String newUrl = "http://" + url + "/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(newUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Message>> call = webServiceAPI.getMessages(SingeltonSerivce.getContactID(), "Bearer " + AppService.getToken());
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                new Thread(() -> {
                    List<MessageForRoom> newMsgList = new ArrayList<>();
                    for (Message message : response.body()) {
                        MessageForRoom newMsg = new MessageForRoom(message.getId(), message.getContent(), AppService.parseTime(message.getCreated()), message.isSent(), SingeltonSerivce.getContactID());
                        newMsgList.add(newMsg);
                    }
                    messageDao.clear();
                    messageDao.insertList(newMsgList);
                    SingeltonSerivce.getMessages().postValue(messageDao.index());
                }).start();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                SingeltonSerivce.getMessages().postValue(messageDao.index());
            }
        });
    }

    public void insert(MessageForRoom message) {
        JsonObject msgsData = new JsonObject();
        msgsData.addProperty("content", message.getContent());
        Call<Void> call = webServiceAPI.insertMessage(SingeltonSerivce.getContactID(), "Bearer " + AppService.getToken(), msgsData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(() -> {
                    messageDao.insert(message);
                    SingeltonSerivce.getMessages().postValue(messageDao.index());
                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                messageDao.insert(message);
                SingeltonSerivce.getMessages().postValue(messageDao.index());
            }
        });
    }
}
