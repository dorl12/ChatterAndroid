package com.example.chatter.API;

import android.util.Log;

import com.example.chatter.AppService;
import com.example.chatter.MyApplication;
import com.example.chatter.R;
import com.example.chatter.SingeltonSerivce;
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

    public void logIn(String username, String password) {
        JsonObject loginData = new JsonObject();
        loginData.addProperty("Id", username);
        loginData.addProperty("Password", password);
        Call<String> call = webServiceAPI.generateToken(loginData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                new Thread(() -> {
                    if (response.isSuccessful()) {
                        // Do your success stuff...
                    } else {
                        String returnValue = "";
                        try {
                            returnValue = response.errorBody().string();
                            Log.i("Is it here: ", AppService.getToken());
                        } catch (Exception e) {
                            Log.i("Exception: ", e.toString());
                        }
                        if (response.code()==404){
                            AppService.setToken(returnValue);
                            SingeltonSerivce.getHasToken().postValue(true);
                            SingeltonSerivce.getLoginValue().postValue("true");
                        }else if (response.code() == 400){
                            SingeltonSerivce.getLoginValue().postValue(returnValue);
                        }


                    }
                }).start();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                    int i = 1;
            }
        });
    }
}

