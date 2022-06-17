package com.example.chatter.API;

import android.util.Log;

import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.example.chatter.Token;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public RegisterAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void post(String username, String password, String name) {
        JsonObject registerData = new JsonObject();
        registerData.addProperty("Id", username);
        registerData.addProperty("Password", password);
        registerData.addProperty("Name", name);
        Call<Void> call = webServiceAPI.registerUser(registerData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(() -> {
                    if (response.isSuccessful()) {
                        // Do your success stuff...
                        System.out.println("HERE");
                    } else {
                        try {
                            String token = response.errorBody().string();
                            Token.setToken(token);
                            Log.i("Is it here: ", Token.getToken());
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

