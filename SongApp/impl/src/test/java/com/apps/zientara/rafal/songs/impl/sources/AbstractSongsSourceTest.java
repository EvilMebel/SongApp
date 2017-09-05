package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.loggers.SystemLogger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class AbstractSongsSourceTest {

    private Logger logger;
    private SongsSource songsSource;

    @Before
    public void setup() throws Exception {
        logger = new SystemLogger();
        songsSource = getSongSource(logger);
    }

    protected abstract SongsSource getSongSource(Logger logger) throws FileNotFoundException;

    @Test
    public void searchSongsOneWord() throws Exception {
        List<SongModel> songModels = songsSource.searchSongs("john");
        Assert.assertNotNull(songModels);
        Assert.assertTrue(!songModels.isEmpty());
        SongModel songModel = songModels.get(0);
        Assert.assertNotNull(songModel.getArtist());
        Assert.assertNotNull(songModel.getSongName());
        Assert.assertNotNull(songModel.getReleaseYear());
        assertModel(songModel);
    }

    protected abstract void assertModel(SongModel songModel);

    @Test
    public void searchSongsTwoWords() throws Exception {
        List<SongModel> john = songsSource.searchSongs("jack johnson");
        Assert.assertNotNull(john);
        Assert.assertTrue(!john.isEmpty());
    }

    @Test
    public void getAll() throws Exception {
        List<SongModel> john = songsSource.getAll();
        Assert.assertNotNull(john);
    }
}
