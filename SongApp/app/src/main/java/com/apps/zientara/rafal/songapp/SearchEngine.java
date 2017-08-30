package com.apps.zientara.rafal.songapp;

import android.content.Context;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songapp.preferences.DataSourcePreferences;
import com.apps.rafal.zientara.songs.core.BaseSearchEngine;
import com.apps.zientara.rafal.songs.impl.sources.FakeSongsSource;
import com.apps.zientara.rafal.songs.impl.sources.JsonSongsSource;
import com.apps.zientara.rafal.songs.impl.sources.TunesSongsSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public class SearchEngine extends BaseSearchEngine {
    private FakeSongsSource fakeSongsSource;
    private JsonSongsSource jsonSongsSource;
    private TunesSongsSource tunesSongsSource;

    public SearchEngine(Logger logger, Context applicationContext) {
        super(logger);
        addSongSources(songsSources, applicationContext);
        refreshSourcesEnableState(applicationContext);
    }

    public void refreshSourcesEnableState(Context applicationContext) {
        DataSourcePreferences preferences = DataSourcePreferences.getInstance(applicationContext);
        fakeSongsSource.setEnabled(preferences.isFakeDataEnabled());
        jsonSongsSource.setEnabled(preferences.isLocalDbEnabled());
        tunesSongsSource.setEnabled(preferences.isTunesEnabled());
    }

    private void prepareJsonSongSource(Context context, String filePath) {
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            jsonSongsSource = new JsonSongsSource(logger, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addSongSources(List<SongsSource> songsSources, Context applicationContext) {
        String filePath = "songs-list.json";
        prepareJsonSongSource(applicationContext, filePath);
        songsSources.add(jsonSongsSource);
        fakeSongsSource = new FakeSongsSource(logger);
        songsSources.add(fakeSongsSource);
        tunesSongsSource = new TunesSongsSource(logger);
        songsSources.add(tunesSongsSource);
    }
}
