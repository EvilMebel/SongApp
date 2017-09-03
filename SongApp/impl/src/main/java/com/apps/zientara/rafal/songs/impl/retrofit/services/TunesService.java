package com.apps.zientara.rafal.songs.impl.retrofit.services;

import com.apps.zientara.rafal.songs.impl.models.tunes.TunesFrame;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TunesService {
    public static final String TUNES_URL = "https://itunes.apple.com";

    @GET("/search?term=jack+johnson")
    Call<TunesFrame> getJackJohnsonSongs();

    @GET("/search")
    Call<TunesFrame> getSongsAndSearch(@Query("term") String search);
}
