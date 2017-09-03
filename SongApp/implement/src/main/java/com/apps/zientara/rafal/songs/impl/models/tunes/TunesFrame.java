package com.apps.zientara.rafal.songs.impl.models.tunes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TunesFrame {
    @SerializedName("resultCount")
    Integer resultCount;

    @SerializedName("results")
    List<TunesSong> resultsSongs;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<TunesSong> getResultsSongs() {
        return resultsSongs;
    }

    public void setResultsSongs(List<TunesSong> resultsSongs) {
        this.resultsSongs = resultsSongs;
    }
}
