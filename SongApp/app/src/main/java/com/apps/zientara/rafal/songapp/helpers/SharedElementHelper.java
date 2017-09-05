package com.apps.zientara.rafal.songapp.helpers;

import com.apps.rafal.zientara.songs.core.model.SongModel;

public class SharedElementHelper {

    public static final String ICON = "_icon";
    public static final String ARTIST = "_artist";
    public static final String NAME = "_name";

    public static String getArtistTrans(SongModel songModel) {
        return getUniqueName(songModel) + ARTIST;
    }

    public static String getNameTrans(SongModel songModel) {
        return getUniqueName(songModel) + NAME;
    }

    public static String getIconTrans(SongModel songModel) {
        return getUniqueName(songModel) + ICON;
    }

    public static String getUniqueName(SongModel songModel) {
        return songModel.getReleaseYear() + songModel.getSongName() + songModel.getArtist();
    }
}
