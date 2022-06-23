package com.example.chatter.API;

import com.example.chatter.AppService;
import com.example.chatter.Entities.MessageForRoom;
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

    public void invitations(String contactID, String server) {
        String fixedServer = AppService.fixServer(server);
        setUrl(fixedServer);
        JsonObject inviteData = new JsonObject();
        inviteData.addProperty("from", AppService.getUserID());
        inviteData.addProperty("to", contactID);
        inviteData.addProperty("server", AppService.getBaseURL().substring(7,20));
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
        String fixedServer = AppService.fixServer(server);
        setUrl(fixedServer);
        JsonObject transferData = new JsonObject();
        transferData.addProperty("from", AppService.getUserID());
        transferData.addProperty("to", m.getTo());
        transferData.addProperty("content", m.getContent());
        Call<Void> call = webServiceAPI.transferMessage(transferData);
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