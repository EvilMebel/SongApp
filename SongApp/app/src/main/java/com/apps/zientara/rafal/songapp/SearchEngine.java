package com.apps.zientara.rafal.songapp;

import android.content.Context;

import com.apps.rafal.zientara.songs.core.BaseSearchEngine;
import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.sorting.AbstractSongsComparator;
import com.apps.rafal.zientara.songs.core.sorting.ArtistComparator;
import com.apps.rafal.zientara.songs.core.sorting.SongsNameComparator;
import com.apps.rafal.zientara.songs.core.sorting.SongsYearComparator;
import com.apps.zientara.rafal.songapp.preferences.DataOrderPreferences;
import com.apps.zientara.rafal.songapp.preferences.DataSourcePreferences;
import com.apps.zientara.rafal.songapp.preferences.enums.CriteriaTypeEnum;
import com.apps.zientara.rafal.songs.impl.sources.FakeSongsSource;
import com.apps.zientara.rafal.songs.impl.sources.JsonSongsSource;
import com.apps.zientara.rafal.songs.impl.sources.TunesSongsSource;

import java.io.IOException;
import java.io.InputStream;

public class SearchEngine extends BaseSearchEngine {
    private FakeSongsSource fakeSongsSource;
    private JsonSongsSource jsonSongsSource;
    private TunesSongsSource tunesSongsSource;

    public SearchEngine(Logger logger, Context applicationContext) {
        super(logger);
        addSongSources(applicationContext);
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

    private void addSongSources(Context applicationContext) {
        addJsonSongsSource(applicationContext);
        addFakeSongsSource();
        addTunesSongsSource();
    }

    private void addJsonSongsSource(Context applicationContext) {
        String filePath = "songs-list.json";
        prepareJsonSongSource(applicationContext, filePath);
        addSongSource(jsonSongsSource);
    }

    private void addFakeSongsSource() {
        fakeSongsSource = new FakeSongsSource();
        fakeSongsSource.setLogger(logger);
        addSongSource(fakeSongsSource);
    }

    private void addTunesSongsSource() {
        tunesSongsSource = new TunesSongsSource();
        tunesSongsSource.setLogger(logger);
        addSongSource(tunesSongsSource);
    }

    public void setSongsComparator(CriteriaTypeEnum soritngCriteria) {
        AbstractSongsComparator comparator = getSongComparatorByEnum(soritngCriteria);
        setSongsComparator(comparator);
    }

    private AbstractSongsComparator getSongComparatorByEnum(CriteriaTypeEnum sortingCriteria) {
        switch (sortingCriteria) {
            case ARTIST:
                return new ArtistComparator();
            case DATE:
                return new SongsYearComparator();
            case SONG_NAME:
            default:
                return new SongsNameComparator();
        }
    }

    public void refreshSongsComparator(DataOrderPreferences dataOrderPreferences) {
        setSongsComparator(dataOrderPreferences.getSortingCriteria());
        getSongsComparator().setAscending(dataOrderPreferences.isAscending());
    }
}
