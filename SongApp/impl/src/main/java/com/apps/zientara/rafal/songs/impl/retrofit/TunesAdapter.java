package com.apps.zientara.rafal.songs.impl.retrofit;

import com.apps.zientara.rafal.songs.impl.retrofit.services.TunesService;
import com.apps.zientara.rafal.songs.impl.sources.TunesSongsSource;

import retrofit2.Retrofit;

public class TunesAdapter {
    private final TunesService tunesService;
    TunesSongsSource tunesRestAdapter;

    public TunesAdapter() {
        tunesRestAdapter = new TunesSongsSource();
        Retrofit retrofit = tunesRestAdapter.getRetrofit();
        tunesService = retrofit.create(TunesService.class);
    }

    public TunesService getTunesService() {
        return tunesService;
    }
}
