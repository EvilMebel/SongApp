package com.apps.zientara.rafal.songs.impl;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.searching.SongsSource;
import com.apps.rafal.zientara.songs.core.sorting.DefaultSongsComparator;
import com.apps.zientara.rafal.songs.impl.example.sources.FakeSongsSource;
import com.apps.zientara.rafal.songs.impl.example.sources.TunesSongsSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public class SearchEngine {
    private final FakeSongsSource fakeSongsSource;
    private final TunesSongsSource tunesSongsSource;
    List<SongsSource> songsSources;
    private Comparator<SongModel> songsComparator;

    public SearchEngine() {
        songsSources = new ArrayList<>();
        fakeSongsSource = new FakeSongsSource();
        songsSources.add(fakeSongsSource);
        tunesSongsSource = new TunesSongsSource();
        songsSources.add(tunesSongsSource);

        songsComparator = new DefaultSongsComparator();
        //// TODO: 29.08.2017 add search engines
    }

    //// TODO: 29.08.2017 sorting
    public List<SongModel> search(String searchPhrase) {
        //// TODO: 29.08.2017 search by name in all engines
        List<SongModel> fakeSongModels = fakeSongsSource.searchSongs(searchPhrase);
        List<SongModel> tunesSongModels = tunesSongsSource.searchSongs(searchPhrase);
        List<SongModel> output = new ArrayList<>();
        output.addAll(fakeSongModels);
        output.addAll(tunesSongModels);
        Collections.sort(output, songsComparator);
        return output;
    }
}
