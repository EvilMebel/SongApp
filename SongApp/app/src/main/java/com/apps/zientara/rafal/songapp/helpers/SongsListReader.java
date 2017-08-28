package com.apps.zientara.rafal.songapp.helpers;

import android.content.Context;

/**
 * Created by Evil on 25.08.2017.
 */

public class SongsListReader {

    private final String jsonString;

    public SongsListReader(Context context) {
        String filePath = "songs-list.json";
//        context.getAssets().open(filePath);
        FileReader reader = new FileReader(filePath);
        jsonString = reader.loadJSONFromAsset(context);
    }

    public String getJsonString() {
        return jsonString;
    }
}
