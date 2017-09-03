package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

/**
 * Created by Evil on 31.08.2017.
 */

public abstract class AbstractSongsComparator implements Comparator<SongModel> {
    boolean isDescending;

    public boolean isDescending() {
        return isDescending;
    }

    public void setDescending(boolean descending) {
        isDescending = descending;
    }

    int compareArtistName(SongModel o1, SongModel o2) {
        int compareArtist = o1.getArtist().compareTo(o2.getArtist());
        return applyOrder(compareArtist);
    }

    private int applyOrder(int compareArtist) {
        if (isDescending)
            compareArtist *= -1;
        return compareArtist;
    }

    int compareSongNames(SongModel o1, SongModel o2) {
        int compareNames = o1.getSongName().compareTo(o2.getSongName());
        return applyOrder(compareNames);
    }

    int compareYear(SongModel o1, SongModel o2) {
        int releaseYear1 = getYear(o1);
        int releaseYear2 = getYear(o2);
        return applyOrder(releaseYear1 - releaseYear2);
    }

    private Integer getYear(SongModel o2) {
        Integer releaseYear2 = o2.getReleaseYear();
        if (releaseYear2 == null)
            releaseYear2 = 0;
        return releaseYear2;
    }
}