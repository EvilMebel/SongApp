package com.apps.zientara.rafal.songapp;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songs.impl.example.sources.TunesSongsSource;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesFrame;
import com.apps.zientara.rafal.songs.impl.example.retrofit.TunesAdapter;
import com.apps.zientara.rafal.songs.impl.example.retrofit.services.TunesService;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class TunesTest {

    @Test
    public void iTunesConnectionJackJohnson() throws Exception {
        TunesSongsSource songsSource = new TunesSongsSource();
        List<SongModel> songModels = songsSource.searchSongs("Jack Johnson");//// TODO: 29.08.2017 handle errors
        Assert.assertNotNull(songModels);
        Assert.assertTrue(songModels.size() > 0);

//        Response<TunesFrame> execute = jackJohnsonSongs.execute();
//        if (execute.isSuccessful()) {
//            TunesFrame body = execute.body();
//            System.out.printf(body.toString());
//        }
    }
}

