package com.apps.rafal.zientara.songs.core.criteries;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import java.util.List;

public class AllSongsCriteria implements SongSourceCriteria {
    @Override
    public List<SongModel> getData(SongsSource songsSource) {
        return songsSource.getAll();
    }
}
