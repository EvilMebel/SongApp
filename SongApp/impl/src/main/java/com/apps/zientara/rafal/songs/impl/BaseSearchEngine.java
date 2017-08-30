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

public abstract class BaseSearchEngine {
    List<SongsSource> songsSources;
    private Comparator<SongModel> songsComparator;

    public BaseSearchEngine() {
        songsComparator = new DefaultSongsComparator();
        songsSources = new ArrayList<>();
        prepareSongSources(songsSources);
    }

    protected abstract void prepareSongSources(List<SongsSource> songsSources);
    //// TODO: 29.08.2017 sorting
    public List<SongModel> search(String searchPhrase) {
        //// TODO: 29.08.2017 search by name in all engines
        List<SongModel> output = new ArrayList<>();
        for(SongsSource source : songsSources) {
            output.addAll(source.searchSongs(searchPhrase));
        }
        Collections.sort(output, songsComparator);
        return output;
    }
}
