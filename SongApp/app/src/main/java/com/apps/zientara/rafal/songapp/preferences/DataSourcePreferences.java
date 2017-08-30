package com.apps.zientara.rafal.songapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Evil on 30.08.2017.
 */

public class DataSourcePreferences {
    private static final String SETTINGS_USE_LOCAL_DB_KEY = "SETTINGS_USE_LOCAL_DB";
    private static final String SETTINGS_USE_FAKE_DATA_KEY = "SETTINGS_FAKE_DATA";
    private static final String SETTINGS_USE_ITUNES_KEY = "SETTINGS_ITUNES";
    private static final boolean ENABLE_LOCAL_DB_DEFAULT = false;
    private static final boolean ENABLE_FAKE_DATA_DEFAULT = true;
    private static final boolean ENABLE_TUNES_DEFAULT = false;
    private final SharedPreferences preferences;
    private boolean isLocalDbEnabled;
    private boolean isFakeDataEnabled;
    private boolean isTunesEnabled;

    public DataSourcePreferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        refreshData();
    }

    public void refreshData() {
        isLocalDbEnabled = preferences.getBoolean(SETTINGS_USE_LOCAL_DB_KEY, ENABLE_LOCAL_DB_DEFAULT);
        isFakeDataEnabled = preferences.getBoolean(SETTINGS_USE_FAKE_DATA_KEY, ENABLE_FAKE_DATA_DEFAULT);
        isTunesEnabled = preferences.getBoolean(SETTINGS_USE_ITUNES_KEY, ENABLE_TUNES_DEFAULT);
    }

    public boolean isLocalDbEnabled() {
        return isLocalDbEnabled;
    }

    public void setLocalDbEnabled(boolean localDbEnabled) {
        isLocalDbEnabled = localDbEnabled;
        saveBoolean(SETTINGS_USE_LOCAL_DB_KEY, localDbEnabled);
    }

    private void saveBoolean(String valueKey, boolean value) {
        preferences.edit().putBoolean(valueKey, value).apply();
    }

    public boolean isFakeDataEnabled() {
        return isFakeDataEnabled;
    }

    public void setFakeDataEnabled(boolean fakeDataEnabled) {
        isFakeDataEnabled = fakeDataEnabled;
        saveBoolean(SETTINGS_USE_FAKE_DATA_KEY, fakeDataEnabled);
    }

    public boolean isTunesEnabled() {
        return isTunesEnabled;
    }

    public void setTunesEnabled(boolean tunesEnabled) {
        isTunesEnabled = tunesEnabled;
        saveBoolean(SETTINGS_USE_ITUNES_KEY, tunesEnabled);
    }
}
