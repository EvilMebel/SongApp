package com.apps.rafal.zientara.songs.core;

import com.apps.rafal.zientara.songs.core.criteries.AllSongsCriteria;
import com.apps.rafal.zientara.songs.core.criteries.SearchSongsCriteria;
import com.apps.rafal.zientara.songs.core.criteries.SongSourceCriteria;
import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.rafal.zientara.songs.core.sorting.DefaultSongsComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public abstract class BaseSearchEngine {
    protected List<SongsSource> songsSources;
    private Comparator<SongModel> songsComparator;
    protected Logger logger;

    public BaseSearchEngine(Logger logger) {
        this.logger = logger;
        songsComparator = new DefaultSongsComparator();
        songsSources = new ArrayList<>();
        logger.info("Ready!");
    }

    public Comparator<SongModel> getSongsComparator() {
        return songsComparator;
    }

    public void setSongsComparator(Comparator<SongModel> songsComparator) {
        this.songsComparator = songsComparator;
    }

    public List<SongModel> search(String searchPhrase) {
        logger.info(String.format("Searching... %s", searchPhrase));
        return getSongModels(new SearchSongsCriteria(searchPhrase));
    }

    public List<SongModel> getAll() {
        return getSongModels(new AllSongsCriteria());
    }

    private List<SongModel> getSongModels(SongSourceCriteria criteria) {
        List<SongModel> output = new ArrayList<>();
        for (SongsSource source : songsSources) {
            if (source.isEnabled())
                output.addAll(criteria.getData(source));
            else
                logger.info(source.getClass().getSimpleName() + " is disabled");
        }
        Collections.sort(output, songsComparator);
        return output;
    }
}
