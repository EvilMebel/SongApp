package com.apps.zientara.rafal.songs.impl.example.sources;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.rafal.zientara.songs.core.searching.SongsSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public class JsonSongSource implements SongsSource {

    private final List<SongModel> songs;

    public JsonSongSource(InputStream inputStream) {
        JsonFileConverter converter = new JsonFileConverter();
        songs = converter.getSongs(inputStream);
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
