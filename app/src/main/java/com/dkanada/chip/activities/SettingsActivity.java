package com.dkanada.chip.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.dkanada.chip.R;
import com.dkanada.chip.fragments.SettingsFragment;
import com.dkanada.chip.utils.AppPreferences;
import com.dkanada.chip.utils.Utils;

public class SettingsActivity extends AppCompatActivity {
    private AppPreferences appPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appPreferences = AppPreferences.get(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setInitialConfiguration();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SettingsFragment fragment = new SettingsFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void setInitialConfiguration() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Utils.dark(appPreferences.getPrimaryColor(), 0.8));
        getWindow().setNavigationBarColor(appPreferences.getPrimaryColor());
    }
}
