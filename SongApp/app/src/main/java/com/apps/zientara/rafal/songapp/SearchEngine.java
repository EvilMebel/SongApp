package com.apps.zientara.rafal.songapp;

import android.content.Context;

import com.apps.rafal.zientara.songs.core.searching.SongsSource;
import com.apps.zientara.rafal.songs.impl.BaseSearchEngine;
import com.apps.zientara.rafal.songs.impl.example.sources.FakeSongsSource;
import com.apps.zientara.rafal.songs.impl.example.sources.JsonSongSource;
import com.apps.zientara.rafal.songs.impl.example.sources.TunesSongsSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public class SearchEngine extends BaseSearchEngine {

    private JsonSongSource jsonSongSource = null;

    public SearchEngine(Context context) {
        String filePath = "songs-list.json";
        prepareJsonSongSource(context, filePath);
    }

    private void prepareJsonSongSource(Context context, String filePath) {
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            jsonSongSource = new JsonSongSource(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareSongSources(List<SongsSource> songsSources) {
        songsSources.add(new FakeSongsSource());
        songsSources.add(new TunesSongsSource());
        if (jsonSongSource != null)
            songsSources.add(jsonSongSource);
    }
}
