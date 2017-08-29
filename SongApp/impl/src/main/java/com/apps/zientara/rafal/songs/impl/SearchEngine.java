package com.apps.zientara.rafal.songs.impl;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.searching.SongsSource;
import com.apps.zientara.rafal.songs.impl.example.sources.FakeSongsSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public class SearchEngine {
    private final FakeSongsSource fakeSongsSource;
    List<SongsSource> songsSources;

    public SearchEngine() {
        songsSources = new ArrayList<>();
        fakeSongsSource = new FakeSongsSource();
        songsSources.add(fakeSongsSource);
        //// TODO: 29.08.2017 add search engines
    }

    public List<SongModel> search(String searchPhrase) {
        //// TODO: 29.08.2017 search by name in all engines
        return fakeSongsSource.searchSongs(searchPhrase);
    }
}
