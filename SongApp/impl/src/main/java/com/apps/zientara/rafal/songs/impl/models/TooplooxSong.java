package com.apps.zientara.rafal.songs.impl.models;

import com.apps.rafal.zientara.songs.core.helpers.SongModelMatcher;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.google.gson.annotations.SerializedName;

public class TooplooxSong implements SongModel {

    @SerializedName("Song Clean")
    String songName;

    @SerializedName("ARTIST CLEAN")
    String artist;

    @SerializedName("Release Year")
    Integer releaseYear;

    @SerializedName("COMBINED")
    String combined;

    @SerializedName("First")
    int first;

    @SerializedName("PlayCount")
    int playCount;

    @SerializedName("F*G")
    int fog;

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getSongName() {
        return songName;
    }

    @Override
    public boolean matchesQuery(String[] words) {
        for (String word : words) {
            if (matchesAny(word))
                return true;
        }
        return false;
    }

    private boolean matchesAny(String word) {
        return SongModelMatcher.matches(this, word);//// TODO: 29.08.2017 check more
    }
}
