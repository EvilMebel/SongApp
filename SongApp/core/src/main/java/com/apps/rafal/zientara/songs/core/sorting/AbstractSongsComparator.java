package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

public abstract class AbstractSongsComparator implements Comparator<SongModel> {
    boolean isAscending;

    public boolean isAscending() {
        return isAscending;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    int compareArtistName(SongModel o1, SongModel o2) {
        int compareArtist = compareStrings(o1.getArtist(), o2.getArtist());
        return applyOrder(compareArtist);
    }

    private int applyOrder(int compareArtist) {
        if (!isAscending)
            compareArtist *= -1;
        return compareArtist;
    }

    int compareSongNames(SongModel o1, SongModel o2) {
        int compareNames = compareStrings(o1.getSongName(), o2.getSongName());
        return applyOrder(compareNames);
    }

    int compareStrings(final String one, final String two) {
        if (one == null ^ two == null)
            return (one == null) ? -1 : 1;
        if (one == null && two == null)
            return 0;
        return one.compareTo(two);
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
