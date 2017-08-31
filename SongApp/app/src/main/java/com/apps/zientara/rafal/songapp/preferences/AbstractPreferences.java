package com.apps.zientara.rafal.songapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Evil on 31.08.2017.
 */

public abstract class AbstractPreferences {

    final SharedPreferences preferences;

    protected void saveBoolean(String valueKey, boolean value) {
        preferences.edit().putBoolean(valueKey, value).apply();
    }

    protected void saveInt(String valueKey, int value) {
        preferences.edit().putInt(valueKey, value).apply();
    }

    AbstractPreferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        refreshData();
    }

    protected abstract void refreshData();

}
