package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

public class SongsYearComparator extends AbstractSongsComparator implements Comparator<SongModel> {

    @Override
    public int compare(SongModel o1, SongModel o2) {
        int compareYear = compareYear(o1, o2);
        if (compareYear == 0)
            return compareSongNames(o1, o2);
        return compareYear;
    }

}
