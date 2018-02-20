package com.dkanada.chip.views;

import android.content.Context;
import android.widget.LinearLayout;

import com.dkanada.chip.interfaces.KeypadListener;

public class ControllerView extends LinearLayout {
    ButtonView x1;
    ButtonView x2;
    ButtonView x3;
    ButtonView xC;
    ButtonView x4;
    ButtonView x5;
    ButtonView x6;
    ButtonView xD;
    ButtonView x7;
    ButtonView x8;
    ButtonView x9;
    ButtonView xE;
    ButtonView xA;
    ButtonView x0;
    ButtonView xB;
    ButtonView xF;

    public ControllerView(Context context, final KeypadListener keypadListener) {
        super(context);
        setOrientation(VERTICAL);

        LinearLayout row1 = new LinearLayout(context);
        x1 = new ButtonView(context, keypadListener, "1");
        x2 = new ButtonView(context, keypadListener, "2");
        x3 = new ButtonView(context, keypadListener, "3");
        xC = new ButtonView(context, keypadListener, "C");
        row1.addView(x1);
        row1.addView(x2);
        row1.addView(x3);
        row1.addView(xC);
        this.addView(row1);

        LinearLayout row2 = new LinearLayout(context);
        x4 = new ButtonView(context, keypadListener, "4");
        x5 = new ButtonView(context, keypadListener, "5");
        x6 = new ButtonView(context, keypadListener, "6");
        xD = new ButtonView(context, keypadListener, "D");
        row2.addView(x4);
        row2.addView(x5);
        row2.addView(x6);
        row2.addView(xD);
        this.addView(row2);

        LinearLayout row3 = new LinearLayout(context);
        x7 = new ButtonView(context, keypadListener, "7");
        x8 = new ButtonView(context, keypadListener, "8");
        x9 = new ButtonView(context, keypadListener, "9");
        xE = new ButtonView(context, keypadListener, "E");
        row3.addView(x7);
        row3.addView(x8);
        row3.addView(x9);
        row3.addView(xE);
        this.addView(row3);

        LinearLayout row4 = new LinearLayout(context);
        xA = new ButtonView(context, keypadListener, "A");
        x0 = new ButtonView(context, keypadListener, "0");
        xB = new ButtonView(context, keypadListener, "B");
        xF = new ButtonView(context, keypadListener, "F");
        row4.addView(xA);
        row4.addView(x0);
        row4.addView(xB);
        row4.addView(xF);
        this.addView(row4);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec) / 4;
        int width = MeasureSpec.getSize(widthMeasureSpec) / 4;

        x1.setHeight(height);
        x1.setWidth(width);
        x2.setHeight(height);
        x2.setWidth(width);
        x3.setHeight(height);
        x3.setWidth(width);
        xC.setHeight(height);
        xC.setWidth(width);
        x4.setHeight(height);
        x4.setWidth(width);
        x5.setHeight(height);
        x5.setWidth(width);
        x6.setHeight(height);
        x6.setWidth(width);
        xD.setHeight(height);
        xD.setWidth(width);
        x7.setHeight(height);
        x7.setWidth(width);
        x8.setHeight(height);
        x8.setWidth(width);
        x9.setHeight(height);
        x9.setWidth(width);
        xE.setHeight(height);
        xE.setWidth(width);
        xA.setHeight(height);
        xA.setWidth(width);
        x0.setHeight(height);
        x0.setWidth(width);
        xB.setHeight(height);
        xB.setWidth(width);
        xF.setHeight(height);
        xF.setWidth(width);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
