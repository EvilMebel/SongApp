package com.apps.rafal.zientara.songs.core.sources;

import com.apps.rafal.zientara.songs.core.loggers.Logger;
import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.List;

public abstract class SongsSource {
    private boolean isEnabled;
    protected Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public abstract List<SongModel> searchSongs(String searchPhrase);

    public abstract List<SongModel> getAll();

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
