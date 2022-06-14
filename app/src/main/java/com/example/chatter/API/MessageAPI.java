package com.example.chatter.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chatter.Entities.Message;
import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.example.chatter.Token;

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
    MutableLiveData<List<Message>> msgs;
    String contactID;

    public MessageAPI(MutableLiveData<List<Message>> msgsList, String contID) {
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
                        // Do your success stuff...
                        Log.i("HERE", " WON");
                    } else {
                        try {
                            String token = response.errorBody().string();
                            Log.i("TRYING THIS: ", token);
                            List<Message> newMsgList = new ArrayList<>(response.body());
                            msgs.postValue(newMsgList);
                        } catch (Exception e) {
                            Log.i("Exception: ", e.toString());
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                // note
            }
        });
    }
}

