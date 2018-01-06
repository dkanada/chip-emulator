package com.dkanada.chip.views;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.dkanada.chip.interfaces.KeypadListener;
import com.dkanada.chip.views.ButtonView;

public class ControllerView extends LinearLayout {
    ButtonView up;
    ButtonView down;
    ButtonView left;
    ButtonView right;

    public ControllerView(Context context, final KeypadListener keypadListener) {
        super(context);

        this.setOrientation(VERTICAL);

        up = new ButtonView(context, keypadListener, (char) 0x2);
        this.addView(up);

        left = new ButtonView(context, keypadListener, (char) 0x4);
        right = new ButtonView(context, keypadListener, (char) 0x6);

        LinearLayout side = new LinearLayout(context);
        side.addView(left);
        side.addView(right);
        this.addView(side);

        down = new ButtonView(context, keypadListener, (char) 0x8);
        this.addView(down);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec) / 3;
        int width = MeasureSpec.getSize(widthMeasureSpec) / 2;

        up.setHeight(height);
        down.setHeight(height);

        left.setHeight(height);
        left.setWidth(width);
        right.setHeight(height);
        right.setWidth(width);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
