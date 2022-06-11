package com.example.chatter.API;

import android.util.Log;

import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public LoginAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void post(String username, String password) {
        JsonObject loginData = new JsonObject();
        loginData.addProperty("Id", username);
        loginData.addProperty("Password", password);
        Call<String> call = webServiceAPI.generateToken(loginData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                Log.i("MyPersonalToken", token);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // note
            }
        });
    }
}

