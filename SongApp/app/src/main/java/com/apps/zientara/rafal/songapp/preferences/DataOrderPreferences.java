package com.apps.zientara.rafal.songapp.preferences;

import android.content.Context;

import com.apps.zientara.rafal.songapp.preferences.enums.CriteriaTypeEnum;

public class DataOrderPreferences extends AbstractPreferences {
    private static final String SETTINGS_IS_ASCENDING_KEY = "SETTINGS_IS_ASCENDING_KEY";
    private static final String SETTINGS_SORTING_CRITERIA_KEY = "SETTINGS_SORTING_CRITERIA_KEY";
    private static final boolean IS_ASCENDING_DEFAULT = true;
    private static DataOrderPreferences instance;
    private CriteriaTypeEnum sortingCriteria;
    private boolean isAscending;

    DataOrderPreferences(Context context) {
        super(context);
    }

    public static DataOrderPreferences getInstance(Context applicationContext) {
        if (instance == null)
            instance = new DataOrderPreferences(applicationContext);
        return instance;
    }

    public void refreshData() {
        isAscending = preferences.getBoolean(SETTINGS_IS_ASCENDING_KEY, IS_ASCENDING_DEFAULT);
        refershCriteria();
    }

    private void refershCriteria() {
        int criteriaInteger = preferences.getInt(SETTINGS_SORTING_CRITERIA_KEY, CriteriaTypeEnum.SONG_NAME.getValue());
        sortingCriteria = CriteriaTypeEnum.valueOf(criteriaInteger);
    }

    public CriteriaTypeEnum getSortingCriteria() {
        return sortingCriteria;
    }

    public void setSortingCriteria(CriteriaTypeEnum sortingCriteria) {
        this.sortingCriteria = sortingCriteria;
        saveInt(SETTINGS_SORTING_CRITERIA_KEY, sortingCriteria.getValue());
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }
}
