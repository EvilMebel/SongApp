package com.apps.rafal.zientara.songs.core;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.loggers.SystemLogger;
import com.apps.rafal.zientara.songs.core.mocks.MockSearchEngine;
import com.apps.rafal.zientara.songs.core.mocks.MockSong;
import com.apps.rafal.zientara.songs.core.mocks.MockSongsSource;
import com.apps.rafal.zientara.songs.core.model.SongModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseSearchEngineTest {
    private Logger logger;
    private BaseSearchEngine searchEngine;

    @Before
    public void setup() {
        logger = new SystemLogger();
        searchEngine = new MockSearchEngine(logger);
    }

    @Test
    public void getEmptySearchEngine() {
        resultIsEmptyAndNotNull(searchEngine.getAllSongs());
        resultIsEmptyAndNotNull(searchEngine.searchSongs(""));
    }

    private void resultIsEmptyAndNotNull(List<SongModel> songModels) {
        Assert.assertNotNull(songModels);
        Assert.assertTrue(songModels.isEmpty());
    }

    @Test
    public void testSearchEngineWithOneSource_getAll() {
        int allSongsCountExpected = 2;
        int songsCount = 2;
        addSongsSourceWithRecordsCount(songsCount);
        List<SongModel> allSongs = searchEngine.getAllSongs();
        assertExpectedListSize(allSongsCountExpected, allSongs);
    }


    @Test
    public void testSearchEngineWithTwoSource_getAll() {
        int allSongsCountExpected = 4;
        int songsCountForSource = 2;
        addSongsSourceWithRecordsCount(songsCountForSource);
        addSongsSourceWithRecordsCount(songsCountForSource);
        List<SongModel> allSongs = searchEngine.getAllSongs();
        assertExpectedListSize(allSongsCountExpected, allSongs);
    }

    @Test
    public void testSearchEngineWithOneSource_searchArtist() {
        int songsCount = 2;
        addSongsSourceWithRecordsCount(songsCount);
        List<SongModel> foundArtistsSongs = searchEngine.searchSongs("artist");
        assertExpectedListSize(songsCount, foundArtistsSongs);
        foundArtistsSongs = searchEngine.searchSongs("artist1");
        assertExpectedListSize(1, foundArtistsSongs);
        foundArtistsSongs = searchEngine.searchSongs("artist1500100900");
        assertExpectedListSize(0, foundArtistsSongs);
    }

    private void addSongsSourceWithRecordsCount(int songsCount) {
        ArrayList<SongModel> songModels = getSongModels(songsCount);
        MockSongsSource songsSource = new MockSongsSource(logger, songModels);
        songsSource.setEnabled(true);
        searchEngine.addSongSource(songsSource);
    }

    private void assertExpectedListSize(int expectedSongsCount, List<SongModel> songModels) {
        Assert.assertNotNull(songModels);
        Assert.assertEquals(expectedSongsCount, songModels.size());
    }

    private ArrayList<SongModel> getSongModels(int songsCount) {
        ArrayList<SongModel> songModels = new ArrayList<>();
        for (int i = 0; i < songsCount; i++)
            songModels.add(new MockSong("artist" + i, "songName" + i, 2017 + i));
        return songModels;
    }

}