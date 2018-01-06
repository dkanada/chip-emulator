package com.dkanada.chip.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dkanada.chip.R;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.interfaces.KeypadListener;

public class ControllerView extends LinearLayout {
    Button top;
    Button bottom;
    Button left;
    Button right;

    public ControllerView(Context context, final KeypadListener keypadListener) {
        super(context);

        this.setOrientation(VERTICAL);

        top = new Button(context);
        top.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    keypadListener.keyDown((char) 0x2);
                } else {
                    keypadListener.keyUp((char) 0x2);
                }
                return true;
            }
        });
        this.addView(top);

        left = new Button(context);
        left.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    keypadListener.keyDown((char) 0x4);
                } else {
                    keypadListener.keyUp((char) 0x4);
                }
                return false;
            }
        });
        right = new Button(context);
        right.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    keypadListener.keyDown((char) 0x6);
                } else {
                    keypadListener.keyUp((char) 0x6);
                }
                return false;
            }
        });

        LinearLayout side = new LinearLayout(context);
        side.addView(left);
        side.addView(right);
        this.addView(side);

        bottom = new Button(context);
        bottom.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    keypadListener.keyDown((char) 0x8);
                } else {
                    keypadListener.keyUp((char) 0x8);
                }
                return false;
            }
        });
        this.addView(bottom);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec) / 3;
        int width = MeasureSpec.getSize(widthMeasureSpec) / 2;

        top.setHeight(height);
        bottom.setHeight(height);

        left.setHeight(height);
        left.setWidth(width);
        right.setHeight(height);
        right.setWidth(width);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
