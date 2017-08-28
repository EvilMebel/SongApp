package com.apps.zientara.rafal.songapp.models;

import com.example.model.SongModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Evil on 26.08.2017.
 */

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
    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getSongName() {
        return songName;
    }
}
