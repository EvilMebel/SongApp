package com.apps.zientara.rafal.songapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.apps.zientara.rafal.songapp.models.TunesFrame;
import com.apps.zientara.rafal.songapp.models.TunesSong;
import com.apps.zientara.rafal.songapp.retrofit.TunesAdapter;
import com.apps.zientara.rafal.songapp.retrofit.services.TunesService;
import com.example.model.SongModel;

import junit.framework.Assert;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

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
