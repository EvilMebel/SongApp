package com.apps.zientara.rafal.songs.impl.example.retrofit.services;

import com.apps.zientara.rafal.songs.impl.models.tunes.TunesFrame;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TunesService {
    @GET("/search?term=jack+johnson")
    Call<TunesFrame> getJackJohnsonSongs();

    @GET("/search?term={search}")
    Call<TunesFrame> getSongsAndSearch(@Path("search") String search);//// TODO: 29.08.2017 Header?
}
