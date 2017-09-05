package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;

import junit.framework.Assert;

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