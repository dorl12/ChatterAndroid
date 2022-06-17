package com.example.chatter.API;

import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitationAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String currentUserID;

    public InvitationAPI(String userID) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        currentUserID = userID;
    }

    public void post(String contactID, String server) {
        JsonObject inviteData = new JsonObject();
        inviteData.addProperty("from", currentUserID);
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
}