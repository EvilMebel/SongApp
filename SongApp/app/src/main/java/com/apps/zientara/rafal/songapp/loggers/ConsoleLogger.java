package com.apps.zientara.rafal.songapp.loggers;

import android.util.Log;

import com.apps.rafal.zientara.songs.core.loggers.Logger;

public class ConsoleLogger implements Logger {

    public static final String TAG = ConsoleLogger.class.getSimpleName();

    @Override
    public void error(String message) {
        Log.e(TAG, String.format("Error: %s", message));
    }

    @Override
    public void info(String message) {
        Log.i(TAG, String.format("Info: %s", message));
    }
}
