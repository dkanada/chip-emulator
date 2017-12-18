package com.dkanada.chip.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.dkanada.chip.R;
import com.dkanada.chip.utils.AppPreferences;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private ListPreference prefTheme;

    AppPreferences appPreferences;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appPreferences = AppPreferences.get(getContext());
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        context = getActivity();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        prefTheme = (ListPreference) findPreference(getString(R.string.pref_theme));

        // removes settings that wont work on lower versions
        Preference prefNavigationColor = findPreference(getString(R.string.pref_navigation_color));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            prefNavigationColor.setEnabled(false);
        }

        setThemeSummary();
    }

    private void setThemeSummary() {
        int themeValue = Integer.valueOf(appPreferences.getTheme());
        prefTheme.setSummary(getResources().getStringArray(R.array.themeEntries)[themeValue]);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (isAdded()) {
            Preference pref = findPreference(key);
            if (pref == prefTheme) {
                setThemeSummary();
            }
        }
    }
}
