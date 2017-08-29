package com.apps.zientara.rafal.songs.impl.example.sources;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.searching.SongsSource;
import com.apps.zientara.rafal.songs.impl.example.retrofit.IntegerTypeAdapter;
import com.apps.zientara.rafal.songs.impl.example.retrofit.services.TunesService;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesFrame;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TunesSongsSource implements SongsSource {

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
        String searchString = getSearchString(searchPhrase);
        List<SongModel> songsList = new ArrayList<>();
        try {
            Response<TunesFrame> execute = tunesService.getSongsAndSearch(searchString).execute();
            List<TunesSong> resultsSongs = execute.body().getResultsSongs();
            for (TunesSong song : resultsSongs)
                songsList.add(song);
        } catch (IOException e) {
            //// TODO: 29.08.2017 clean up
            e.printStackTrace();
            System.out.printf(String.format("%s : %s", e.getClass().getSimpleName()), e.getMessage());
            return new ArrayList<>();
        }
        return songsList;
    }

    private String getSearchString(String searchPhrase) {
        String searchString = "";
        String[] split = searchPhrase.split(" ");
        int wordsCount = split.length;
        for (int i = 0; i < wordsCount; i++) {
            searchString += split[i];
            if (i < wordsCount - 1)
                searchPhrase += "+";
        }
        return searchString;
    }

    @Override
    public List<SongModel> getAll() {
        return new ArrayList<>();//// TODO: 29.08.2017 return something?
    }
}
