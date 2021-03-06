package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesFrame;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;
import com.apps.zientara.rafal.songs.impl.retrofit.IntegerTypeAdapter;
import com.apps.zientara.rafal.songs.impl.retrofit.services.TunesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TunesSongsSource extends SongsSource {
    private final Retrofit retrofit;
    private final TunesService tunesService;

    public TunesSongsSource() {
        retrofit = createRestAdapter(TunesService.TUNES_URL);
        tunesService = retrofit.create(TunesService.class);
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

    @Override
    public List<SongModel> searchSongs(String searchPhrase) {
        List<SongModel> songsList = new ArrayList<>();
        try {
            trySearchSongs(searchPhrase, songsList);
        } catch (Exception e) {
            logger.error("Searching iTunes failed. Check internet connection");
            return new ArrayList<>();
        }
        return songsList;
    }

    private void trySearchSongs(String searchString, List<SongModel> songsList) throws java.io.IOException {
        Response<TunesFrame> execute = tunesService.getSongsAndSearch(searchString).execute();
        List<TunesSong> resultsSongs = execute.body().getResultsSongs();
        for (TunesSong song : resultsSongs)
            songsList.add(song);
    }

    @Override
    public List<SongModel> getAll() {
        return new ArrayList<>();
    }
}
