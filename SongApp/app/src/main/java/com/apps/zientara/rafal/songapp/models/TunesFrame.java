package com.apps.zientara.rafal.songapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

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
