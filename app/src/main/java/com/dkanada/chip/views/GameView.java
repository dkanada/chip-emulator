package com.dkanada.chip.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder surfaceHolder;
    GameThread gameThread;
    Paint paint;

    byte[][] display;

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

        gameThread = new GameThread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    public void update(byte[][] array) {
        this.display = array;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (display != null) {
            int stepX = width / display[0].length;
            int stepY = height / display[1].length;

            for (int x = 0; x < display[0].length; x++) {
                for (int y = 0; y < display[0].length; y++) {
                    if (display[x][y] == 1) {
                        canvas.drawRect(
                                stepX * x,
                                stepY * y,
                                stepX * (x + 1),
                                stepY * (y + 1),
                                paint);
                    }
                }
            }
        } else {
            canvas.drawRect(0, 0, 100, 100, paint);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.run();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void invalidate() {
        super.invalidate();
        Log.e("DisplayView: ", "INVALIDATE");
    }
}
