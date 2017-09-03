package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.loggers.SystemLogger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Evil on 03.09.2017.
 */
public class TunesSongsSourceTest extends AbstractSongsSourceTest {

    @Override
    protected SongsSource getSongSource(Logger logger) {
        TunesSongsSource tunesSongsSource = new TunesSongsSource();
        tunesSongsSource.setLogger(logger);
        return tunesSongsSource;
    }

    @Override
    protected void assertModel(SongModel songModel) {
        Assert.assertEquals(songModel.getClass(), TunesSong.class);
    }

}