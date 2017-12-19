package com.dkanada.chip.fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.dkanada.chip.R;
import com.dkanada.chip.utils.AppPreferences;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private AppPreferences appPreferences;
    private ListPreference theme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appPreferences = AppPreferences.get(getContext());
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        theme = (ListPreference) findPreference(getString(R.string.pref_theme));
        setThemeSummary();
    }

    private void setThemeSummary() {
        int themeValue = Integer.valueOf(appPreferences.getTheme());
        theme.setSummary(getResources().getStringArray(R.array.themeEntries)[themeValue]);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (isAdded()) {
            Preference preference = findPreference(key);
            if (preference == theme) {
                setThemeSummary();
            }
        }
    }
}
