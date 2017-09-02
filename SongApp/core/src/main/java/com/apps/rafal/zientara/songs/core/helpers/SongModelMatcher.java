package com.apps.rafal.zientara.songs.core.helpers;

import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evil on 29.08.2017.
 */

public class SongModelMatcher {

    public static boolean defaultMatchesQuery(String[] words, SongModel songModel) {
        for (String word : words) {
            if (matches(songModel, word))
                return true;
        }
        return false;
    }

    public static boolean matches(SongModel songModel, String word) {
        return songModel.getArtist().contains(word) ||
                songModel.getSongName().toLowerCase().contains(word.toLowerCase());
    }

    public static List<SongModel> searchSongs(String searchPhrase, List<SongModel> songs) {
        String[] words = searchPhrase.split(" ");
        List<SongModel> output = new ArrayList<>();
        for (SongModel song : songs) {
            if (song.matchesQuery(words))
                output.add(song);
        }
        return output;
    }

}