package com.apps.zientara.rafal.songapp.helpers;

import com.apps.rafal.zientara.songs.core.model.SongModel;

/**
 * Created by Evil on 04.09.2017.
 */

public class SharedElementHelper {

    public static String getUniqueName(SongModel songModel) {
        return songModel.getReleaseYear()+songModel.getSongName() + songModel.getArtist();
    }
}
