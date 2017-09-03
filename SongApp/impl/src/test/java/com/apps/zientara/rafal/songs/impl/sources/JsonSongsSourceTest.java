package com.apps.zientara.rafal.songs.impl.sources;

import android.util.Log;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.loggers.SystemLogger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.models.TooplooxSong;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Evil on 03.09.2017.
 */
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