package com.dkanada.chip.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dkanada.chip.R;
import com.dkanada.chip.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.checkPermissions(this);
    }
}
