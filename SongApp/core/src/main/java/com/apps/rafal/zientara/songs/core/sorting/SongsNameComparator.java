package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

public class SongsNameComparator extends AbstractSongsComparator{

    @Override
    public int compare(SongModel o1, SongModel o2) {
        int compareSongNames = compareSongNames(o1, o2);
        if (compareSongNames == 0)
            return compareArtistName(o1, o2);
        return compareSongNames;
    }

}
