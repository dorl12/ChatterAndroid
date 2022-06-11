package com.example.chatter.API;

import com.example.chatter.Entities.Contact;
import com.example.chatter.Entities.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @POST("login")
    Call<String> generateToken(@Body JsonObject body);

    @GET("api/Users/Index?id={id}")
    Call<User> getUser(@Path("id") String id, @Header("Authorization") String authHeader);

    @GET("contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String authHeader);

    @POST("contacts")
    Call<Void> createContact(@Body Contact contact, @Header("Authorization") String authHeader);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") int id, @Header("Authorization") String authHeader);
}
