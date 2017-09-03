package com.apps.rafal.zientara.songs.core.helpers;

import com.apps.rafal.zientara.songs.core.model.SongModel;

/**
 * Created by Evil on 03.09.2017.
 */

public class SongHelper {

    public static String getYearText(SongModel song) {
        if (song.getReleaseYear() != null && song.getReleaseYear() != -1)
            return Integer.toString(song.getReleaseYear());
        return "";
    }
}
