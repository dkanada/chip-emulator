package com.dkanada.chip.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.dkanada.chip.R;
import com.dkanada.chip.utils.AppPreferences;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private AppPreferences appPreferences;
    private ListPreference speed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appPreferences = AppPreferences.get(getContext());
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        //speed = (ListPreference) findPreference(getString(R.string.pref_speed));
        //setSpeedSummary();
    }

    private void setSpeedSummary() {
        int speedValue = appPreferences.getSpeed();
        speed.setSummary(getResources().getStringArray(R.array.speedEntries)[speedValue] + "MHz");
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (isAdded()) {
            Preference preference = findPreference(key);
            if (preference == speed) {
                setSpeedSummary();
            }
        }
    }
}
