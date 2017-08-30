package com.apps.rafal.zientara.songs.core.criteries;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import java.util.List;

/**
 * Created by Evil on 30.08.2017.
 */

public interface SongSourceCriteria {
    List<SongModel> getData(SongsSource songsSource);
}
