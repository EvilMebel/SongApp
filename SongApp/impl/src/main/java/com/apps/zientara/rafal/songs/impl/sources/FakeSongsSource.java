package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.models.FakeSong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeSongsSource extends SongsSource {
    private static final int FAKE_LOADING_MILLIS = 300;
    private final Random random;

    public FakeSongsSource() {
        random = new Random();
    }

    @Override
    public List<SongModel> searchSongs(String searchPhrase) {
        if (searchPhrase.length() > 10)
            return new ArrayList<>();
        int recordsCount = random.nextInt(6) + 1;
        return getFakeSongModels(recordsCount);
    }

    private List<SongModel> getFakeSongModels(int recordsCount) {
        List<SongModel> songModels = generateFakeData(recordsCount);
        simulateLoading();
        return songModels;
    }

    private List<SongModel> generateFakeData(int recordsCount) {
        List<SongModel> songModels = new ArrayList<>();
        for (int i = 0; i < recordsCount; i++) {
            FakeSong fakeSong = new FakeSong();
            songModels.add(fakeSong);
        }
        return songModels;
    }

    private void simulateLoading() {
        try {
            Thread.sleep(FAKE_LOADING_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SongModel> getAll() {
        int recordsCount = 200;
        return getFakeSongModels(recordsCount);
    }
}
