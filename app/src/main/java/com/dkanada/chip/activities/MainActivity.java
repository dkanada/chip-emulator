package com.dkanada.chip.activities;

import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dkanada.chip.R;
import com.dkanada.chip.async.GameThread;
import com.dkanada.chip.utils.AppPreferences;
import com.dkanada.chip.utils.Utils;
import com.dkanada.chip.views.ControllerView;
import com.dkanada.chip.views.GameView;

import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.data.ExFilePickerResult;

public class MainActivity extends ThemeActivity {
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appPreferences = AppPreferences.get(this);
        setInitialConfiguration();

        Utils.checkPermissions(this);
        GameView gameView = findViewById(R.id.display);

        GameThread gameThread = new GameThread(gameView);
        gameThread.start();

        LinearLayout linearLayout = new ControllerView(this);
        LinearLayout main = findViewById(R.id.main);
        main.addView(linearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void setInitialConfiguration() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackgroundColor(AppPreferences.get(this).getPrimaryColor());

        setSupportActionBar(toolbar);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Utils.dark(appPreferences.getPrimaryColor(), 0.8));
        getWindow().setNavigationBarColor(appPreferences.getPrimaryColor());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
            if (result != null && result.getCount() > 0) {
                // contains selected files names and path
            }
        }
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
                ExFilePicker exFilePicker = new ExFilePicker();
                exFilePicker.setCanChooseOnlyOneItem(true);
                exFilePicker.setChoiceType(ExFilePicker.ChoiceType.FILES);
                exFilePicker.setStartDirectory(Environment.getExternalStorageDirectory().toString());
                exFilePicker.setNewFolderButtonDisabled(true);
                exFilePicker.start(this, 0);
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
