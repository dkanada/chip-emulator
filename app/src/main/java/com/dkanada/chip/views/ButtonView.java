package com.dkanada.chip.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.dkanada.chip.interfaces.KeypadListener;

public class ButtonView extends AppCompatButton {
    public ButtonView(Context context, final KeypadListener keypadListener, final char key) {
        super(context);

        setText(Integer.toString(key));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ButtonView", "onTouch :: " + Integer.toString(key));
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    keypadListener.keyDown(key);
                } else {
                    keypadListener.keyUp(key);
                }
                return true;
            }
        });
    }
}
