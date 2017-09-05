package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.helpers.SongModelMatcher;
import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.converters.JsonFileConverter;

import java.io.InputStream;
import java.util.List;

public class JsonSongsSource extends SongsSource {

    private final List<SongModel> songs;

    public JsonSongsSource(Logger logger, InputStream inputStream) {
        super(logger);
        JsonFileConverter converter = new JsonFileConverter();
        songs = converter.getSongs(inputStream);
        logger.info("JSON Songs count: " + songs.size());
    }

    @Override
    public List<SongModel> searchSongs(String searchPhrase) {
        return SongModelMatcher.searchSongs(searchPhrase, songs);
    }

    @Override
    public List<SongModel> getAll() {
        return songs;
    }
}
