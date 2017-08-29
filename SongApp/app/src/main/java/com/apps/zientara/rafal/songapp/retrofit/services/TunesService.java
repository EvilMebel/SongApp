package com.apps.zientara.rafal.songapp.retrofit.services;

import com.apps.zientara.rafal.songapp.models.TunesFrame;
import com.apps.zientara.rafal.songapp.models.TunesSong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Evil on 29.08.2017.
 */

public interface TunesService {
    @GET("/search?term=jack+johnson")
    Call<TunesFrame> getJackJohnsonSongs();

    @GET("/search?term={search}")
    Call<TunesFrame> getSongsAndSearch(@Path("search") String search);//// TODO: 29.08.2017 Header? 
}
