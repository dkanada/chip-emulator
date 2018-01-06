package com.dkanada.chip.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.dkanada.chip.interfaces.DisplayListener;

public class DisplayView extends SurfaceView {
    private Paint background;
    private Paint foreground;
    private byte[][] display;

    public DisplayView(Context context) {
        super(context);
        setFocusable(true);
        setWillNotDraw(false);

        background = new Paint();
        background.setColor(Color.BLACK);
        background.setStyle(Paint.Style.FILL);

        foreground = new Paint();
        foreground.setColor(Color.WHITE);
        foreground.setStyle(Paint.Style.FILL);
    }

    public void setDisplay(byte[][] array) {
        display = array;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawRect(0, 0, width, height, background);
        if (display != null) {
            double stepX = width / display.length;
            double stepY = height / display[0].length;

            for (int x = 0; x < display.length; x++) {
                for (int y = 0; y < display[1].length; y++) {
                    if (display[x][y] == 1) {
                        float startX = (float) stepX * x;
                        float startY = (float) stepY * y;
                        float endX = (float) stepX * (x + 1);
                        float endY = (float) stepY * (y + 1);
                        canvas.drawRect(startX, startY, endX, endY, foreground);
                    }
                }
            }
        } else {
            float startX = (width / 2) - 100;
            float startY = (height / 2) - 100;
            float endX = (width / 2) + 100;
            float endY = (height / 2) + 100;
            canvas.drawRect(startX, startY, endX, endY, foreground);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 2;
        int height = 1;

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
