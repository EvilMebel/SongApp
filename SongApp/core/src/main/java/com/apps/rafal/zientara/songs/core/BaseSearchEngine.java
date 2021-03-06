package com.apps.rafal.zientara.songs.core;

import com.apps.rafal.zientara.songs.core.criteries.AllSongsCriteria;
import com.apps.rafal.zientara.songs.core.criteries.SearchSongsCriteria;
import com.apps.rafal.zientara.songs.core.criteries.SongSourceCriteria;
import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sorting.AbstractSongsComparator;
import com.apps.rafal.zientara.songs.core.sorting.SongsNameComparator;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseSearchEngine {
    private List<SongsSource> songsSources;
    private AbstractSongsComparator songsComparator;
    protected Logger logger;

    public BaseSearchEngine(Logger logger) {
        this.logger = logger;
        songsComparator = new SongsNameComparator();
        songsSources = new ArrayList<>();
    }

    public AbstractSongsComparator getSongsComparator() {
        return songsComparator;
    }

    public void setSongsComparator(AbstractSongsComparator songsComparator) {
        this.songsComparator = songsComparator;
    }

    public List<SongModel> searchSongs(String searchPhrase) {
        return getSongModels(new SearchSongsCriteria(searchPhrase));
    }

    public List<SongModel> getAllSongs() {
        return getSongModels(new AllSongsCriteria());
    }

    private List<SongModel> getSongModels(SongSourceCriteria criteria) {
        List<SongModel> output = new ArrayList<>();
        for (SongsSource source : songsSources) {
            if (source.isEnabled())
                output.addAll(criteria.getData(source));
        }
        Collections.sort(output, songsComparator);
        return output;
    }

    public boolean removeSongSource(SongsSource songsSource) {
        return songsSources.remove(songsSource);
    }

    public void addSongSource(SongsSource songsSource) {
        songsSources.add(songsSource);
    }
}
