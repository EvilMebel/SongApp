package com.apps.zientara.rafal.songs.impl.example.sources;

import com.apps.zientara.rafal.songs.impl.example.FakeSong;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.searching.SongsSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Evil on 29.08.2017.
 */

public class FakeSongsSource implements SongsSource {

    private final Random random;

    public FakeSongsSource() {
        random = new Random();
    }

    @Override
    public List<SongModel> searchSongs(String searchPhrase) {
        int recordsCount = random.nextInt(6);
        return getFakeSongModels(recordsCount);
    }

    private List<SongModel> getFakeSongModels(int recordsCount) {
        List<SongModel> songModels = new ArrayList<>();

        for (int i = 0; i < recordsCount; i++) {
            FakeSong fakeSong = new FakeSong();
            songModels.add(fakeSong);
        }
        return songModels;
    }

    @Override
    public List<SongModel> getAll() {
        int recordsCount = 200;
        return getFakeSongModels(recordsCount);
    }
}
