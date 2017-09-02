package com.apps.rafal.zientara.songs.core.loggers;

/**
 * Created by Evil on 02.09.2017.
 */

public class SystemLogger implements Logger {
    @Override
    public void error(String message) {
        System.out.printf(String.format("Error: %s", message));
    }

    @Override
    public void info(String message) {
        System.out.printf(String.format("Info: %s", message));
    }
}
