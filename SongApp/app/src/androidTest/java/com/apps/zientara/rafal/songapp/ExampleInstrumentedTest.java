package com.apps.zientara.rafal.songapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.apps.zientara.rafal.songapp.helpers.SongsListReader;
import com.apps.zientara.rafal.songapp.retrofit.TunesAdapter;
import com.apps.zientara.rafal.songapp.retrofit.services.TunesService;
import com.example.model.SongModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private Context appContext;

    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();

    }

    @Test
    public void useAppContext() throws Exception {
        assertEquals("com.apps.zientara.rafal.songapp", appContext.getPackageName());
    }

    @Test
    public void jsonTest() throws Exception {
        String filePath = "songs-list.json";
        InputStream inputStream = appContext.getAssets().open(filePath);
        JsonFileConverter converter = new JsonFileConverter();
        List<SongModel> songs = converter.getSongs(inputStream);
        Assert.assertNotNull(songs);
        Assert.assertFalse(songs.isEmpty());
    }
}
