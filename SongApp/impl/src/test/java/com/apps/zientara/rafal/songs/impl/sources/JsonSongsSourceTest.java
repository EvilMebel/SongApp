package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.models.TooplooxSong;

import junit.framework.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JsonSongsSourceTest extends AbstractSongsSourceTest {
    private static final String FILE_PATH = "impl\\src\\test\\assets\\songs-list.json";

    @Override
    protected SongsSource getSongSource(Logger logger) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        InputStream inputStream = new FileInputStream(file);
        JsonSongsSource jsonSongsSource = new JsonSongsSource(logger, inputStream);
        jsonSongsSource.setLogger(logger);
        return jsonSongsSource;
    }

    @Override
    protected void assertModel(SongModel songModel) {
        Assert.assertEquals(songModel.getClass(), TooplooxSong.class);
    }

}