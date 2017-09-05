package com.apps.rafal.zientara.songs.core.helpers;

import com.apps.rafal.zientara.songs.core.model.SongModel;

public class SongHelper {

    public static String getYearText(SongModel song) {
        if (song.getReleaseYear() != null && song.getReleaseYear() != -1)
            return Integer.toString(song.getReleaseYear());
        return "";
    }
}
