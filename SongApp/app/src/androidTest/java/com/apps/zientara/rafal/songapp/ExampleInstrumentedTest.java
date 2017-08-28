package com.apps.zientara.rafal.songapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.apps.zientara.rafal.songapp.helpers.SongsListReader;
import com.example.model.SongModel;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.apps.zientara.rafal.songapp", appContext.getPackageName());

        String filePath = "songs-list.json";
        InputStream inputStream = appContext.getAssets().open(filePath);
        JsonFileConverter converter = new JsonFileConverter();
        List<SongModel> songs = converter.getSongs(inputStream);
        Assert.assertNotNull(songs);
        Assert.assertFalse(songs.isEmpty());
//        SongsListReader reader = new SongsListReader(appContext);
//        Assert.assertNotNull(reader);
//        Assert.assertNotNull(reader.getJsonString());
//        System.out.println(reader.getJsonString());
//        Log.d("LOL","text = "  + reader.getJsonString());



    }
}
