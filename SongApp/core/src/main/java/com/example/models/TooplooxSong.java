package com.example.models;

import com.example.model.SongModel;
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
    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getSongName() {
        return songName;
    }
}
