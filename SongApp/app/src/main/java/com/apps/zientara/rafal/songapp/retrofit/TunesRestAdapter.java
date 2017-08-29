package com.apps.zientara.rafal.songapp.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.apps.zientara.rafal.songapp.converter.serializers.IntegerTypeAdapter;
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
        //2005-03-01T08:00:00Z
        return new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
//                .setDateFormat("2005-03-01T08:00:00Z")
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @NonNull
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
