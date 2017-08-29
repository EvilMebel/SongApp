package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Comparator;

/**
 * Created by Evil on 29.08.2017.
 */

public class DefaultSongsComparator implements Comparator<SongModel> {

    @Override
    public int compare(SongModel o1, SongModel o2) {
        int compareArtist = o1.getArtist().compareTo(o2.getArtist());
        if (compareArtist == 0)
            return o1.getSongName().compareTo(o2.getSongName());
        return compareArtist;
    }
}
