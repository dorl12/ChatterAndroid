package com.example.chatter.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chatter.AppService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    MutableLiveData<String> isRegister;

    public RegisterAPI(MutableLiveData<String> isRegister) {
        this.isRegister = isRegister;
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
                        isRegister.postValue("true");
                    } else {
                        try {
                            String responseString = response.errorBody().string();
                            isRegister.postValue(responseString);
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

