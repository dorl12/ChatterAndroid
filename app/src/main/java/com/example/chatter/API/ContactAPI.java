package com.example.chatter.API;

import com.example.chatter.Entities.Contact;
import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.example.chatter.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ContactAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts(Token.getToken());
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                // note
            }
        });
    }
}

