package com.apps.zientara.rafal.songapp;

import com.apps.zientara.rafal.songs.impl.models.tunes.TunesFrame;
import com.apps.zientara.rafal.songs.impl.example.retrofit.TunesAdapter;
import com.apps.zientara.rafal.songs.impl.example.retrofit.services.TunesService;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class TunesTest {

    @Test
    public void iTunesConnectionJackJohnson() throws Exception {
        TunesAdapter tunesAdapter = new TunesAdapter();
        TunesService tunesService = tunesAdapter.getTunesService();
        Call<TunesFrame> jackJohnsonSongs = tunesService.getJackJohnsonSongs();
        Response<TunesFrame> execute = jackJohnsonSongs.execute();
        if (execute.isSuccessful()) {
            TunesFrame body = execute.body();
            System.out.printf(body.toString());
        }
    }
}
