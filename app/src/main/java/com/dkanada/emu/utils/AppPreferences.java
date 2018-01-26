package com.dkanada.emu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dkanada.emu.R;

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

    public int getSpeed() {
        return Integer.parseInt(sharedPreferences.getString(context.getString(R.string.pref_speed), "250"));
    }

    public int getForegroundColor() {
        return sharedPreferences.getInt(context.getString(R.string.pref_foreground_color), context.getResources().getColor(R.color.foreground));
    }

    public int getBackgroundColor() {
        return sharedPreferences.getInt(context.getString(R.string.pref_background_color), context.getResources().getColor(R.color.background));
    }

    public boolean getRegisterQuirk() {
        return sharedPreferences.getBoolean(context.getResources().getString(R.string.pref_register_quirk), false);
    }

    public boolean getShiftQuirk() {
        return sharedPreferences.getBoolean(context.getResources().getString(R.string.pref_shift_quirk), false);
    }
}
