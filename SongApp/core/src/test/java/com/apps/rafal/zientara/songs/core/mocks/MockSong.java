package com.apps.rafal.zientara.songs.core.mocks;

import com.apps.rafal.zientara.songs.core.helpers.SongModelMatcher;
import com.apps.rafal.zientara.songs.core.model.SongModel;

public class MockSong implements SongModel {

    private String artist;
    private String songName;
    private Integer releaseYear;

    public MockSong(String artist, String songName, Integer releaseYear) {
        this.artist = artist;
        this.songName = songName;
        this.releaseYear = releaseYear;
    }

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
        return SongModelMatcher.defaultMatchesQuery(words, this);
    }

    @Override
    public String toString() {
        return "MockSong{" +
                "artist='" + artist + '\'' +
                ", songName='" + songName + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
