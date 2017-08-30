package com.apps.zientara.rafal.songs.impl.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.sources.SongsSource;
import com.apps.zientara.rafal.songs.impl.converters.JsonFileConverter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

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
        String[] words = searchPhrase.split(" ");
        List<SongModel> output = new ArrayList<>();
        for (SongModel song : songs) {
            if (song.matchesQuery(words))
                output.add(song);
        }
        return output;
    }

    @Override
    public List<SongModel> getAll() {
        return songs;
    }
}
