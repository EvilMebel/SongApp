package com.apps.rafal.zientara.songs.core.criteries;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import java.util.List;

public interface SongSourceCriteria {
    List<SongModel> getData(SongsSource songsSource);
}
