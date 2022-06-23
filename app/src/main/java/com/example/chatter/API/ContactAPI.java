package com.example.chatter.API;

import com.example.chatter.AppService;
import com.example.chatter.Entities.Contact;
import com.example.chatter.R;
import com.example.chatter.Room.ContactDao;
import com.example.chatter.SingeltonSerivce;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {

    private final ContactDao contactDao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    // MyApplication.getContext().getString(R.string.BaseURL)

    public ContactAPI(ContactDao contactDao) {
        this.contactDao = contactDao;
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

    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts("Bearer " + AppService.getToken());
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                new Thread(() -> {
                    List<Contact> newContList = new ArrayList<>(response.body());
                    for (Contact contact : newContList) {
                        contact.setPic(R.drawable.generic_profile);
                    }
                    contactDao.clear();
                    contactDao.insertList(newContList);
                    SingeltonSerivce.getContacts().postValue(contactDao.index());
                }).start();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                SingeltonSerivce.getContacts().postValue(contactDao.index());
            }
        });
    }

    public void insert(Contact contact) {
        JsonObject contactData = new JsonObject();
        contactData.addProperty("id", contact.getId());
        contactData.addProperty("name", contact.getName());
        contactData.addProperty("server", contact.getServer());
        Call<Void> call = webServiceAPI.insertContact("Bearer " + AppService.getToken(), contactData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(() -> {
                    contactDao.insert(contact);
                    SingeltonSerivce.getContacts().postValue(contactDao.index());
                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                contactDao.insert(contact);
                SingeltonSerivce.getContacts().postValue(contactDao.index());
            }
        });
    }
}

