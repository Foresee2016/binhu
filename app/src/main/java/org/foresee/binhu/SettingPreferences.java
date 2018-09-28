package org.foresee.binhu;

import android.content.Context;
import android.preference.PreferenceManager;

public class SettingPreferences {
    private static final String PREF_MOON_MODE = "moonMode";

    public static boolean getMoonMode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_MOON_MODE, false);
    }

    public static void setMoonMode(Context context, boolean moonMode) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_MOON_MODE, moonMode).apply();
    }
}
