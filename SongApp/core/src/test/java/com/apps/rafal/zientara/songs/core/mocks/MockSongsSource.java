package com.apps.rafal.zientara.songs.core.mocks;

import com.apps.rafal.zientara.songs.core.helpers.SongModelMatcher;
import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockSongsSource extends SongsSource {
    List<SongModel> songModels;

    public MockSongsSource(List<SongModel> songModels) {
        this.songModels = songModels;
    }

    public MockSongsSource(SongModel[] songModels) {
        this.songModels = new ArrayList<>(Arrays.asList(songModels));
    }

    @Override
    public List<SongModel> searchSongs(String searchPhrase) {
        return SongModelMatcher.searchSongs(searchPhrase, songModels);
    }

    @Override
    public List<SongModel> getAll() {
        return songModels;
    }
}
