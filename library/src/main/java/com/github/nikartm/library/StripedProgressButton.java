package com.github.nikartm.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StripedProgressButton extends AppCompatButton implements Animatable {

    private Paint paint;
    private float stripeWidth = 7f;
    private int stripeAlpha = 0x90;
    private float density;

    public StripedProgressButton(Context context) {
        super(context);
    }

    public StripedProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StripedProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        stripedWidthToDp();
    }

    private void stripedWidthToDp() {
        density = getContext().getResources().getDisplayMetrics().density;
        stripeWidth = stripeWidth * density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(stripeWidth);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    private enum State {
        PROGRESS, DONE, STOPPED
    }

}
