package com.dkanada.chip.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AspectRatioLayout extends FrameLayout {
    private int width;
    private int height;

    public AspectRatioLayout(Context context) {
        super(context);
    }

    public AspectRatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AspectRatioLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        width = 2;
        height = 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);
        int originalHeight = MeasureSpec.getSize(heightMeasureSpec);
        int calculatedHeight = originalWidth * height / width;

        int finalWidth;
        int finalHeight;

        if (calculatedHeight > originalHeight) {
            finalWidth = originalHeight * width / height;
            finalHeight = originalHeight;
        } else {
            finalWidth = originalWidth;
            finalHeight = calculatedHeight;
        }

        int measureWidth = MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY);
        int measureHeight = MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY);

        super.onMeasure(measureWidth, measureHeight);
    }
}
