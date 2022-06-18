package com.example.chatter.API;

import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.example.chatter.AppService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilsAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UtilsAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
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

    public void invitations(String contactID, String server) {
        setUrl(server);
        JsonObject inviteData = new JsonObject();
        inviteData.addProperty("from", AppService.getUserID());
        inviteData.addProperty("to", contactID);
        inviteData.addProperty("server", server);
        Call<Void> call = webServiceAPI.inviteContact(inviteData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int i = 1;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                int j = 1;
            }
        });
    }


    public void transfer(MessageForRoom m, String server) {
        setUrl(server);
        JsonObject inviteData = new JsonObject();
        inviteData.addProperty("from", AppService.getUserID());
        inviteData.addProperty("to", m.getTo());
        inviteData.addProperty("content", m.getContent());
        Call<Void> call = webServiceAPI.inviteContact(inviteData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int i = 1;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                int j = 1;
            }
        });
    }

}