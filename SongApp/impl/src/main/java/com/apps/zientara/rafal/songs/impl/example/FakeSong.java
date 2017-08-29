package com.apps.zientara.rafal.songs.impl.example;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.Random;

/**
 * Created by Evil on 29.08.2017.
 */

public class FakeSong implements SongModel {

    String songName;

    public FakeSong() {
        songName = "Lol" + new Random().nextInt(1000);
    }

    @Override
    public String getArtist() {
        return "Popek";
    }

    @Override
    public Integer getReleaseYear() {
        return 2017;
    }

    @Override
    public String getSongName() {
        return songName;
    }
}
