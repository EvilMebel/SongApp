package com.apps.rafal.zientara.songs.core;

import com.apps.rafal.zientara.songs.core.criteries.AllSongsCriteria;
import com.apps.rafal.zientara.songs.core.criteries.SearchSongsCriteria;
import com.apps.rafal.zientara.songs.core.criteries.SongSourceCriteria;
import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sorting.AbstractSongsComparator;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.rafal.zientara.songs.core.sorting.SongsNameComparator;
import com.apps.rafal.zientara.songs.core.sources.SourcesSynchronizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public abstract class BaseSearchEngine {
    private final SourcesSynchronizer sourcesSynchronizer;
    protected List<SongsSource> songsSources;
    private AbstractSongsComparator songsComparator;
    protected Logger logger;

    public BaseSearchEngine(Logger logger) {
        this.logger = logger;
        songsComparator = new SongsNameComparator();
        songsSources = new ArrayList<>();
        sourcesSynchronizer = new SourcesSynchronizer();
        logger.info("Ready!");
    }

    public AbstractSongsComparator getSongsComparator() {
        return songsComparator;
    }

    public void setSongsComparator(AbstractSongsComparator songsComparator) {
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
        List<SongModel> output = new SourcesSynchronizer().getSongModelsSync(criteria, songsSources);
        Collections.sort(output, songsComparator);
        return output;
    }
}
