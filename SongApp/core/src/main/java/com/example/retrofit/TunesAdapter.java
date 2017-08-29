package com.example.retrofit;

import com.example.retrofit.services.TunesService;

import retrofit2.Retrofit;

public class TunesAdapter {
    static final String TUNES_URL = "https://itunes.apple.com";
    private final TunesService tunesService;
    TunesRestAdapter tunesRestAdapter;

    public TunesAdapter() {
        tunesRestAdapter = new TunesRestAdapter(TUNES_URL);
        Retrofit retrofit = tunesRestAdapter.getRetrofit();
        tunesService = retrofit.create(TunesService.class);
    }

    public TunesService getTunesService() {
        return tunesService;
    }
}
