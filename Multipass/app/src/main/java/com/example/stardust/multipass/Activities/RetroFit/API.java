package com.example.stardust.multipass.Activities.RetroFit;


import com.example.stardust.multipass.Activities.Models.alumno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BaseURL="http://192.168.0.2:8080/RestF/webresources/";
    public static Retrofit getApiJSON(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
