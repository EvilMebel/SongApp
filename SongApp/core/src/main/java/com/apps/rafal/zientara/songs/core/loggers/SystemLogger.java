package com.apps.rafal.zientara.songs.core.loggers;

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
