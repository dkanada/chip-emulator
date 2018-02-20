package com.dkanada.chip.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.dkanada.chip.interfaces.KeypadListener;

public class ButtonView extends AppCompatButton {
    public ButtonView(Context context, final KeypadListener keypadListener, final String key) {
        super(context);

        final char keycode;
        if (key.charAt(0) < 65) {
            keycode = (char) (key.charAt(0) - 48);
        } else {
            keycode = (char) (key.charAt(0) - 55);
        }

        setText(key);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.e("ButtonView", "button down :: " + key);
                    keypadListener.keyDown(keycode);
                } else {
                    keypadListener.keyUp(keycode);
                }
                return true;
            }
        });
    }
}
