package com.apps.zientara.rafal.songapp.preferences.enums;

/**
 * Created by Evil on 31.08.2017.
 */

public enum CriteriaTypeEnum {
    ARTIST(1),
    DATE(2),
    SONG_NAME(0);

    private int value;

    CriteriaTypeEnum(int typeValue) {
        this.value = typeValue;
    }

    public int getValue() {
        return value;
    }

    public static CriteriaTypeEnum valueOf(int value) {
        for (CriteriaTypeEnum e : CriteriaTypeEnum.values()) {
            if (e.value == value)
                return e;
        }
        return SONG_NAME;
    }
}
