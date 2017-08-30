package com.apps.rafal.zientara.songs.core.helpers;

import com.apps.rafal.zientara.songs.core.model.SongModel;

/**
 * Created by Evil on 29.08.2017.
 */

public class SongModelMatcher {
    public static boolean matches(SongModel songModel, String word) {
        return songModel.getArtist().contains(word) ||
                songModel.getSongName().toLowerCase().contains(word.toLowerCase());
    }
}