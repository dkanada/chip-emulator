package com.dkanada.chip.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dkanada.chip.R;

public class ControllerView extends LinearLayout {
    Button top;
    Button bottom;
    Button left;
    Button right;

    public ControllerView(Context context) {
        super(context);

        this.setOrientation(VERTICAL);

        top = new Button(context);
        this.addView(top);

        left = new Button(context);
        right = new Button(context);

        LinearLayout side = new LinearLayout(context);
        side.addView(left);
        side.addView(right);
        this.addView(side);

        bottom = new Button(context);
        top.setPadding(4, 0, 4, 4);
        this.addView(bottom);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec) / 3;
        int width = MeasureSpec.getSize(widthMeasureSpec) / 2;

        top.setHeight(height);
        top.setPadding(4, 4, 40, 0);
        bottom.setHeight(height);

        left.setHeight(height);
        left.setWidth(width);
        right.setHeight(height);
        right.setWidth(width);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
