package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

/**
 * Created by Evil on 29.08.2017.
 */

public class ArtistComparator extends AbstractSongsComparator{

    @Override
    public int compare(SongModel o1, SongModel o2) {
        int compareSongNames = compareArtistName(o1, o2);
        if (compareSongNames == 0)
            return compareSongNames(o1, o2);
        return compareSongNames;
    }
}
