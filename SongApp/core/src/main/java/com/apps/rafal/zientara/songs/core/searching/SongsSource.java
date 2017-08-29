package com.apps.rafal.zientara.songs.core.searching;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public interface SongsSource {
    List<SongModel> searchSongs(String searchPhrase);

    List<SongModel> getAll();
}
