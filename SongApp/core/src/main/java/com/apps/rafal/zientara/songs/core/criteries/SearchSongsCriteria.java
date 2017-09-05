package com.apps.rafal.zientara.songs.core.criteries;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import java.util.List;

public class SearchSongsCriteria implements SongSourceCriteria {

    private String searchPhrase;

    public SearchSongsCriteria(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    @Override
    public List<SongModel> getData(SongsSource songsSource) {
        return songsSource.searchSongs(searchPhrase);
    }
}
