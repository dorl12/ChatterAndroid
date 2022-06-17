package com.example.chatter.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chatter.Entities.Contact;
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

public class ContactAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    MutableLiveData<List<Contact>> conts;

    public ContactAPI(MutableLiveData<List<Contact>> contsList) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        conts = contsList;
    }

    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts("Bearer " + Token.getToken());
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                new Thread(() -> {
                    List<Contact> newContList = new ArrayList<>(response.body());
                    for (Contact contact : newContList) {
                        contact.setPic(R.drawable.generic_profile);
                    }
                    conts.postValue(newContList);
                }).start();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                // note
            }
        });
    }

    public void insert(Contact contact) {
//        Message newMsg = new Message(message.getId(), message.getContent(), message.getCreated(), message.isSent());
        JsonObject contactData = new JsonObject();
        contactData.addProperty("id", contact.getId());
        contactData.addProperty("name", contact.getName());
        contactData.addProperty("server", contact.getServer());
//        contactData.addProperty("last", contact.getLast());
//        contactData.addProperty("lastdate", contact.getCreated());
        Call<Void> call = webServiceAPI.insertContact("Bearer " + Token.getToken(), contactData);
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

