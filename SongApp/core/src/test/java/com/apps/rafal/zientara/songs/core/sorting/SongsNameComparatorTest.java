package com.apps.rafal.zientara.songs.core.sorting;

import com.apps.rafal.zientara.songs.core.loggers.SystemLogger;
import com.apps.rafal.zientara.songs.core.mocks.MockSearchEngine;
import com.apps.rafal.zientara.songs.core.mocks.MockSong;
import com.apps.rafal.zientara.songs.core.mocks.MockSongsSource;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Evil on 03.09.2017.
 */
public class SongsNameComparatorTest {

    private SystemLogger logger;
    private MockSearchEngine mockSearchEngine;

    private static final SongModel artistZSongA3 = new MockSong("artist Z", "song A", 3);
    private static final SongModel artistCSongB2 = new MockSong("artist C", "song B", 2);
    private static final SongModel artistASongZ1 = new MockSong("artist A", "song Z", 1);

    @Before
    public void setup() {
        logger = new SystemLogger();
        mockSearchEngine = new MockSearchEngine(logger);
        addSongs(artistASongZ1, artistZSongA3, artistCSongB2);
    }

    private void addSongs(SongModel... songModels) {
        SongsSource songsSource = new MockSongsSource(songModels);
        songsSource.setEnabled(true);
        mockSearchEngine.addSongSource(songsSource);
    }

    @Test
    public void testSongsNameComparatorAscending() {
        setComparator(new SongsNameComparator(), true);
        List<SongModel> allSongs = getAllSongs();
        Assert.assertEquals(artistZSongA3, allSongs.get(0));
        Assert.assertEquals(artistCSongB2, allSongs.get(1));
    }

    @Test
    public void testSongsNameComparatorDescending() {
        setComparator(new SongsNameComparator(), false);
        List<SongModel> allSongs = getAllSongs();
        Assert.assertEquals(artistASongZ1, allSongs.get(0));
    }

    @Test
    public void testArtistComparatorAscending() {
        setComparator(new ArtistComparator(), true);
        List<SongModel> allSongs = getAllSongs();
        Assert.assertEquals(artistASongZ1, allSongs.get(0));
    }

    @Test
    public void testArtistComparatorDescending() {
        setComparator(new ArtistComparator(), false);
        List<SongModel> allSongs = getAllSongs();
        Assert.assertEquals(artistZSongA3, allSongs.get(0));
    }

    @Test
    public void testYearComparatorAscending() {
        setComparator(new SongsYearComparator(), true);
        List<SongModel> allSongs = getAllSongs();
        Assert.assertEquals(artistASongZ1, allSongs.get(0));
    }

    @Test
    public void testYearComparatorDescending() {
        setComparator(new SongsYearComparator(), false);
        List<SongModel> allSongs = getAllSongs();
        Assert.assertEquals(artistZSongA3, allSongs.get(0));
    }

    private List<SongModel> getAllSongs() {
        return mockSearchEngine.getAllSongs();
    }

    private void setComparator(AbstractSongsComparator songsComparator, boolean isAscending) {
        songsComparator.setAscending(isAscending);
        mockSearchEngine.setSongsComparator(songsComparator);
    }

}