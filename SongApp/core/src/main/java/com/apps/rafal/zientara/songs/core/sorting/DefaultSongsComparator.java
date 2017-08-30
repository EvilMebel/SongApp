package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

/**
 * Created by Evil on 29.08.2017.
 */

public class DefaultSongsComparator implements Comparator<SongModel> {

    @Override
    public int compare(SongModel o1, SongModel o2) {
        int compareSongNames = compareSongNames(o1, o2);
        if (compareSongNames == 0)
            return compareArtistName(o1, o2);
        return compareSongNames;
    }

    private int compareArtistName(SongModel o1, SongModel o2) {
        return o1.getArtist().compareTo(o2.getArtist());
    }

    private int compareSongNames(SongModel o1, SongModel o2) {
        return o1.getSongName().compareTo(o2.getSongName());
    }
}
