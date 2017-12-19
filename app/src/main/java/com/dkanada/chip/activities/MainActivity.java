package com.dkanada.chip.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.dkanada.chip.R;
import com.dkanada.chip.utils.AppPreferences;
import com.dkanada.chip.utils.Utils;

public class MainActivity extends ThemeActivity {
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appPreferences = AppPreferences.get(this);
        setInitialConfiguration();

        Utils.checkPermissions(this);
    }

    private void setInitialConfiguration() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackgroundColor(getColor(R.color.primary));

        setSupportActionBar(toolbar);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Utils.dark(appPreferences.getPrimaryColor(), 0.8));
        getWindow().setNavigationBarColor(appPreferences.getPrimaryColor());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open:
                return true;
            case R.id.settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
