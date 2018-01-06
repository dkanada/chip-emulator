package com.dkanada.chip.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dkanada.chip.R;

public class AppPreferences {
    private SharedPreferences sharedPreferences;
    private Context context;

    public AppPreferences(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public static AppPreferences get(Context context) {
        return new AppPreferences(context);
    }

    public String getSpeed() {
        return sharedPreferences.getString(context.getString(R.string.pref_speed), "1");
    }

    public int getForegroundColor() {
        return sharedPreferences.getInt(context.getString(R.string.pref_foreground_color), context.getResources().getColor(R.color.foreground));
    }

    public int getBackgroundColor() {
        return sharedPreferences.getInt(context.getString(R.string.pref_background_color), context.getResources().getColor(R.color.background));
    }
}
