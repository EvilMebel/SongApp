package com.example.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TunesRestAdapter {
    private final Retrofit retrofit;

    public TunesRestAdapter(final String serverUrl) {
        retrofit = createRestAdapter(serverUrl);
    }

    private Retrofit createRestAdapter(final String serverUrl) {
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    private Gson getGson() {
        return new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
