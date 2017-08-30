package com.apps.zientara.rafal.songs.impl.converters;

import com.apps.zientara.rafal.songs.impl.retrofit.IntegerTypeAdapter;
import com.apps.zientara.rafal.songs.impl.models.TooplooxSong;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonFileConverter {

    public List<SongModel> getSongs(InputStream source) {
        Gson gson = getGson();
        Reader reader = new InputStreamReader(source);
        Type listType = new TypeToken<ArrayList<TooplooxSong>>(){}.getType();
        List<SongModel> songModels = gson.fromJson(reader, listType);
        return songModels;
    }

    private Gson getGson() {
        return new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                    .create();
    }
}
