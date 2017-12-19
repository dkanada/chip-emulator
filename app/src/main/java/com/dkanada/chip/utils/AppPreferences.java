package com.dkanada.chip.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dkanada.chip.R;

public class AppPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AppPreferences(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = sharedPreferences.edit();
        this.context = context;
    }

    public static AppPreferences get(Context context) {
        return new AppPreferences(context);
    }

    public String getTheme() {
        return sharedPreferences.getString(context.getString(R.string.pref_theme), "1");
    }

    public int getPrimaryColor() {
        return sharedPreferences.getInt(context.getString(R.string.pref_primary_color), context.getResources().getColor(R.color.primary));
    }

    public int getAccentColor() {
        return sharedPreferences.getInt(context.getString(R.string.pref_accent_color), context.getResources().getColor(R.color.accent));
    }
}
